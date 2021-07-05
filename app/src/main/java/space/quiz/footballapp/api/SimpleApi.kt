package space.quiz.footballapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Model.ResponseTeams
import space.quiz.footballapp.Model.Standings.ResponseStandings

interface SimpleApi {


    @GET("v2/competitions/")
    suspend fun getCompetitions(): Response<ResponseCompetitions>

    @GET("v2/competitions/{id}/standings")
    suspend fun getStandings(@Header("Authorization") token: String, @Path("id") id: Int): Response<ResponseStandings>
}