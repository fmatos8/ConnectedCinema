package pt.fabiomatos.connectedcinema.enums

enum class MediaType(val type: String) {
    MOVIE("movie"), TV("tv"), PEOPLE("people");

    companion object {
        // Function to get enum value by status code
        fun fromMediaType(mediaType: String): MediaType? {
            return entries.find { it.type == mediaType }
        }
    }
}