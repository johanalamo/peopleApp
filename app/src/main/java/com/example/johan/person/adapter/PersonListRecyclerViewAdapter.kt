package com.example.johan.person.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_person_list_recycler_view.view.*
import android.widget.ImageView
import com.squareup.picasso.Picasso;
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.johan.person.response.MapPerson
import com.example.johan.person.PersonDetailsActivity
import com.example.johan.person.R
import com.squareup.picasso.Callback
import com.example.johan.person.viewholder.PersonListRecyclerViewViewHolder

class PersonListRecyclerViewAdapter(private val dataMap: MapPerson, private val context:AppCompatActivity) :
    RecyclerView.Adapter<PersonListRecyclerViewViewHolder>() {
    private val data = ArrayList(dataMap.values)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListRecyclerViewViewHolder {
        // create a new view
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_person_list_recycler_view, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters
        return PersonListRecyclerViewViewHolder(linearLyt)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: PersonListRecyclerViewViewHolder, position: Int) {
        holder.updateImageWithUrl(data[position].picture?.thumbnail, context)
        holder.linearLyt.txtName.text  = data[position].name?.first

        holder.linearLyt.setOnClickListener({
            val intent: Intent = Intent(this.context, PersonDetailsActivity::class.java)
            intent.putExtra("p_id", data[position].login?.uuid)
            this.context.startActivity(intent)
        })
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
