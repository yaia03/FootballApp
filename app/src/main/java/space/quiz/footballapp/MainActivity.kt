package space.quiz.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        mainViewPager.adapter = ViewPagerAdapter(supportFragmentManager)
    }

    private fun init(){
        mainViewPager = findViewById(R.id.main_viewpager)
    }

    inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount(): Int { return 1 }

        override fun getItem(position: Int): Fragment { return MainFragment() }
    }
}
