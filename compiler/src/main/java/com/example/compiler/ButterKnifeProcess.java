package com.example.compiler;

import com.example.annotation.BindView;
import com.example.annotation.OnClick;
import com.google.auto.service.AutoService;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

//不用手动的生成Meta等签名信息文件
@AutoService(Processor.class)
public class ButterKnifeProcess extends AbstractProcessor {

    //操作Elements的工具和方法
    private Elements elementUtils;
    //报告错误和警告信息
    private Messager messager;
    //创建新的源文件，Class文件和辅助方法
    private Filer filer;
    private String activityName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        //从宏观上去描述一个类
        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE, "----start----");
        Set<? extends Element> bindViewSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //Key com.netease.butterKnife.MainActivity value是带有BindView注解的属性集合
        Map<String, List<VariableElement>> bindViewMap = new HashMap<>();
        // === 采集属性，过程 ===
        for (Element element : bindViewSet) {
            if (element instanceof VariableElement) {
                VariableElement varElement = (VariableElement) element;
                activityName = getActivityName(varElement);
                List<VariableElement> listElement = bindViewMap.get(activityName);
                if (listElement == null){
                    listElement = new ArrayList<>();
                    bindViewMap.put(activityName,listElement);
                }
                //带有注解的变量
                listElement.add(varElement);
                messager.printMessage(Diagnostic.Kind.NOTE,"Variable >>> "+varElement.getSimpleName());
            }
        }
        List<VariableElement> cacheElement = bindViewMap.get(activityName);
        try {
            JavaFileObject javaFileObject = filer.createSourceFile(activityName +
                    "$ViewBinder");
            //开始写文件
            String packageName = getPackageName(cacheElement.get(0));
            Writer writer = javaFileObject.openWriter();
            //类名为MainActivity$ViewBinder 不是 com.xxxx.MainActivity$Binder
            String activitySimpleName = cacheElement.get(0).getEnclosingElement().getSimpleName().toString() + "$ViewBinder";
            messager.printMessage(Diagnostic.Kind.NOTE,"activityName >>> "+activityName);

            writer.write("package "+packageName+";\n");
            writer.write("import com.example.library.ViewBinder;\n");
            writer.write("import android.view.View;\n");
            //第三行生成类
            writer.write("public class "+activitySimpleName+" implements ViewBinder<"+activityName+"> {\n");

            //生成Binder方法
            writer.write("public void bind(final " + activityName + " target) {\n");

            //for 循环生成MainActivity每个控件的属性
            for (VariableElement variableElement : cacheElement) {
                String filedName = variableElement.getSimpleName().toString();
                BindView bindView = variableElement.getAnnotation(BindView.class);
                int viewId = bindView.value();
                //target.tv = target.findViewById(xxx);
                writer.write("target." + filedName + " = " + "target.findViewById(" + viewId + ");\n");
            }
            writer.write("\n}\n}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // === 造文件过程 ===
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    private String getPackageName(VariableElement variableElement) {
        //获取类名标签
        TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
        //获取方法标签获取类名标签
        String packageName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
        messager.printMessage(Diagnostic.Kind.NOTE, "packageName >>> " + packageName);
        return packageName;
    }

    private String getActivityName(VariableElement variableElement) {
        String packageName = getPackageName(variableElement);
        TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
        //com.example.compare.MainActivity
        return packageName + "." + typeElement.getSimpleName().toString();
    }
}
