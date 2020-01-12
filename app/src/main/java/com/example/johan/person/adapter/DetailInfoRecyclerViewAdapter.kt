package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.johan.person.R
import com.example.johan.person.response.DetailInfo
import kotlinx.android.synthetic.main.layout_detailinfo_list_recycler_view.view.*


class DetailInfoRecyclerViewAdapter(
    private val data: ArrayList<DetailInfo>
) :
    RecyclerView.Adapter<DetailInfoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_detailinfo_list_recycler_view, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailInfoRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.view.txtTitle.text = data[position].field
        holder.view.txtValue.text = data[position].value
    }

    override fun getItemCount() = data.size

    //internal objects and interfaces
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}
