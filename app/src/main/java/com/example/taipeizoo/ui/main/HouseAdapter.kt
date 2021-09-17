package com.example.taipeizoo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoo.R
import com.example.taipeizoo.extension.showTextIfNotBlank
import com.example.taipeizoo.model.House
import kotlinx.android.synthetic.main.component_detail_item.view.*

class HouseAdapter(
        private val onHouseClickListener: (id: Int, name: String) -> Unit,
        private var items: List<House> = listOf(),
) : RecyclerView.Adapter<HouseAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], onHouseClickListener)

    fun updateData(list: List<House>) {
        this.items = list
        notifyDataSetChanged()
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.component_detail_item, parent, false)
    ) {

        fun bind(item: House, onHouseClickListener: (id: Int, name: String) -> Unit) {

            with(itemView) {
                image_photo.setImageURI(item.picUrl)
                text_title.showTextIfNotBlank(item.name)
                text_subtitle.showTextIfNotBlank(item.info)
                text_category.showTextIfNotBlank(item.category)

                layout_card.setOnClickListener {
                    onHouseClickListener(item.id, item.name)
                }
            }
        }
    }
}