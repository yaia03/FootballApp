package space.quiz.footballapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.quiz.footballapp.Enums.RefereesEnums
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.R

class RefereesAdapter(private var refereesList: List<Player>) : RecyclerView.Adapter<RefereesAdapter.RefereesHolder>(){

        inner class RefereesHolder(view: View): RecyclerView.ViewHolder(view){
            val nameText: TextView = view.findViewById(R.id.squad_item_name)
            val position: TextView = view.findViewById(R.id.squad_item_position)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefereesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.squad_item, parent, false)
        return RefereesHolder(view)
    }

    override fun onBindViewHolder(holder: RefereesHolder, position: Int) {
        var referee = refereesList[position]
        holder.nameText.text = referee.name
        holder.position.text = when(referee.role){
            RefereesEnums.ASSISTANT_REFEREE_N1.name -> RefereesEnums.ASSISTANT_REFEREE_N1.value
            RefereesEnums.ASSISTANT_REFEREE_N2.name -> RefereesEnums.ASSISTANT_REFEREE_N2.value
            RefereesEnums.FOURTH_OFFICIAL.name -> RefereesEnums.FOURTH_OFFICIAL.value
            RefereesEnums.REFEREE.name -> RefereesEnums.REFEREE.value
            RefereesEnums.VIDEO_ASSISANT_REFEREE_N1.name -> RefereesEnums.VIDEO_ASSISANT_REFEREE_N1.value
            else -> RefereesEnums.VIDEO_ASSISANT_REFEREE_N2.value
        }
    }

    override fun getItemCount(): Int {
        return refereesList.size
    }
}