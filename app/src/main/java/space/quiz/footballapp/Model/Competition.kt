package space.quiz.footballapp.Model


data class Competition(
    val id: Int,
    val area: Area?,
    val name: String?,
    val code: String?,
    val emblemUrl: String?,
    val plan: String?,
    val currentSeason: CurrentSeason?,
    val numberOfAvailableSeasons: Int?,
    val lastUpdated: String?
)

