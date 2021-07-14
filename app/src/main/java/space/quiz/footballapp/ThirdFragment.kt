package space.quiz.footballapp

import android.content.Intent
import android.net.Uri
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
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import space.quiz.footballapp.Model.Team
import space.quiz.footballapp.Repository.Repository



class ThirdFragment : Fragment() {

    private lateinit var teamEmblem: ImageView
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var address: TextView
    private lateinit var website: TextView
    private lateinit var viewModel: ThirdViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val repository = Repository()
        val viewModelFactory = ThirdViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(ThirdViewModel::class.java)


//        readTeam(viewModel.teamId.value!!)
        name = view.findViewById(R.id.third_fragment_name)
        email = view.findViewById(R.id.third_fragment_email)
        phone = view.findViewById(R.id.third_fragment_phone)
        address = view.findViewById(R.id.third_fragment_address)
        teamEmblem = view.findViewById(R.id.third_fragment_emblem)
        website = view.findViewById(R.id.third_fragment_website)

        readTeam(arguments?.getInt("id")!!)

        website.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(website.text.toString()))
            startActivity(intent)
        })

        return view
    }


    fun readTeam(id: Int): Team? {
        var team: Team? = null

        viewModel.getTeam(id)
        viewModel.teamResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", response.body()!!.toString())
                open(response.body()!!)
            }
        })
        return team
    }

    fun open(team: Team){
        name.text = team.name
        email.text = team.email
        address.text = team.address
        phone.text = team.phone
        website.text = team.website

        if (team.crestUrl != null){
            val uri = Uri.parse(team.crestUrl)
            GlideToVectorYou.init()
                    .with(context)
                    .load(uri, teamEmblem)
        }
    }

}