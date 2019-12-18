package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.support.v7.app.AppCompatActivity
import com.example.johan.person.R
import com.example.johan.person.response.DetailInfo
import kotlinx.android.synthetic.main.layout_detailinfo_list_recycler_view.view.*
import com.example.johan.person.viewholder.DetailInfoRecyclerViewViewHolder

class DetailInfoRecyclerViewAdapter(private val data: ArrayList<DetailInfo>, private val context:AppCompatActivity) :
    RecyclerView.Adapter<DetailInfoRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailInfoRecyclerViewViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_detailinfo_list_recycler_view, parent, false) as LinearLayout
        return DetailInfoRecyclerViewViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: DetailInfoRecyclerViewViewHolder, position: Int) {
        holder.linearLyt.txtTitle.text  = data[position].field
        holder.linearLyt.txtValue.text  = data[position].value
    }

    override fun getItemCount() = data.size
}
