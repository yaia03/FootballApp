package space.quiz.footballapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.quiz.footballapp.Repository.Repository


class SecondViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SecondViewModel(repository) as T
    }
}