package space.quiz.footballapp

import androidx.fragment.app.Fragment

interface Communicator {
    fun passId(id: Int?, fragment: Fragment, key: String)
    fun openFragment(fragment: Fragment)
}