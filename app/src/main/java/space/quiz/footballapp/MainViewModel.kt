package space.quiz.retrofit2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.Model.Standings.Standings
import space.quiz.footballapp.Repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ResponseCompetitions>> = MutableLiveData()
    val standingResponse: MutableLiveData<Response<ResponseStandings>> = MutableLiveData()

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
}