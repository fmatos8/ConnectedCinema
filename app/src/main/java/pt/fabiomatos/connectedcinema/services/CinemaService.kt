package pt.fabiomatos.connectedcinema.services
import pt.fabiomatos.connectedcinema.models.Movie
import pt.fabiomatos.connectedcinema.models.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CinemaService {

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Response

    @GET("trending/all/week")
    suspend fun getTrending(): Response

    @GET("movie/top_rated")
    suspend fun getTopRated(): Response

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        //@Query("id") id: Int
    ) : Movie
}