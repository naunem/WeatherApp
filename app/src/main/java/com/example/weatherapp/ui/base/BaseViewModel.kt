package com.example.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    open fun callApiOnIOThread() {
        viewModelScope.launch() {

        }
    }
}
