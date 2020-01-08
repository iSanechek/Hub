package com.isanechek.averdhub.ext

import androidx.compose.effectOf
import androidx.compose.memo
import androidx.compose.onCommit
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> observe(data: LiveData<T>) = effectOf<T?> {
    val result = +state { data.value }
    val observer = +memo { Observer<T> { result.value = it } }

    +onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    result.value
}