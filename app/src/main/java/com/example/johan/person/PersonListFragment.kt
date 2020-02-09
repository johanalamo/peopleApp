package com.example.johan.person

import DataRepository
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.johan.person.adapter.PersonListRecyclerViewAdapter
import com.example.johan.person.layoutmanager.AnimatedLinearLayoutManager
import com.example.johan.person.response.MapPerson
import com.example.johan.person.viewmodel.PersonListViewModel
import kotlinx.android.synthetic.main.fragment_person_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "cols"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PersonListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PersonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonListFragment : Fragment(), PersonListRecyclerViewAdapter.ClickListener {
    private var TAG = PersonListFragment::class.java.simpleName
    // TODO: Rename and change types of parameters
    private var cols: Int = 3

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cols = it.getString(ARG_PARAM1, "2").toInt()
            Log.d(TAG, " ----- onCreate -> cols -> " + cols.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_list, container, false)
    }

    override fun listItemClicked(valor: String?) {
        listener?.onListItemClicked(valor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoading()
        DataRepository.viewModelPersonList =
            ViewModelProviders.of(this).get(PersonListViewModel::class.java)

        DataRepository.viewModelPersonList.getPersonList().observe(
            this,
            Observer { personList ->
                Log.d(
                    TAG,
                    "onActivityCreated: (personList == null) -> " + (personList == null).toString()
                )
                if (personList == null) {
                    showNetworkError()
                } else {
                    createRecyclerViewPersonList(personList!!, R.id.rviewPersonList)
                    showPersonList()
                }
            }
        )

        DataRepository.viewModelPersonList.loadPersonListData()
    }

    private fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
        networkErrorLayout.visibility = View.GONE
        rviewPersonList.visibility = View.GONE
        rviewPersonList.isEnabled = false
    }

    private fun showNetworkError() {
        loadingLayout.visibility = View.GONE
        networkErrorLayout.visibility = View.VISIBLE
        rviewPersonList.visibility = View.GONE
        rviewPersonList.isEnabled = false
    }

    private fun showPersonList() {
        loadingLayout.visibility = View.GONE
        networkErrorLayout.visibility = View.GONE
        rviewPersonList.visibility = View.VISIBLE
        rviewPersonList.isEnabled = true
    }

    fun createRecyclerViewPersonList(data: MapPerson, idRecyclerView: Int) {
        view?.let {
            viewManager = GridLayoutManager(context, cols)
            viewAdapter = PersonListRecyclerViewAdapter(data, this)
            recyclerView = it.findViewById<RecyclerView>(idRecyclerView).apply {
                setHasFixedSize(false)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListItemClicked(valor: String?)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(cols: Int) =
            PersonListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, cols.toString())
                }
            }
    }
}
