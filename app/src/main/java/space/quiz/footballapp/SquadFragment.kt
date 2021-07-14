package space.quiz.footballapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.quiz.footballapp.Adapter.PlayerOnClickListener
import space.quiz.footballapp.Adapter.SquadAdapter
import space.quiz.footballapp.Adapter.TeamOnClickListener
import space.quiz.footballapp.Adapter.TeamsAdapter
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.Model.Standings.Position
import space.quiz.footballapp.Model.Team
import space.quiz.footballapp.Repository.Repository


class SquadFragment : Fragment() {

    private lateinit var squadRV: RecyclerView
    private lateinit var viewModel: ThirdViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_squad, container, false)

        squadRV = view.findViewById(R.id.fragment_squad_rv)

        val repository = Repository()
        val viewModelFactory = ThirdViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(ThirdViewModel::class.java)

        readTeam(arguments?.getInt("id")!!)

        return view
    }

    private fun readTeam(id: Int): Team? {
        var team: Team? = null


        viewModel.getTeam(id)
        viewModel.teamResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", response.body()!!.toString())
                createRV(response.body()!!.squad!!)
            }
        })
        return team
    }

    private fun createRV(list: List<Player>){
        val adapter = SquadAdapter(list, object : PlayerOnClickListener {
            override fun onClicked(player: Player) {
                TODO("Not yet implemented")
            }

        })
        squadRV.layoutManager = LinearLayoutManager(activity)
        squadRV.adapter = adapter
    }
}