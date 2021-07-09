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
import space.quiz.footballapp.Adapter.TeamOnClickListener
import space.quiz.footballapp.Adapter.TeamsAdapter
import space.quiz.footballapp.Model.Standings.Position
import space.quiz.footballapp.Model.Standings.ResponseStandings
import space.quiz.footballapp.Repository.Repository
import space.quiz.retrofit2.MainViewModel
import space.quiz.retrofit2.MainViewModelFactory

class SecondFragment : Fragment() {

    private lateinit var teamsRv: RecyclerView
    private lateinit var communicator: Communicator
    private lateinit var back: ImageView
    private lateinit var viewModel: MainViewModel
    private lateinit var winTxt: TextView
    private lateinit var loseTxt: TextView
    private lateinit var drawTxt: TextView
    private lateinit var pointsTxt: TextView
    private lateinit var positionTxt: TextView
    private lateinit var adapter: TeamsAdapter
    private lateinit var positionList: ArrayList<Position>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        back = view.findViewById(R.id.second_fragment_back)
        teamsRv = view.findViewById(R.id.second_fragment_rv)
        communicator = activity as Communicator
        readTeams(arguments?.getInt("compId")!!)

        back.setOnClickListener(View.OnClickListener {
            val fragment = MainFragment()
            communicator.openFragment(fragment)
        })

        winTxt = view.findViewById(R.id.table_win)
        winTxt.setOnClickListener(View.OnClickListener {
            adapter.sortWinList()
        })

        loseTxt = view.findViewById(R.id.table_lose)
        loseTxt.setOnClickListener(View.OnClickListener {
            adapter.sortLoseList()
        })

        positionTxt = view.findViewById(R.id.table_position)
        positionTxt.setOnClickListener(View.OnClickListener {
            adapter.sortPointsList()
        })

        drawTxt = view.findViewById(R.id.table_draw)
        drawTxt.setOnClickListener(View.OnClickListener {
            adapter.sortDrawList()
        })

        pointsTxt = view.findViewById(R.id.table_points)
        pointsTxt.setOnClickListener(View.OnClickListener {
            adapter.sortPointsList()
        })

        return view
    }



    private fun readTeams(id: Int): ResponseStandings? {
        var responseStandings: ResponseStandings? = null
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)

        viewModel.getStandings(id)
        viewModel.standingResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Standings", response.body()!!.standings.toString())
                positionList = (response.body()!!.standings[0].table as ArrayList<Position>?)!!
                createRV(positionList)
            }
        })
        return responseStandings
    }

    private fun createRV(list: ArrayList<Position>){
        adapter = TeamsAdapter(list, object : TeamOnClickListener {
            override fun onClicked(position: Position) {
                val pagerFragment = PagerFragment()
                communicator.passId(position.team.id, pagerFragment, "teamId")
//                communicator.passId(arguments!!.getInt("compId"), pagerFragment, "compId")
                communicator.openFragment(pagerFragment)
            }
        }, context)
        teamsRv.layoutManager = LinearLayoutManager(activity)
        teamsRv.adapter = adapter
    }

}