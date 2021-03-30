package com.example.imagesearch.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.imagesearch.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor(private val repository: UnsplashRepository,
                                                  @Assisted state: SavedStateHandle
) : ViewModel() {
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = currentQuery.switchMap { repository.getSearchResults(it).cachedIn(viewModelScope) }

    fun searchPhotos(query:String){
        currentQuery.value=query
    }

    companion object{
        private const val DEFAULT_QUERY = "dogs"
        private const val CURRENT_QUERY = "current_query"
    }
}