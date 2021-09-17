package com.example.taipeizoo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoo.R
import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.HouseInfo
import kotlinx.android.synthetic.main.component_house_detail.view.*

class HouseAdapter(
        private val onHouseClickListener: () -> Unit,
        private var items: List<House> = listOf(),
) : RecyclerView.Adapter<HouseAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], onHouseClickListener)

    fun updateData(data: HouseInfo) {
        this.items = data.results
        notifyDataSetChanged()
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.component_house_detail, parent, false)
    ) {

        fun bind(item: House, onHouseClickListener: () -> Unit) {

            with(itemView) {
                image_house.setImageURI(item.picUrl)
                text_title.text = item.name
                text_subtitle.text = item.info
                text_category.text = item.category

                layout_card.setOnClickListener {
                    onHouseClickListener()
                }
            }
        }
    }
}