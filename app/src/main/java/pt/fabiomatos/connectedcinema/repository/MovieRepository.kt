package pt.fabiomatos.connectedcinema.repository

import pt.fabiomatos.connectedcinema.models.ApiResponse
import pt.fabiomatos.connectedcinema.utils.RetrofitInstance

class MovieRepository {
    private val api = RetrofitInstance.service

    suspend fun getUpcoming(): ApiResponse {
        return api.getUpcoming()
    }

    suspend fun getTrending(): ApiResponse {
        return api.getTrending()
    }

    suspend fun getTopRated(): ApiResponse {
        return api.getTopRated()
    }
}