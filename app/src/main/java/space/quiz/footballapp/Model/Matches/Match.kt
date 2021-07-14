package space.quiz.footballapp.Model.Matches


import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.Model.Season
import space.quiz.footballapp.Model.Team
import java.io.Serializable

data class Match(
        val id: Int,
        val competition: Competition?,
        val season: Season?,
        val utcDate: String?,
        val status: String?,
        val matchday: Int?,
        val stage: String?,
        val group: String?,
        val lastUpdated: String?,
        val score: Score?,
        val homeTeam: Team?,
        val awayTeam: Team?,
        val referees: List<Player>?
): Serializable{
//    fun getStatusMatch():String{
//        when(status){
//            EnumsStatusMatch.FINISHED.name-> return EnumsStatusMatch.FINISHED.name
//        }
//    }
}
