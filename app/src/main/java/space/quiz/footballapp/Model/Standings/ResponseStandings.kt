package space.quiz.footballapp.Model.Standings

import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.Season

data class ResponseStandings(
        val competitions: Competition,
        val season: Season,
        val standings: Standings
)
