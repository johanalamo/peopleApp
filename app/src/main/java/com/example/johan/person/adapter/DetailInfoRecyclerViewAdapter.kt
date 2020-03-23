package com.example.johan.person.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.johan.person.R
import com.example.johan.person.databinding.LayoutDetailinfoListViewHolderBinding
import com.example.johan.person.response.DetailInfo
import kotlinx.android.synthetic.main.layout_detailinfo_list_view_holder.view.*


class DetailInfoRecyclerViewAdapter(
    private val data: ArrayList<DetailInfo>
) :
    RecyclerView.Adapter<DetailInfoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutDetailinfoListViewHolderBinding>(layoutInflater,
            R.layout.layout_detailinfo_list_view_holder, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DetailInfoRecyclerViewAdapter.ViewHolder, position: Int) {
        val data = data[position]
        holder.binding.info = data
    }


    override fun getItemCount() = data.size

    //internal objects and interfaces
    class ViewHolder(val binding: LayoutDetailinfoListViewHolderBinding) : RecyclerView.ViewHolder(binding.root)

}
