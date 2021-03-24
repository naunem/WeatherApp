package com.example.weatherapp.extensions

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.weatherapp.R

fun View.navigate(
    @IdRes resId: Int,
    args: Bundle? = null
) {
    val navOptions = NavOptions.Builder().apply {
        setEnterAnim(R.anim.slide_in)
        setExitAnim(R.anim.fade_out)
        setPopEnterAnim(R.anim.fade_in)
        setPopExitAnim(R.anim.slide_out)
    }.build()
    findNavController().navigate(resId, args, navOptions)
}

fun View.hideKeyboard(): Boolean {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return imm.hideSoftInputFromWindow(windowToken, 0)
}
