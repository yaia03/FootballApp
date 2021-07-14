package space.quiz.footballapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.footballapp.Model.ResponseCompetitions
import space.quiz.footballapp.Repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {
    var myResponse: MutableLiveData<Response<ResponseCompetitions>> = MutableLiveData()

    fun getCompetition(): MutableLiveData<Response<ResponseCompetitions>>{
        if (myResponse.value == null) {
            loadCompetitions()
        }

        return myResponse
    }

    private fun loadCompetitions(){
        viewModelScope.launch {
            val response = repository.getCompetitions()
            myResponse.postValue(response)
        }
    }

}