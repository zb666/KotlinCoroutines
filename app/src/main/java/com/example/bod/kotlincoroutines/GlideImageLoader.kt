package com.example.bod.kotlincoroutines

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.youth.banner.loader.ImageLoader

/**
 *
 * @ClassName: GlideImageLoader
 * @Description:
 * @CreateDate: 2019/10/18
 */
class GlideImageLoader: ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context!!).load(path).into(imageView!!)
    }

    override fun createImageView(context: Context?): ImageView {
        return RoundedImageView(context).apply {
            cornerRadius = 100f
        }
    }
}