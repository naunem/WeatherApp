package com.example.weatherapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun <T> ViewGroup.inflate(@LayoutRes resId: Int): View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}
