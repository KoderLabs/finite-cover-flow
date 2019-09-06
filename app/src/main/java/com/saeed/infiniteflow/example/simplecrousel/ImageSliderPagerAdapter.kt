package com.saeed.infiniteflow.example.simplecrousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.saeed.infiniteflow.example.R

class ImageSliderPagerAdapter(
    val onClick: (resId: Int) -> Unit
) : RecyclerView.Adapter<ImageSliderPagerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return RecyclerViewHolder(view, onClick)
    }

    override fun getItemCount(): Int = 10

    override fun getItemViewType(position: Int): Int {
        return R.layout.square_view
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class RecyclerViewHolder(
        val item: View,
        val onClick: (resId: Int) -> Unit
    ) : RecyclerView.ViewHolder(item) {

        fun bind(position: Int) {
            val image = item.findViewById<ImageView>(R.id.image)

            val resId = when {
                position % 4 == 0 -> {
                    R.drawable.watch_4
                }
                position % 3 == 0 -> {

                    R.drawable.watch_3
                }
                position % 2 == 0 -> {
                    R.drawable.watch_2
                }
                position % 1 == 0 -> {
                    R.drawable.watch_1
                }
                else -> {

                    R.drawable.watch_4
                }
            }
            image.setImageDrawable(
                ContextCompat.getDrawable(
                    image.context,
                    resId
                )
            )

            image.setOnClickListener {
                onClick(resId)
            }
        }

    }

}