package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.support.v7.app.AppCompatActivity
import com.example.johan.person.R
import com.example.johan.person.response.DetailInfo
import kotlinx.android.synthetic.main.layout_detailinfo_list_recycler_view.view.*


class DetailInfoAdapter(private val data: ArrayList<DetailInfo>, private val context:AppCompatActivity) :
    RecyclerView.Adapter<DetailInfoAdapter.ViewHolder>() {

    class ViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt){    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_detailinfo_list_recycler_view, parent, false) as LinearLayout
        return ViewHolder(linearLyt)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linearLyt.txtTitle.text  = data[position].field
        holder.linearLyt.txtValue.text  = data[position].value
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
