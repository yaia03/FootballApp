package space.quiz.footballapp.Repository

import android.util.Log
import retrofit2.Response
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Model.ResponseTeams
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.utils.RetrofitInstance

class Repository {

    suspend fun getCompetitions(): Response<ResponseCompetitions>{
        Log.d("Response", RetrofitInstance.api.getCompetitions().toString())
        return RetrofitInstance.api.getCompetitions()
    }

    suspend fun getStandings(token: String, id: Int): Response<ResponseStandings> {
        Log.d("Response", RetrofitInstance.api.getStandings(token, id).toString())
        return RetrofitInstance.api.getStandings(token, id)
    }
}