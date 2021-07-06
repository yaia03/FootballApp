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
import space.quiz.footballapp.Adapter.CompetitionAdapter
import space.quiz.footballapp.Adapter.CompetitionOnClickListener
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Repository.Repository
import space.quiz.retrofit2.MainViewModel
import space.quiz.retrofit2.MainViewModelFactory


class MainFragment : Fragment() {

    private lateinit var competitionRV: RecyclerView
    private lateinit var root:View
    private lateinit var viewModel: MainViewModel
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_main, container, false)
        init()

        communicator = activity as Communicator

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)


        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCompetition()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()!!.competitions.toString())
                createRV(response.body()?.competitions!!)
            }
            else {
                Log.d("Response", response.errorBody().toString())
            }
        })

        return root
    }

    private fun init(){
        competitionRV = root.findViewById(R.id.main_fragment_rv)
    }

    private fun createRV(list: List<Competition>){
        val adapter = CompetitionAdapter(list, object : CompetitionOnClickListener{
            override fun onClicked(competition: Competition) {
                communicator.passId(competition.id)
            }
        }, context)
        competitionRV.layoutManager = LinearLayoutManager(activity)
        competitionRV.adapter = adapter
    }

}
