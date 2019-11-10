package com.example.bod.kotlincoroutines.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bod.kotlincoroutines.AboutMeParams
import com.example.bod.kotlincoroutines.R
import java.io.File

/**
 *
 * @ClassName: PhotoActivity
 * @Description:
 * @CreateDate: 2019/11/6
 */
class PhotoActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
    }




    private fun cropImage(uri: Uri, outputX: Int, outputY: Int, requestCode: Int) {
        // 裁剪图片意图
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.putExtra("crop", "true")
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(File(AboutMeParams.HEAD_PATH)))
        // 裁剪框的比例，1：1
        if (android.os.Build.MODEL.contains("HUAWEI")) {//华为特殊处理 不然会显示圆
            intent.putExtra("aspectX", 9998)
            intent.putExtra("aspectY", 9999)
        } else {
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
        }
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX)
        intent.putExtra("outputY", outputY)
        // 图片格式
        intent.putExtra("outputFormat", "JPEG")
        intent.putExtra("noFaceDetection", true)
        intent.putExtra("return-data", false)
        intent.putExtra("circleCrop", "circleCrop")
        startActivityForResult(intent, requestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AboutMeParams.OPEN_CAMERA && resultCode == Activity.RESULT_OK) {
            
        }
    }

}