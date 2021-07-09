package space.quiz.footballapp.Model.Standings

import space.quiz.footballapp.Model.Team

data class Position(
        val position: Int,
        val team: Team,
        val playedGames: Int?,
        val won: Int?,
        val draw: Int?,
        val lost: Int?,
        val points: Int?,
        val goalsFor: Int?,
        val goalsAgainst: Int?,
        val goalDifference: Int?
): Comparable<Position> {
    override fun compareTo(other: Position): Int {
        TODO("Not yet implemented")
    }
}
