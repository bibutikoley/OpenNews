package io.bibuti.opennews.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.observe(this, observer)
}

inline fun <T> LiveData<T>.observeOnce(
    lifecycleOwner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T) {
            observer.invoke(t)
            removeObserver(this)
        }
    })
}
