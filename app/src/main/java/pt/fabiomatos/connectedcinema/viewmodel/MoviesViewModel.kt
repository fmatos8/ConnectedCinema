package pt.fabiomatos.connectedcinema.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.fabiomatos.connectedcinema.models.Movie
import pt.fabiomatos.connectedcinema.models.Results
import pt.fabiomatos.connectedcinema.repository.HomeRepository
import pt.fabiomatos.connectedcinema.repository.MovieRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class MoviesViewModel : ViewModel() {

    private val repository = MovieRepository()



    private val _detailsData = mutableStateOf<Movie?>(null)
    val detailsData: State<Movie?> get() = _detailsData

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading




    internal fun fetchMovie(id: Int) {
        if (_detailsData.value != null) return // Avoid multiple calls

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.getDetails(id)
                _detailsData.value = response

                Log.i("FETCH  MOVIE -> ", _detailsData.value!!.toString())

            } catch (e: Exception) {
                Log.e("ERRO FETCH", e.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }
}