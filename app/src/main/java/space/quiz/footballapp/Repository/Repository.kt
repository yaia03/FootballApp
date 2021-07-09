package space.quiz.footballapp.Repository

import android.util.Log
import retrofit2.Response
import space.quiz.footballapp.Model.Matches.ResponseMatches
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.Model.Team
import space.quiz.footballapp.utils.RetrofitInstance

class Repository {

    suspend fun getCompetitions(): Response<ResponseCompetitions>{
        Log.d("Response", RetrofitInstance.api.getCompetitions().toString())
        return RetrofitInstance.api.getCompetitions()
    }

    suspend fun getStandings(id: Int): Response<ResponseStandings> {
        Log.d("Response", RetrofitInstance.api.getStandings(id).toString())
        return RetrofitInstance.api.getStandings(id)
    }

    suspend fun getTeam(id: Int): Response<Team> {
        Log.d("Response", RetrofitInstance.api.getTeam(id).toString())
        return RetrofitInstance.api.getTeam(id)
    }

    suspend fun getMatches(id: Int): Response<ResponseMatches>{
        Log.d("Response", RetrofitInstance.api.getMatches(id).toString())
        return RetrofitInstance.api.getMatches(id)
    }
}