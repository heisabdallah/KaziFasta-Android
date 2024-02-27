package com.example.kazifasta.ui.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazifasta.data.model.Photo
import com.example.kazifasta.data.network.api.photosApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos

    init {
        viewModelScope.launch {
            try {
                val fetchedPhotos = photosApi.getPhotos()
                _photos.value = fetchedPhotos
            } catch (e: Exception) {
                // Handle network errors here
            }
        }
    }
}
