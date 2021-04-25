package io.bibuti.opennews.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import io.bibuti.opennews.core.load

object BindingAdapters {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImages(imageView: ImageView?, url: String?) {
        imageView?.apply {
            visibility = View.GONE
            url?.let {
                load(it)
                visibility = View.VISIBLE
            }
        }
    }

    // NOTE - This function only supports UTC Format time sent from Server.
    @BindingAdapter("utcTime", "desiredFormat", requireAll = false)
    @JvmStatic
    fun formatTimeAndDate(textView: TextView?, utcDate: String?, desiredFormat: String?) {
        textView?.apply {
            text = TimeFormatter.formatZonedTime(utcDate, desiredFormat)
        }
    }
}