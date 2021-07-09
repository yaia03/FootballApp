package space.quiz.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), Communicator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mainFragment).commit()
    }


    override fun passId(id: Int?, fragment: Fragment, key: String) {
        val bundle = Bundle()
        if (id != null) {
            bundle.putInt(key, id)
        }

        fragment.arguments = bundle
    }


    override fun openFragment(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment).commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

}
