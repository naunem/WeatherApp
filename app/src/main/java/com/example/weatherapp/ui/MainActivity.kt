package com.example.weatherapp.ui

import android.os.Bundle
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.extensions.viewBinding
import com.example.weatherapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
