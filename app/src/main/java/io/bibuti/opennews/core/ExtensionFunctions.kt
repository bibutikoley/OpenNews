package io.bibuti.opennews.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.removeObservers(this)
    liveData.observe(this, observer)
}

fun Boolean?.isTrue(): Boolean {
    return this == true
}

fun Boolean.toggle(): Boolean {
    return !this
}

fun Context?.hideKeyboard(view: View) {
    (this?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.hideKeyboard() {
    view?.let {
        activity?.hideKeyboard(it)
    }
}

fun Activity.hideKeyboard() {
    currentFocus?.let {
        hideKeyboard(it)
    }
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.isNotNull(): Boolean {
    return this != null
}

fun Double.adaptiveNumber(upto: Int = 2): String {
    return if (this.rem(1).equals(0.0)) {
        this.roundToInt().toString()
    } else {
        "%.${upto}f".format(this)
    }
}

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}