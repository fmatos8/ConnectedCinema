package pt.fabiomatos.connectedcinema.services
import pt.fabiomatos.connectedcinema.models.ApiResponse
import pt.fabiomatos.connectedcinema.models.Results
import retrofit2.Call
import retrofit2.http.GET

interface MoviesService {

    @GET("movie/upcoming")
    suspend fun getUpcoming(): ApiResponse

    @GET("trending/all/week")
    suspend fun getTrending(): ApiResponse
}