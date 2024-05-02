package com.acuon.moviesapp.utils.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import com.acuon.moviesapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
fun ImageView.setImageRoundCornerCenterCrop(
    imgUrl: Any?,
    radius: Int = 0,
    placeholder: Int = R.drawable.ic_launcher_background
) {
    val options = RequestOptions()
        .placeholder(placeholder)
        .error(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
    if (radius > 0)
        options.transform(CenterCrop(), RoundedCorners(radius))
    try {
        Glide.with(this.context)
            .load(imgUrl)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

@SuppressLint("CheckResult")
fun ImageView.setImageRoundCorner(
    imgUrl: Any?,
    radius: Int = 0,
    placeholder: Int = R.drawable.ic_launcher_background,
    rotate: Float = -1F,
    options: RequestOptions = RequestOptions()
        .placeholder(placeholder)
        .error(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
) {
    try {
        if (rotate != -1F) {
            Glide.with(this.context)
                .load(imgUrl)
                //.transform(RotateTransformation(context, 90F))
                .apply(options)
                .into(this)
        } else {
            if (radius > 0)
                options.transform(FitCenter(), RoundedCorners(radius))
            Glide.with(this.context)
                .load(imgUrl)
                .apply(options)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.loadImageWithThumbnail(imgUrl: String?, thumbUrl: String?) {
    Glide.with(context)
        .load(imgUrl)
        .placeholder(R.drawable.ic_launcher_background)
        .thumbnail(
            Glide.with(context)
                .load(thumbUrl)
        )
        .into(this)
}




