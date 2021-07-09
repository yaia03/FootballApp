package space.quiz.footballapp.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import space.quiz.footballapp.Model.Standings.Position
import space.quiz.footballapp.R


class TeamsAdapter(private var teamList: ArrayList<Position>, private val onClickListener: TeamOnClickListener,
                   val context: Context?): RecyclerView.Adapter<TeamsAdapter.TeamHolder>() {

    inner class TeamHolder(view: View): RecyclerView.ViewHolder(view){
        val nameText: TextView = view.findViewById(R.id.rv_team)
        val positionText: TextView = view.findViewById(R.id.rv_position)
        val playedGamesText: TextView = view.findViewById(R.id.rv_played_games)
        val winText: TextView = view.findViewById(R.id.rv_win)
        val loseText: TextView = view.findViewById(R.id.rv_lose)
        val drawText: TextView = view.findViewById(R.id.rv_draw)
        val pointsText: TextView = view.findViewById(R.id.rv_win)
        val emblem: ImageView = view.findViewById(R.id.rv_emblem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.team_item, parent, false)
        return TeamHolder(view)
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        var  position = teamList[position]
        holder.positionText?.text = position.position.toString()
        holder.nameText?.text = position.team.name
        holder.drawText?.text = position.draw.toString()
        holder.loseText?.text = position.lost.toString()
        holder.playedGamesText?.text = position.playedGames.toString()
        holder.pointsText?.text = position.points.toString()
        holder.winText?.text = position.won.toString()
        if (position.team.crestUrl != null){
            val uri = Uri.parse(position.team.crestUrl)
            GlideToVectorYou.init()
                    .with(context)
                    .load(uri, holder.emblem)
        }

        holder.itemView.setOnClickListener {
            onClickListener.onClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return teamList.size
    }



    fun sortWinList(){
        teamList.sortByDescending { it.won }
        notifyDataSetChanged()
    }
    fun sortLoseList(){
        teamList.sortByDescending { it.lost }
        notifyDataSetChanged()
    }

    fun sortDrawList(){
        teamList.sortByDescending { it.draw }
        notifyDataSetChanged()
    }
    fun sortPointsList(){
        teamList.sortByDescending { it.points }
        notifyDataSetChanged()
    }

    fun sortPositionList(){
        teamList.sortByDescending { it.position }
        notifyDataSetChanged()
    }
}