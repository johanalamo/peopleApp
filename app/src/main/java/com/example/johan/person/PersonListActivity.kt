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

    private var personListFragment: PersonListFragment = PersonListFragment.newInstance()

//    private var personDetailsFragment: PersonDetailsFragment? = null

    private var fragmentContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_person_list_activity)

        personListFragment = supportFragmentManager.findFragmentById(R.id.person_list_fragment) as PersonListFragment

        //aqui
//        fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container  XXXXXXXXXxxx)


        isLargeScreen = fragmentContainer != null

        //hide Action bar
        supportActionBar!!.hide()
    }


    override fun onListItemClicked (valor: String?) {
        showPersonDetail(valor)
    }

    fun showPersonDetail(valor: String?) {
        Log.d(TAG, "----------- showPersonDetail: valor -> " + valor)

        val intent: Intent = Intent(this, PersonDetailsActivity::class.java)
        Log.d(TAG, "----------- showPersonDetail: 2 ")
        intent.putExtra("p_id", valor) //person.login?.uuid)
        Log.d(TAG, "----------- showPersonDetail: 3 ")
        startActivity(intent)
        Log.d(TAG, "----------- showPersonDetail: 4 ")
    }
}
