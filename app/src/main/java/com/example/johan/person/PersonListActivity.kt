package com.example.johan.person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.arch.lifecycle.Observer
import com.example.johan.person.viewmodel.PersonListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import com.example.johan.person.adapter.PersonListRecyclerViewAdapter
import com.example.johan.person.response.MapPerson
import com.example.johan.person.response.Person

class PersonListActivity : AppCompatActivity(), PersonListRecyclerViewAdapter.ClickListener {

	private lateinit var recyclerView:RecyclerView
	private lateinit var viewAdapter: RecyclerView.Adapter<*>
	private lateinit var viewManager: RecyclerView.LayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.layout_person_list_activity)

		DataRepository.viewModelPersonList = ViewModelProviders.of(this).get(PersonListViewModel::class.java)
		DataRepository.viewModelPersonList.getPersonList().observe(
			this,
			Observer {
				personList -> createRecyclerViewPersonList(personList!!, R.id.rviewPersonList)
			}
		)
		DataRepository.viewModelPersonList.loadPersonListData()
		//hide Action bar
		supportActionBar!!.hide()
	}

	fun createRecyclerViewPersonList(data:MapPerson, idRecyclerView:Int){
		viewManager = GridLayoutManager(this, 4)
		viewAdapter = PersonListRecyclerViewAdapter(data, this, this)
		recyclerView = findViewById <RecyclerView>(idRecyclerView).apply {
			setHasFixedSize(false)
			layoutManager = viewManager
			adapter = viewAdapter
		}
	}

	override fun listItemClicked(person:Person){
		val intent:Intent = Intent(this, PersonDetailsActivity::class.java)
		intent.putExtra("p_id", person.login?.uuid)
		startActivity(intent)
	}
}
