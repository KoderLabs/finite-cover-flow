package com.saeed.finiteflow.example.infinitepagerexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.saeed.finiteflow.example.R

class CrouselPagerAdapter() : RecyclerView.Adapter<CrouselPagerAdapter.RecyclerViewHolder>() {

    private var list: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return RecyclerViewHolder(view)
    }

    fun setItems(list: MutableList<Int>){
        this.list = list
        notifyDataSetChanged()
    }

    fun addItems(list:MutableList<Int>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.count()

    override fun getItemViewType(position: Int): Int {
        return R.layout.circle_view
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class RecyclerViewHolder(val item: View) : RecyclerView.ViewHolder(item) {

        fun bind(position: Int) {
            val image = item.findViewById<ImageView>(R.id.image)

            when {
                position % 4 == 0 -> {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            image.context,
                            R.drawable.watch_4
                        )
                    )
                }
                position % 3 == 0 -> {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            image.context,
                            R.drawable.watch_3
                        )
                    )
                }
                position % 2 == 0 -> {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            image.context,
                            R.drawable.watch_2
                        )
                    )
                }
                position % 1 == 0 -> {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            image.context,
                            R.drawable.watch_1
                        )
                    )
                }
                else -> {
                    image.setImageDrawable(
                        ContextCompat.getDrawable(
                            image.context,
                            R.drawable.watch_4
                        )
                    )
                }
            }
        }

    }
}