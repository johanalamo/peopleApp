package com.example.johan.person

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.FrameLayout

class PersonListActivity : AppCompatActivity(), PersonListFragment.OnFragmentInteractionListener {

    private val TAG: String = PersonListActivity::class.java.simpleName

    private var isLargeScreen: Boolean = false

    private  var personListFragment: PersonListFragment? = null //= PersonListFragment.newInstance()

    private var personDetailsFragment: PersonDetailsFragment? = null

    private var fragmentContainer: FrameLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_person_list_activity)

        fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container)
        isLargeScreen = fragmentContainer != null

        var p_cols = if (isLargeScreen) 2 else 4

        personListFragment = PersonListFragment.newInstance(p_cols)
        personListFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, it, getString(R.string.list_fragment_tag))
                .addToBackStack(null)
                .commit()
        }

        //hide Action bar
        supportActionBar!!.hide()
    }


    override fun onListItemClicked (valor: String?) {
        showPersonDetail(valor)
    }

    private fun showPersonDetail(valor: String?) {
        Log.d(TAG, "------ isLargeScreen: " + isLargeScreen.toString())

        if (!isLargeScreen) {
            val intent: Intent = Intent(this, PersonDetailsActivity::class.java)
            intent.putExtra("p_id", valor) //person.login?.uuid)
            startActivity(intent)
        }else {
//            title = list.name
            personDetailsFragment = PersonDetailsFragment.newInstance(valor)
            personDetailsFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it, getString(R.string.list_fragment_tag))
                    .addToBackStack(null)
                    .commit()
            }
        }

    }
}
