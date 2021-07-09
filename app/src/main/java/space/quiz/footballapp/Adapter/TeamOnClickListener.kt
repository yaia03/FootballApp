package space.quiz.footballapp.Adapter

import space.quiz.footballapp.Model.Standings.Position

interface TeamOnClickListener {
    fun onClicked(team: Position)
}