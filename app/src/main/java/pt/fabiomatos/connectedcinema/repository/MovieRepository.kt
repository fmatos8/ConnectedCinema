package pt.fabiomatos.connectedcinema.repository

import pt.fabiomatos.connectedcinema.models.Movie
import pt.fabiomatos.connectedcinema.models.Response
import pt.fabiomatos.connectedcinema.utils.RetrofitInstance

class MovieRepository {
    private val api = RetrofitInstance.service

    suspend fun getDetails(id: Int): Movie {
        return api.getMovieDetails(id)
    }
}