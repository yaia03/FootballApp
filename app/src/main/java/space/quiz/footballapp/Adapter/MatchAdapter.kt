package space.quiz.footballapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.quiz.footballapp.Model.Matches.Match
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.R

class MatchAdapter(private var matchList: List<Match>, private val onClickListener: MatchOnClickListener)
    : RecyclerView.Adapter<MatchAdapter.MatchHolder>(){

        inner class MatchHolder(view: View): RecyclerView.ViewHolder(view){
            val awayTeam: TextView = view.findViewById(R.id.match_item_away_team)
            val homeTeam: TextView = view.findViewById(R.id.match_item_home_team)
            val score: TextView = view.findViewById(R.id.match_item_score)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchHolder(view)
    }

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        var match = matchList[position]
        holder.awayTeam.text = match.awayTeam?.name
        holder.homeTeam.text = match.homeTeam?.name
        holder.score.text = match.score?.fullTime?.homeTeam.toString() + ":" +
                match.score?.fullTime?.awayTeam.toString()
        holder.itemView.setOnClickListener(View.OnClickListener {
            onClickListener.onClicked(match)
        })
    }

    override fun getItemCount(): Int {
        return matchList.size
    }
}