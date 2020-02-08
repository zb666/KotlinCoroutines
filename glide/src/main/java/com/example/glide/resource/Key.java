package com.example.glide.resource;

/**
 * @ClassName: Key
 * @Description:
 * @CreateDate: 2020/2/8
 */
public class Key {
    private String key;

    /**
     * sha256（https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg）之前
     *  ac037ea49e34257dc5577d1796bb137dbaddc0e42a9dff051beee8ea457a4668 处理后
     * @param key
     */
    public Key(String key) {
        this.key = Tool.getSHA256StrJava(key);
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }
}
