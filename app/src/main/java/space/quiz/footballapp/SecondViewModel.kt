package space.quiz.footballapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.Repository.Repository

class SecondViewModel(private val repository: Repository) : ViewModel() {
    val myStandingResponse: MutableLiveData<Response<ResponseStandings>> = MutableLiveData()

    fun getStandings(id: Int): MutableLiveData<Response<ResponseStandings>>{
        if (myStandingResponse.value == null)
            loadStandings(id)

        return myStandingResponse
    }

    private fun loadStandings(id: Int){
        viewModelScope.launch {
            val response = repository.getStandings(id)
            myStandingResponse.value = response
        }
    }
}