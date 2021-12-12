package com.jieun.imagebrowser.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private var pending = false

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { t ->
            if (pending) {
                observer.onChanged(t)
                pending = false
            }
        })
    }

    override fun setValue(t: T?) {
        pending = true
        super.setValue(t)
    }

    fun call() {
        value = null
    }
}