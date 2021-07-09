package space.quiz.footballapp.Model

data class Team(
    val id: Int,
    val name: String?,
    val crestUrl: String?,
    val area: Area?,
    val shortName: String?,
    val tla: String?,
    val address: String?,
    val phone: String?,
    val website: String?,
    val email: String?,
    val founded: Int?,
    val clubColors: String?,
    val venue: String?,
    val squad: List<Player>?,
    val lastUpdated: String?
)
