package com.ejavinas.trackbyitunes.itunessearch.views.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Class for binding adapters
 */
object BindingAdapters {

    /**
     * Binding adapter for loading images
     */
    @JvmStatic
    @BindingAdapter("imageUrl", "errorDrawable")
    fun loadImage(view: ImageView, url: String, errorDrawable: Drawable) {
        Glide.with(view.context)
            .load(url)
            .error(errorDrawable)
            .into(view)
    }

}