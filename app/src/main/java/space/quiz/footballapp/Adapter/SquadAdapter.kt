package space.quiz.footballapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.R

class SquadAdapter(private var squadList: List<Player>, private val onClickListener: PlayerOnClickListener)
    : RecyclerView.Adapter<SquadAdapter.SquadHolder>(){

        inner class SquadHolder(view: View): RecyclerView.ViewHolder(view){
            val nameText: TextView = view.findViewById(R.id.squad_item_name)
            val position: TextView = view.findViewById(R.id.squad_item_position)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.squad_item, parent, false)
        return SquadHolder(view)
    }

    override fun onBindViewHolder(holder: SquadHolder, position: Int) {
        var player = squadList[position]
        holder.nameText.text = player.name
        if (player.position != null){
            holder.position.text = player.position
        }
        else
            holder.position.text = player.role
        holder.itemView.setOnClickListener(View.OnClickListener {
            onClickListener.onClicked(player)
        })
    }

    override fun getItemCount(): Int {
        return squadList.size
    }
}