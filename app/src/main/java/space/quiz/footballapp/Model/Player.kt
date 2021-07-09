package space.quiz.footballapp.Model

data class Player(
        val id: Int,
        val name: String,
        val position: String?,
        val dateOfBirth: String?,
        val countryOfBirth: String?,
        val nationality: String?,
        val shirtNumber: Int?,
        val role: String?
)
