package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_person_list_recycler_view.view.*
import android.support.v7.app.AppCompatActivity
import com.example.johan.person.response.MapPerson
import com.example.johan.person.R
import com.example.johan.person.viewholder.PersonListRecyclerViewViewHolder
import com.example.johan.person.listener.PersonListRecyclerViewClickListener

class PersonListRecyclerViewAdapter(
			private val dataMap: MapPerson,
			private val context:AppCompatActivity,
			private val clickListener:PersonListRecyclerViewClickListener
		) : RecyclerView.Adapter<PersonListRecyclerViewViewHolder>() {

	private val data = ArrayList(dataMap.values)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListRecyclerViewViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_person_list_recycler_view, parent, false) as LinearLayout
        return PersonListRecyclerViewViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: PersonListRecyclerViewViewHolder, position: Int) {
        holder.updateImageWithUrl(data[position].picture?.thumbnail, context)
        holder.linearLyt.txtName.text  = data[position].name?.first

        holder.linearLyt.setOnClickListener {
			clickListener.listItemClicked(data[position])
        }
    }

    override fun getItemCount() = data.size
}
