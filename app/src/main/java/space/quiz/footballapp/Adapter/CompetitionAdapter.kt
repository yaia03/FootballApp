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
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.R

class CompetitionAdapter(private var competitionList: List<Competition>, private val onClickListener: CompetitionOnClickListener,
                         val context: Context?
): RecyclerView.Adapter<CompetitionAdapter.CompetitionHolder>() {

    inner class CompetitionHolder(view: View): RecyclerView.ViewHolder(view){
        val nameText: TextView = view.findViewById(R.id.comp_item_name)
        val startDateTextView: TextView = view.findViewById(R.id.comp_item_start_date)
        val endDateTextView: TextView = view.findViewById(R.id.comp_item_end_date)
        val emblemImage: ImageView = view.findViewById(R.id.comp_item_emblem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.competition_item, parent, false)
        return CompetitionHolder(view)
    }

    override fun onBindViewHolder(holder: CompetitionHolder, position: Int) {
        var  competition = competitionList[position]
        holder.nameText.text = competition.name
        holder.startDateTextView.text = competition.currentSeason?.startDate
        holder.endDateTextView.text = competition.currentSeason?.endDate
        when {
            competition.emblemUrl != null -> {
                val uri = Uri.parse(competition.emblemUrl)
                GlideToVectorYou.init()
                        .with(context)
                        .load(uri, holder.emblemImage)
            }
            competition.area.ensignUrl != null -> {
                val uri = Uri.parse(competition.area.ensignUrl)
                GlideToVectorYou.init()
                        .with(context)
                        .load(uri, holder.emblemImage)
            }
            else -> holder.emblemImage.setImageResource(R.drawable.ic_soccer)
        }

        holder.itemView.setOnClickListener {
            onClickListener.onClicked(competition)
        }
    }

    override fun getItemCount(): Int {
        return competitionList.size
    }
}