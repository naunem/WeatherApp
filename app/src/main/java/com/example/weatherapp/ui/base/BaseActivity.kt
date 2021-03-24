package com.example.weatherapp.ui.base

import android.graphics.Rect
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.weatherapp.extensions.hideKeyboard

abstract class BaseActivity : AppCompatActivity() {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view is EditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    view.clearFocus()
                    view.hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}
