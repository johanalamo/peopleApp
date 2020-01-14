package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.johan.person.R
import com.example.johan.person.response.MapPerson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_person_list_view_holder.view.*

class PersonListRecyclerViewAdapter(
    private val dataMap: MapPerson,
    private val clickListener: PersonListRecyclerViewAdapter.ClickListener
) : RecyclerView.Adapter<PersonListRecyclerViewAdapter.ViewHolder>() {

    private val TAG = PersonListRecyclerViewAdapter::class.java.simpleName

    private val data = ArrayList(dataMap.values)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonListRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_person_list_view_holder, parent, false) as View
        return PersonListRecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonListRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.updateImageWithUrl(data[position].picture?.thumbnail)
        holder.view.txtName.text = data[position].name?.first
        holder.view.setOnClickListener {
            clickListener.listItemClicked(data[position].login?.uuid)
        }
        Log.d(TAG, " ------  PersonListRecyclerViewAdapter.onBindViewHolder.position = " + position.toString())
    }

    override fun getItemCount() = data.size

    //internal class and interfaces
    interface ClickListener {
        fun listItemClicked(valor: String?)
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgPerson)

        fun updateImageWithUrl(url: String?) {
            Picasso.with(itemView.context).load(url).into(
                myImageView,
                object : Callback {
                    override fun onSuccess() {}
                    override fun onError() {}
                }
            )
        }
    }
}
