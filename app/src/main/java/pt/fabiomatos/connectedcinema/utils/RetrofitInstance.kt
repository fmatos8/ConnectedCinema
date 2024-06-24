package pt.fabiomatos.connectedcinema.utils
import okhttp3.OkHttpClient
import pt.fabiomatos.connectedcinema.services.MoviesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val API_KEY: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOWEzZTkwNjc2ZDRhZGMzMjRjYjcxN2ZjNmJmZjdhMCIsInN1YiI6IjY1YjIzZjFiYTgwMjM2MDE2ZGFlYjc1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SSZWvn9j3hVumWm3PJPSu2me3KGcjdskxk3Tl6heOKY"
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    val service: MoviesService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)
    }
}