package com.example.johan.person

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.johan.person.adapter.DetailInfoRecyclerViewAdapter
import com.example.johan.person.response.DetailInfo
import com.example.johan.person.response.MapPerson
import com.example.johan.person.response.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_person_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "personId"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PersonDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PersonDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonDetailsFragment : Fragment() {

    private val TAG = PersonDetailsFragment::class.java.simpleName
    // TODO: Rename and change types of parameters
    private var personId: String? = null



    private lateinit var recyclerViewDetails: RecyclerView
    private lateinit var viewAdapterDetails: RecyclerView.Adapter<*>
    private lateinit var viewManagerDetails: RecyclerView.LayoutManager

    private lateinit var imgPerson: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personId = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_person_details, container, false)

        val personList = DataRepository.viewModelPersonList.getPersonList()

//        chargePerson(personList.value, inflater, container)

        var dataMap = personList.value
        val person = dataMap?.get(this.personId)

        Log.d(TAG, "--------- person.name: -> " + person?.name?.first + " " + person?.name?.last)
//        showDetailsOnUi(person)
        var extra: ArrayList<DetailInfo> = getExtraData(person)
        var urlImage = person?.picture?.large
//        createRecyclerViewReviewList(extra, inflater, container, urlImage)

        view?.let {
            Log.d(TAG, " ----- inside view")
            viewManagerDetails = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            viewAdapterDetails = DetailInfoRecyclerViewAdapter(extra)
            recyclerViewDetails = it.findViewById<RecyclerView>(R.id.rviewDetailInfo).apply {
                setHasFixedSize(false)
                layoutManager = viewManagerDetails
                adapter = viewAdapterDetails
            }

            val name = it.findViewById<TextView>(R.id.txtName)
            name?.let {
                name.text = person?.name?.title + " " + person?.name?.first + " " + person?.name?.last
            }

            Log.d(TAG, " ----- inside view2")
            imgPerson = it.findViewById<ImageView>(R.id.imgPersonLarge)
            Picasso.with(context).load(urlImage).into(imgPerson)

        }


        Log.d(TAG, " ----- inside OnCreateView2")
        // Inflate the layout for this fragment
        return view
    }




    private fun getExtraData(p: Person?): ArrayList<DetailInfo> {
        var extra: ArrayList<DetailInfo> = ArrayList()

        extra.add(
            DetailInfo(
                getString(R.string.strCompleteName).toUpperCase() + ":",
                ucFirst(p?.name?.title) + ". " + ucFirst(p?.name?.first) + " " + ucFirst(p?.name?.last)
            )
        )
        extra.add(
            DetailInfo(
                getString(R.string.strDocument).toUpperCase() + ":",
                p?.id?.name + ": " + p?.id?.value
            )
        )
        extra.add(DetailInfo(getString(R.string.strNationality).toUpperCase() + ":", p?.nat))
        extra.add(DetailInfo(getString(R.string.strEmail).toUpperCase() + ":", p?.email))
        extra.add(DetailInfo(getString(R.string.strGender).toUpperCase() + ":", p?.gender))
        extra.add(
            DetailInfo(
                getString(R.string.strAge).toUpperCase() + ":",
                p?.dob?.age.toString()
            )
        )
        extra.add(DetailInfo(getString(R.string.strBornDate).toUpperCase() + ":", p?.dob?.date))
        extra.add(DetailInfo(getString(R.string.strPhone).toUpperCase() + ":", p?.phone))
        extra.add(DetailInfo(getString(R.string.strCell).toUpperCase() + ":", p?.cell))
        extra.add(
            DetailInfo(
                getString(R.string.strAddress).toUpperCase() + ":",
                p?.location?.street?.number + " - " + p?.location?.street?.name + "\n" + p?.location?.city + "\n" + p?.location?.state
            )
        )
        extra.add(
            DetailInfo(
                getString(R.string.strPostalCode).toUpperCase() + ":",
                p?.location?.postcode
            )
        )

        return extra
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment PersonDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(personId: String?) =
            PersonDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, personId)
                }
            }
    }
}
