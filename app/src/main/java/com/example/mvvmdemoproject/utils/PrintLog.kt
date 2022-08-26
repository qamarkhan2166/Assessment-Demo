package com.example.mvvmdemoproject.utils

import com.example.mvvmdemoproject.BuildConfig
import timber.log.Timber

object PrintLog {
    fun print(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Timber.tag(key).e(value)
        }
    }
    fun printI(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Timber.tag(key).i(value)
        }
    }
    fun printD(key: String, value: String) {
        if (BuildConfig.DEBUG) {
            Timber.tag(key).d(value)
        }
    }
}
