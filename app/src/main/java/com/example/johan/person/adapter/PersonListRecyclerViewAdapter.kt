package com.example.johan.person.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.johan.person.R
import com.example.johan.person.databinding.LayoutPersonListViewHolderBinding
import com.example.johan.person.response.MapPerson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_person_list_view_holder.view.*

class PersonListRecyclerViewAdapter(
    private val dataMap: MapPerson,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<PersonListRecyclerViewAdapter.ViewHolder>() {

    private val TAG = PersonListRecyclerViewAdapter::class.java.simpleName

    private val data = ArrayList(dataMap.values)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutPersonListViewHolderBinding>(layoutInflater,
            R.layout.layout_person_list_view_holder, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonListRecyclerViewAdapter.ViewHolder, position: Int) {
        val person = data[position]

        holder.binding.person = person
        Picasso.get().load(person.picture?.thumbnail).into(holder.binding.imgPerson)

        holder.binding.root.setOnClickListener {
            clickListener.listItemClicked(data[position].login?.uuid)
        }
        Log.d(TAG, " ------  PersonListRecyclerViewAdapter.onBindViewHolder.position = " + position.toString())
    }

    override fun getItemCount() = data.size

    //internal class and interfaces
    interface ClickListener {
        fun listItemClicked(valor: String?)
    }


    class ViewHolder(val binding: LayoutPersonListViewHolderBinding): RecyclerView.ViewHolder(binding.root)

}
