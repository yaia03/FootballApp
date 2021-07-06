package space.quiz.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var mainViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        mainViewPager = findViewById(R.id.main_viewpager)
        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mainFragment).commit()
    }

    override fun passId(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val transaction = this.supportFragmentManager.beginTransaction()
        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle

        transaction.replace(R.id.fragment_container, secondFragment).commit()
    }

}
