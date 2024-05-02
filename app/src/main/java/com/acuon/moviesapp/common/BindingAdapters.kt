package com.acuon.moviesapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.acuon.moviesapp.utils.extensions.dp
import com.acuon.moviesapp.utils.extensions.setImageRoundCornerCenterCrop

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        view.setImageRoundCornerCenterCrop(imageUrl)
    }

    @JvmStatic
    @BindingAdapter("imageURL", "cornerRadius", requireAll = false)
    fun loadImageWithRoundedCorner(view: ImageView, imageUrl: Any?, radius: Int = 0) {
        view.setImageRoundCornerCenterCrop(imageUrl, radius.dp)
    }

}