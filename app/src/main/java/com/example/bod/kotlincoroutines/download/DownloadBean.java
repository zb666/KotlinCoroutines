package com.example.bod.kotlincoroutines.download;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: DownloadBeean
 * @Description:
 * @CreateDate: 2019/8/27
 */
public class DownloadBean {
    public String title;
    public String content;
    public String versionCode;
    public String url;
    public String md5;

    public static DownloadBean parse(String responseJson) {

        try {
            //可能是不正常的Json数据
            JSONObject jsonObject = new JSONObject(responseJson);
            String title = jsonObject.optString("title");
            String content = jsonObject.optString("content");
            String versionCode = jsonObject.optString("versionCode");
            String url = jsonObject.optString("url");
            String md5 = jsonObject.optString("md5");
            DownloadBean downloadBean = new DownloadBean();
            downloadBean.title = title;
            downloadBean.content = content;
            downloadBean.versionCode = versionCode;
            downloadBean.title = title;
            downloadBean.url = url;
            downloadBean.md5 = md5;
            return downloadBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "DownloadBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
