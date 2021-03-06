package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
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
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_detailinfo_list_recycler_view, parent, false) as LinearLayout
        return ViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: DetailInfoRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.linearLyt.txtTitle.text = data[position].field
        holder.linearLyt.txtValue.text = data[position].value
    }

    override fun getItemCount() = data.size

    //internal objects and interfaces
    class ViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt)

}
