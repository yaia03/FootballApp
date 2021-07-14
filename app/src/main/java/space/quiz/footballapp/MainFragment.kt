package space.quiz.footballapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.quiz.footballapp.Adapter.CompetitionAdapter
import space.quiz.footballapp.Adapter.CompetitionOnClickListener
import space.quiz.footballapp.Model.Competition
import space.quiz.footballapp.Repository.Repository
import java.util.*


class MainFragment : Fragment() {

    private lateinit var competitionRV: RecyclerView
    private lateinit var root:View
    private lateinit var viewModel: MainViewModel
    private lateinit var communicator: Communicator
    private lateinit var listComp: ArrayList<Competition>
    private lateinit var btnSearch: ImageButton
    private lateinit var btnClose: ImageButton
    private lateinit var editSearch: EditText
    private lateinit var appName: TextView
    private lateinit var adapter: CompetitionAdapter


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
                listComp = response.body()?.competitions!! as ArrayList<Competition>
                createRV(listComp)
            }
        })

        btnSearch.setOnClickListener(View.OnClickListener {
            appName.visibility = View.INVISIBLE
            editSearch.visibility = View.VISIBLE
            btnSearch.visibility = View.INVISIBLE
            btnClose.visibility = View.VISIBLE
        })

        btnClose.setOnClickListener(View.OnClickListener {
            appName.visibility = View.VISIBLE
            editSearch.visibility = View.INVISIBLE
            btnSearch.visibility = View.VISIBLE
            btnClose.visibility = View.INVISIBLE
            adapter.filterList(listComp)
        })


        return root
    }

    override fun onResume() {
        super.onResume()
        editSearch.addTextChangedListener {
            filter(it.toString())
        }
    }



    private fun init(){
        competitionRV = root.findViewById(R.id.main_fragment_rv)
        listComp = arrayListOf<Competition>()
        btnSearch = root.findViewById(R.id.main_fragment_btn_search)
        editSearch = root.findViewById(R.id.main_fragment_edit_text_search)
        btnClose = root.findViewById(R.id.main_fragment_btn_close)
        appName= root.findViewById(R.id.main_fragment_app_name)

    }

    private fun createRV(list: List<Competition>){
        adapter = CompetitionAdapter(list, object : CompetitionOnClickListener{
            override fun onClicked(competition: Competition) {
                val fragment = SecondFragment()
                val args = Bundle().apply {
                    putInt("compId", competition.id)
                }
                fragment.arguments = args
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack("bs")
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }, context)
        competitionRV.layoutManager = LinearLayoutManager(activity)
        competitionRV.adapter = adapter
    }

    private fun filter(text: String){
        val filterList = arrayListOf<Competition>()

        for (comp in listComp){
            if (comp.name!!.toLowerCase().contains(text.toLowerCase())){
                filterList.add(comp)
            }
        }
        adapter.filterList(filterList)
    }
}
