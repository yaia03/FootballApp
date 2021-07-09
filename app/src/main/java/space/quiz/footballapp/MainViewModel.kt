package space.quiz.retrofit2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.Matches.ResponseMatches
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.Model.Standings.Standings
import space.quiz.footballapp.Model.Team
import space.quiz.footballapp.Repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ResponseCompetitions>> = MutableLiveData()
    val standingResponse: MutableLiveData<Response<ResponseStandings>> = MutableLiveData()
    val teamResponse: MutableLiveData<Response<Team>> = MutableLiveData()
    val matchesResponse: MutableLiveData<Response<ResponseMatches>> = MutableLiveData()

    fun getCompetition(){
        viewModelScope.launch {
            val response = repository.getCompetitions()
            myResponse.value = response
        }
    }

    fun getStandings(id: Int){
        viewModelScope.launch {
            val response = repository.getStandings(id)
            standingResponse.value = response
        }
    }

    fun getTeam(id: Int){
        viewModelScope.launch {
            val response = repository.getTeam(id)
            teamResponse.value = response
        }
    }

    fun getMatches(id: Int){
        viewModelScope.launch {
            val response = repository.getMatches(id)
            matchesResponse.value = response
        }
    }

}