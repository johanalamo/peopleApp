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

class PersonListAdapter(private val dataMap: MapPerson, private val context:AppCompatActivity) :
    RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {
    private val data = ArrayList(dataMap.values)
    class ViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt) {
        private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgPerson)

        fun updateImageWithUrl(url: String?, c:AppCompatActivity) {
         Picasso.with(itemView.context).load(url).into(myImageView,
             object
                 : Callback {
                 override fun onSuccess() {}
                 override fun onError() {}
             }
            )
            }
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_person_list_recycler_view, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(linearLyt)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
