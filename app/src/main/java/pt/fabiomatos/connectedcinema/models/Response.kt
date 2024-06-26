package pt.fabiomatos.connectedcinema.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("dates")
    var dates: Dates? = Dates(),
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: List<Results> = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)

data class Results(
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("media_type")
    var mediaType: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("original_name")
    var originalName: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int? = null
)

data class Dates(
    @SerializedName("maximum")
    var maximum: String? = null,
    @SerializedName("minimum")
    var minimum: String? = null
)