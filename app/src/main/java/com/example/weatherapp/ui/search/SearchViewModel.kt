package com.example.weatherapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.AppRepository
import com.example.weatherapp.data.repository.LocalRepository
import com.example.weatherapp.data.source.ResultWrapper
import com.example.weatherapp.data.source.model.Location
import com.example.weatherapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: AppRepository,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val searchResultLiveData = MutableLiveData<List<Location>>()
    private val errorApiLiveData = MutableLiveData<String>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    internal fun getSearchResultLiveData(): LiveData<List<Location>> = searchResultLiveData

    internal fun getErrorApiLiveData(): LiveData<String> = errorApiLiveData

    internal fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

    internal fun searchLocation(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingLiveData.postValue(true)
            when (val response = repository.searchLocationByName(query)) {
                is ResultWrapper.Success -> {
                    applyFavoriteLocation(response.value)
                }
                is ResultWrapper.GenericError -> {
                    errorApiLiveData.postValue(response.message)
                }
                is ResultWrapper.NetworkError -> {
                    errorApiLiveData.postValue(response.io.message)
                }
                else -> Unit
            }
            loadingLiveData.postValue(false)
        }
    }

    internal fun handleFavoriteLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            location.run {
                if (isFavorite) {
                    localRepository.saveLocation(this)
                } else {
                    localRepository.removeLocation(this)
                }
            }
        }
    }

    private fun applyFavoriteLocation(listLocation: List<Location>) {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = localRepository.getAllLocation()
            listLocation.map {
                favorites.forEach { favorite ->
                    if (favorite.woeid == it.woeid) {
                        it.isFavorite = true
                        return@forEach
                    }
                }
            }
            searchResultLiveData.postValue(listLocation)
        }
    }
}
