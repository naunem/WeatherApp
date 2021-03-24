package com.example.weatherapp.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R

fun Fragment.navigate(
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

fun Fragment.navigate(directions: NavDirections) {
    val navOptions = NavOptions.Builder().apply {
        setEnterAnim(R.anim.slide_in)
        setExitAnim(R.anim.fade_out)
        setPopEnterAnim(R.anim.fade_in)
        setPopExitAnim(R.anim.slide_out)
    }.build()
    findNavController().navigate(directions, navOptions)
}

//fun Fragment.superNavHostFragment() =
//    activity?.supportFragmentManager?.findFragmentById(R.id.navHostSuperContainer) as NavHostFragment
