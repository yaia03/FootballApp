package space.quiz.footballapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.quiz.footballapp.Adapter.RefereesAdapter
import space.quiz.footballapp.Enums.MatchStatus
import space.quiz.footballapp.Enums.RefereesEnums
import space.quiz.footballapp.Enums.StageEnums
import space.quiz.footballapp.Model.Matches.Match
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.Repository.Repository
import space.quiz.retrofit2.MainViewModel
import space.quiz.retrofit2.MainViewModelFactory

class MatchInfoFragment : Fragment() {

    private lateinit var root: View
    private lateinit var homeTeamTxt: TextView
    private lateinit var awayTeamTxt: TextView
    private lateinit var scoreTxt: TextView
    private lateinit var statusTxt: TextView
    private lateinit var dateTxt: TextView
    private lateinit var competitionTxt: TextView
    private lateinit var stageTxt: TextView
    private lateinit var fullTimeScoreTxt: TextView
    private lateinit var halfTimeScoreTxt: TextView
    private lateinit var extraTimeScoreTxt: TextView
    private lateinit var penaltiesScoreTxt: TextView
    private lateinit var competitionEmblem: ImageView
    private lateinit var refereesRV: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_match_info, container, false)

        init()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)

//        readMatches(arguments?.getInt("id")!!, 3)
        joinMatch(arguments?.getSerializable("match") as Match)


        return root
    }



    private fun init(){
        homeTeamTxt = root.findViewById(R.id.match_info_home_team)
        awayTeamTxt = root.findViewById(R.id.match_info_away_team)
        scoreTxt = root.findViewById(R.id.match_info_score)
        statusTxt = root.findViewById(R.id.match_info_status)
        dateTxt = root.findViewById(R.id.match_info_date)
        competitionTxt = root.findViewById(R.id.match_info_competition_name)
        stageTxt = root.findViewById(R.id.match_info_stage)
        fullTimeScoreTxt = root.findViewById(R.id.match_info_full_time_score)
        halfTimeScoreTxt = root.findViewById(R.id.match_info_halftime_score)
        extraTimeScoreTxt = root.findViewById(R.id.match_info_extra_time_score)
        penaltiesScoreTxt = root.findViewById(R.id.match_info_penalties_score)
        competitionEmblem = root.findViewById(R.id.match_info_competition_emblem)
        refereesRV = root.findViewById(R.id.match_info_referees_rv)
    }

//    private fun readMatches(id: Int, position: Int){
//        viewModel.getMatches(id)
//        viewModel.matchesResponse.observe(viewLifecycleOwner, Observer { response ->
//            if (response.isSuccessful){
//                Log.d("Response", response.body()!!.toString())
//                joinMatch(response.body()!!.matches[position])
//            }
//        })
//    }

    private fun joinMatch(match: Match){
        homeTeamTxt.text = match.homeTeam?.name
        awayTeamTxt.text = match.awayTeam?.name
        scoreTxt.text = "${match.score!!.fullTime!!.homeTeam.toString()}:" +
                "${match.score.fullTime!!.awayTeam.toString()}"
        statusTxt.text = when(match.status){
            MatchStatus.FINISHED.name -> MatchStatus.FINISHED.status
            else -> MatchStatus.SCHEDULED.status
        }
        dateTxt.text = match.utcDate
        competitionTxt.text = match.competition?.name
        stageTxt.text = when(match.stage){
            StageEnums.FINAL.name -> StageEnums.FINAL.stage
            StageEnums.GROUP_STAGE.name -> StageEnums.GROUP_STAGE.stage
            StageEnums.LAST_16.name -> StageEnums.LAST_16.stage
            StageEnums.QUARTER_FINAL.name -> StageEnums.QUARTER_FINAL.stage
            else -> StageEnums.SEMI_FINAL.stage
        }
        fullTimeScoreTxt.text = "${match.score.fullTime.homeTeam}:${match.score.fullTime.awayTeam}"
        halfTimeScoreTxt.text = "${match.score.halfTime?.homeTeam}:${match.score.halfTime?.awayTeam}"
        if (match.score.extraTime?.homeTeam != null && match.score.extraTime?.awayTeam != null) {
            extraTimeScoreTxt.text = "${match.score.extraTime.homeTeam}:${match.score.extraTime.awayTeam}"
        }
        if (match.score.penalties?.homeTeam != null){
            penaltiesScoreTxt.text = "${match.score.penalties.homeTeam}:${match.score.penalties.awayTeam}"
        }
        createRv(match.referees!!)
    }

    private fun createRv(list: List<Player>){
        val adapter = RefereesAdapter(list)
        refereesRV.adapter = adapter
        refereesRV.layoutManager = LinearLayoutManager(activity)
    }
}