package space.quiz.footballapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.footballapp.Model.Team
import space.quiz.footballapp.Repository.Repository

class ThirdViewModel(private val repository: Repository) : ViewModel() {

    var teamResponse : MutableLiveData<Response<Team>> = MutableLiveData()

    fun getTeam(id: Int): MutableLiveData<Response<Team>>{
        if (teamResponse.value == null)
            loadTeam(id)

        return teamResponse
    }

    private fun loadTeam(id: Int){
        viewModelScope.launch {
            val response = repository.getTeam(id)
            teamResponse.value = response
        }
    }
}