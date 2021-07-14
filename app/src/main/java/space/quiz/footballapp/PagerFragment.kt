package space.quiz.footballapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class PagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private var thirdFragment = ThirdFragment()
    private var matchFragment = MatchFragment()
    private var squadFragment = SquadFragment()
    private lateinit var communicator: Communicator
    private lateinit var back: ImageView
    private var compId = 0

    @SuppressLint("FragmentBackPressedCallback")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        back = view.findViewById(R.id.pager_fragment_back)
        compId = arguments?.getInt("compId")!!
        communicator = activity as Communicator
        communicator.passId(arguments?.getInt("teamId")!!, thirdFragment, "id")
        communicator.passId(arguments?.getInt("teamId")!!, matchFragment, "id")
        communicator.passId(arguments?.getInt("teamId")!!, squadFragment, "id")


        viewPager = view.findViewById(R.id.fragment_pager_vp)
        tabs = view.findViewById(R.id.fragment_pager_tab)
        viewPager.adapter = ViewPagerAdapter(childFragmentManager)
        tabs.setupWithViewPager(viewPager)

        return view
    }



    inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> thirdFragment
                1 -> matchFragment
                else -> squadFragment
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0 -> "Информация"
                1 -> "Матчи"
                else -> "Состав"
            }
        }

    }
}