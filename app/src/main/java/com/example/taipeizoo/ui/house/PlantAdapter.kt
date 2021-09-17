package com.example.taipeizoo.ui.house

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoo.R
import com.example.taipeizoo.extension.showTextIfNotBlank
import com.example.taipeizoo.model.Plant
import kotlinx.android.synthetic.main.component_detail_item.view.*

class PlantAdapter(
        private val onPlantClickListener: (plantName: String) -> Unit,
        private var items: List<Plant> = listOf(),
) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], onPlantClickListener)

    fun updateData(plants: List<Plant>) {
        this.items = plants
        notifyDataSetChanged()
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.component_detail_item, parent, false)
    ) {

        fun bind(item: Plant, onHouseClickListener: (plantName: String) -> Unit) {

            with(itemView) {
                image_photo.setImageURI(item.pic01Url)
                text_title.showTextIfNotBlank(item.nameC)
                text_subtitle.showTextIfNotBlank(item.brief)
                text_category.showTextIfNotBlank(item.alsoKnown)
                text_category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)

                layout_card.setOnClickListener {
                    onHouseClickListener(item.nameC)
                }
            }
        }
    }
}