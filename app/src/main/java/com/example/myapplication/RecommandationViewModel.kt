// RecommendationViewModel.kt
package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecommendationViewModel : ViewModel() {

    // Private mutable state that can only be changed from within the ViewModel
    private val _recommendedMovieIds = MutableStateFlow<List<Int>>(emptyList())
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())


    // Public, read-only state that the UI can observe
    val recommendedMovieIds: StateFlow<List<Int>> = _recommendedMovieIds.asStateFlow()
    val moviesList: StateFlow<List<Movie>> = _moviesList.asStateFlow()


    // Function to update the state from your UI
    fun updateRecommendedMovies(neighborIds: List<Int>) {
        viewModelScope.launch {
            _recommendedMovieIds.value = neighborIds
        }
    }

    fun updateMoviesList(movies: List<Movie>) {
        viewModelScope.launch {
            _moviesList.value = movies
        }
    }
}
