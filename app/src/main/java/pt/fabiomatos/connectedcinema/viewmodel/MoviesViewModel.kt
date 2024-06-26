package pt.fabiomatos.connectedcinema.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.fabiomatos.connectedcinema.models.Results
import pt.fabiomatos.connectedcinema.repository.MovieRepository

class MoviesViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _upcoming = MutableLiveData<List<Results>>()
    private val _trending = MutableLiveData<List<Results>>()
    private val _toprated = MutableLiveData<List<Results>>()


    val upcoming: LiveData<List<Results>> get() = _upcoming
    val trending: LiveData<List<Results>> get() = _trending
    val toprated: LiveData<List<Results>> get() = _toprated

    init {
        fetchTrending()
        fetchUpcoming()
        fetchTopRated()
    }

    private fun fetchTrending() {
        viewModelScope.launch {
            try {
                val response = repository.getTrending()
                _trending.value = response.results
            } catch (e: Exception) {
                Log.e("ERRO FETCH", e.toString())
            }
        }
    }

    private fun fetchUpcoming() {
        viewModelScope.launch {
            try {
                val response = repository.getUpcoming()
                _upcoming.value = response.results
            } catch (e: Exception) {
                Log.e("ERRO FETCH", e.toString())
            }
        }
    }

    private fun fetchTopRated() {
        viewModelScope.launch {
            try {
                val response = repository.getTopRated()
                _toprated.value = response.results
            } catch (e: Exception) {
                Log.e("ERRO FETCH", e.toString())
            }
        }
    }
}



