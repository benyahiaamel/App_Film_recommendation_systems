// RecommendationViewModel.kt
package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RecommendationViewModel : ViewModel() {

    private val _selectedMovieIds = MutableStateFlow<List<Int>>(emptyList())
    val selectedMovieIds: StateFlow<List<Int>> = _selectedMovieIds.asStateFlow()

    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> = _moviesList.asStateFlow()

    private val _recommendedMovieIds = MutableStateFlow<List<Int>>(emptyList())
    val recommendedMovieIds: StateFlow<List<Int>> = _recommendedMovieIds.asStateFlow()


    fun updateSelectedMovies(ids: List<Int>) {
        viewModelScope.launch {
            _selectedMovieIds.value = ids
        }
    }

    fun updateMoviesList(movies: List<Movie>) {
        viewModelScope.launch {
            _moviesList.value = movies
        }
    }

    fun computeRecommendations(neighbors: List<List<Int>>) {
        viewModelScope.launch {

            val selected = _selectedMovieIds.value
            if (selected.isEmpty()) {
                _recommendedMovieIds.value = emptyList()
                return@launch
            }

            val combined = selected.flatMap { neighbors[it] }

            val finalRecommendations = combined
                .groupBy { it }
                .mapValues { it.value.size }
                .toList()
                .sortedByDescending { it.second }
                .map { it.first }
                .distinct()
                .take(10)

            _recommendedMovieIds.value = finalRecommendations
        }
    }
}
