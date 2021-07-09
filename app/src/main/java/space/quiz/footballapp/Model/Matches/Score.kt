package space.quiz.footballapp.Model.Matches

data class Score(
        val winner: String?,
        val duration: String?,
        val fullTime: Time?,
        val halfTime: Time?,
        val extraTime: Time?,
        val penalties: Time?,
)
