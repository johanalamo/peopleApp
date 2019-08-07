package com.example.johan.person

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.layout_person_details_activity.*
import kotlinx.android.synthetic.main.layout_person_details.*
import com.example.johan.person.adapter.DetailInfoAdapter
import com.example.johan.person.response.DetailInfo
import com.example.johan.person.response.*

import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun ucFirst(s:String?):String? = if (s == null) null else s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase()

class PersonDetailsActivity : AppCompatActivity() {
    private var personId: String = ""
    private lateinit var recyclerViewDetails:RecyclerView
    private lateinit var viewAdapterDetails: RecyclerView.Adapter<*>
    private lateinit var viewManagerDetails: RecyclerView.LayoutManager

    private lateinit var imgPerson: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_up, R.anim.slide_off)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_person_details_activity)
        try {
            this.personId = getIntent().getExtras().getString("p_id")
        } catch (e: Exception) {
            this.personId = "1"
        }
        DataRepository.viewModelPersonList.getPersonList().observe(this,
                 Observer {
                    personList -> chargePerson(personList)
                    }
        )
        //hide Action bar
        supportActionBar?.hide()
    }

    private fun chargePerson(dataMap:MapPerson?){
        val data = ArrayList(dataMap?.values)
        val person = dataMap?.get(this.personId)
        showDetailsOnUi(person)
        var extra: ArrayList<DetailInfo> = getExtraData(person)
        createRecyclerViewReviewList(extra)

        var url = person?.picture?.large

        imgPerson = findViewById<ImageView>(R.id.imgPersonLarge)
        Picasso.with(this).load(url).into(imgPerson)
    }

    private fun getExtraData(p:Person?): ArrayList<DetailInfo>{
      var r: DetailInfo = DetailInfo()
      var extra:ArrayList<DetailInfo> = ArrayList()

      extra.add(DetailInfo(getString(R.string.strCompleteName).toUpperCase() + ":", ucFirst(p?.name?.title) + ". " + ucFirst(p?.name?.first) + " " + ucFirst(p?.name?.last) ) )
      extra.add(DetailInfo(getString(R.string.strDocument).toUpperCase() + ":", p?.id?.name + ": " + p?.id?.value) )
      extra.add(DetailInfo(getString(R.string.strNationality).toUpperCase() + ":", p?.nat) )
      extra.add(DetailInfo(getString(R.string.strEmail).toUpperCase() + ":", p?.email) )
      extra.add(DetailInfo(getString(R.string.strGender).toUpperCase() + ":", p?.gender) )
      extra.add(DetailInfo(getString(R.string.strAge).toUpperCase() + ":", p?.dob?.age.toString()) )
      extra.add(DetailInfo(getString(R.string.strBornDate).toUpperCase() + ":", p?.dob?.date) )
      extra.add(DetailInfo(getString(R.string.strPhone).toUpperCase() + ":", p?.phone) )
      extra.add(DetailInfo(getString(R.string.strCell).toUpperCase() + ":", p?.cell) )
      extra.add(DetailInfo(getString(R.string.strAddress).toUpperCase() + ":", p?.location?.street + "\n" + p?.location?.city + "\n" + p?.location?.state) )
      extra.add(DetailInfo(getString(R.string.strPostalCode).toUpperCase() + ":", p?.location?.postcode.toString()) )

      return extra
    }

    fun showDetailsOnUi(res: Person?) {
        txtName.text = res?.name?.first
    }
    fun createRecyclerViewReviewList(data:ArrayList<DetailInfo>){
        viewManagerDetails = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewAdapterDetails = DetailInfoAdapter(data, this)
        recyclerViewDetails = findViewById <RecyclerView>(R.id.rviewDetailInfo).apply {
            setHasFixedSize(false);
            layoutManager = viewManagerDetails
            adapter = viewAdapterDetails
        }
    }
    fun pressButton(view: View){
        when (view.id)  {
            R.id.btnBack -> finish()
        }
    }
}
