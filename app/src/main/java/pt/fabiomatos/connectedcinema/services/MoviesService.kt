package pt.fabiomatos.connectedcinema.services
import pt.fabiomatos.connectedcinema.models.Response
import retrofit2.http.GET

interface MoviesService {

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Response

    @GET("trending/all/week")
    suspend fun getTrending(): Response

    @GET("movie/top_rated")
    suspend fun getTopRated(): Response
}