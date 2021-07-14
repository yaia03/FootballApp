package space.quiz.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), Communicator {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            val mainFragment = MainFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, mainFragment)
                    .commit()
        }
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
        transaction.replace(R.id.fragment_container, fragment)
            .addToBackStack("bs")
            .commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }
}
