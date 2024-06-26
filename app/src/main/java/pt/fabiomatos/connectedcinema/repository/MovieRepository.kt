package pt.fabiomatos.connectedcinema.repository

import pt.fabiomatos.connectedcinema.models.Response
import pt.fabiomatos.connectedcinema.utils.RetrofitInstance

class MovieRepository {
    private val api = RetrofitInstance.service

    suspend fun getUpcoming(): Response {
        return api.getUpcoming()
    }

    suspend fun getTrending(): Response {
        return api.getTrending()
    }

    suspend fun getTopRated(): Response {
        return api.getTopRated()
    }
}