package com.example.nit3213finalapp.presentation.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213finalapp.R
import com.example.nit3213finalapp.data.models.EntityItem

class DashboardAdapter(
    private var entities: List<EntityItem>,
    private val onItemClicked: (EntityItem) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    // this is the viewholder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // getting views from layout
        private val itemName = itemView.findViewById<TextView>(R.id.tvProperty1)
        private val designer = itemView.findViewById<TextView>(R.id.tvProperty2)

        // binding data to views
        fun bind(item: EntityItem) {
            itemName.text = item.itemName
            designer.text = item.designer

            // on item click sending data
            itemView.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    // inflating layout here
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return ViewHolder(view)
    }

    // binding viewholder with data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    // returning size of list
    override fun getItemCount(): Int = entities.size

    // updating list data
    fun setData(newList: List<EntityItem>) {
        entities = newList
        notifyDataSetChanged()
    }
}
