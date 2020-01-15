package com.example.johan.person

import DataRepository
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.johan.person.adapter.DetailInfoRecyclerViewAdapter
import com.example.johan.person.adapter.DetailsAdapter
import com.example.johan.person.response.DetailInfo
import com.example.johan.person.response.MapPerson
import com.example.johan.person.response.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_person_details.*

fun ucFirst(s: String?): String? =
    if (s == null) null else s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()

class PersonDetailsActivity : AppCompatActivity() {
    private var personId: String = ""
    private lateinit var recyclerViewDetails: RecyclerView
    private lateinit var viewAdapterDetails: RecyclerView.Adapter<*>
    private lateinit var viewManagerDetails: RecyclerView.LayoutManager

    private lateinit var imgPerson: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_up, R.anim.slide_off)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_person_details_activity)
        try {
            this.personId = getIntent()?.getExtras()?.getString("p_id") ?: ""
        } catch (e: Exception) {
            this.personId = "1"
        }
        DataRepository.viewModelPersonList.getPersonList().observe(
            this,
            Observer { personList ->
                chargePerson(personList)
            }
        )
        //hide Action bar
        supportActionBar?.hide()
    }

    private fun chargePerson(dataMap: MapPerson?) {
        val person = dataMap?.get(this.personId)
        showDetailsOnUi(person)
        var extra: ArrayList<DetailInfo> = DetailsAdapter.getExtraData(person, this)
        createRecyclerViewReviewList(extra)

        var url = person?.picture?.large

        imgPerson = findViewById<ImageView>(R.id.imgPersonLarge)
        Picasso.with(this).load(url).into(imgPerson)
    }

    fun showDetailsOnUi(res: Person?) {
        txtName.text = res?.name?.first
    }

    fun createRecyclerViewReviewList(data: ArrayList<DetailInfo>) {
        viewManagerDetails = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewAdapterDetails = DetailInfoRecyclerViewAdapter(data)
        recyclerViewDetails = findViewById<RecyclerView>(R.id.rviewDetailInfo).apply {
            setHasFixedSize(false);
            layoutManager = viewManagerDetails
            adapter = viewAdapterDetails
        }
    }

    fun pressButton(view: View) {
        when (view.id) {
            R.id.btnBack -> finish()
        }
    }
}
