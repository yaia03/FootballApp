package space.quiz.footballapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.quiz.footballapp.Model.Matches.ResponseMatches
import space.quiz.footballapp.Repository.Repository

class MatchViewModel(private val repository: Repository) : ViewModel() {

    var matchesResponse: MutableLiveData<Response<ResponseMatches>> = MutableLiveData()

    fun getMatches(id: Int): MutableLiveData<Response<ResponseMatches>>{
        if (matchesResponse.value == null)
            loadMatches(id)

        return matchesResponse
    }

    fun loadMatches(id: Int){
        viewModelScope.launch {
            val response = repository.getMatches(id)
            matchesResponse.value = response
        }
    }
}