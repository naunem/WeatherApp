package com.example.weatherapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.AppRepository
import com.example.weatherapp.data.source.ResultWrapper
import com.example.weatherapp.data.source.model.Location
import com.example.weatherapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: AppRepository): BaseViewModel() {

    private val searchResultLiveData = MutableLiveData<List<Location>>()

    internal fun getSearchResultLiveData(): LiveData<List<Location>> = searchResultLiveData

    internal fun searchLocation(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchLocationByName(query)
            when(response) {
                is ResultWrapper.Success -> {
                    searchResultLiveData.postValue(response.value)
                }
                else -> {

                }
            }
        }
    }
}
