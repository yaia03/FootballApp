package space.quiz.footballapp.api

import retrofit2.Response
import retrofit2.http.*
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.Matches.ResponseMatches
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Model.ResponseTeams
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.Model.Team

interface SimpleApi {


    @GET("v2/competitions/")
    suspend fun getCompetitions(): Response<ResponseCompetitions>

    @GET("v2/competitions/{id}/standings")
    suspend fun getStandings(@Path("id") id: Int): Response<ResponseStandings>

    @GET("v2/teams/{id}")
    suspend fun getTeam(@Path("id") id: Int): Response<Team>

    @GET("v2/teams/{id}/matches")
    suspend fun getMatches(@Path("id") id: Int): Response<ResponseMatches>
}