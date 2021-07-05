package space.quiz.retrofit2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.quiz.footballapp.Repository.Repository


class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}