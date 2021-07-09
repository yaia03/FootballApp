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
import space.quiz.footballapp.Adapter.MatchAdapter
import space.quiz.footballapp.Adapter.MatchOnClickListener
import space.quiz.footballapp.Adapter.PlayerOnClickListener
import space.quiz.footballapp.Adapter.SquadAdapter
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Model.Matches.Match
import space.quiz.footballapp.Model.Player
import space.quiz.footballapp.Repository.Repository
import space.quiz.retrofit2.MainViewModel
import space.quiz.retrofit2.MainViewModelFactory
import java.util.ArrayList

class MatchFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var matchRV: RecyclerView
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        matchRV = view.findViewById(R.id.fragment_match_rv)

        communicator = activity as Communicator

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)

        readMatches(arguments?.getInt("id")!!)

        return view
    }

    private fun readMatches(id: Int){
        viewModel.getMatches(id)
        viewModel.matchesResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", response.body()!!.toString())
                createRV(response.body()?.matches!!)
            }
        })
    }


   private fun createRV(list: List<Match>){
       val adapter = MatchAdapter(list, object : MatchOnClickListener {
           override fun onClicked(match: Match) {
               val fragment = MatchInfoFragment()
               val bundle = Bundle()
               bundle.putSerializable("match", match)
               fragment.arguments = bundle
//               communicator.passId(position, fragment, "position")
//               communicator.passId(arguments?.getInt("id"), fragment, "id")
               communicator.openFragment(fragment)
           }
       })
       matchRV.layoutManager = LinearLayoutManager(activity)
       matchRV.adapter = adapter
   }

}