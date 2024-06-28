package pt.fabiomatos.connectedcinema.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.fabiomatos.connectedcinema.models.Movie
import pt.fabiomatos.connectedcinema.models.Results
import pt.fabiomatos.connectedcinema.repository.HomeRepository
import pt.fabiomatos.connectedcinema.repository.MovieRepository

class MoviesViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _details = MutableLiveData<Movie>()

    val details: LiveData<Movie> get() = _details


    internal fun fetchMovie(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDetails(id)
                _details.value = response

                Log.e("TRENDING LIST -> ", _details.value!!.toString())

            } catch (e: Exception) {
                Log.e("ERRO FETCH", e.toString())
            }
        }
    }
}