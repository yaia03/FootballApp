package space.quiz.footballapp.Model

data class ResponseTeams(
    val count: Int,
    val competition: Competition,
    val season: Season,
    val teams: List<Team>
)
