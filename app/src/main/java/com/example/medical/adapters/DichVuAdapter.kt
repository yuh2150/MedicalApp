package com.example.medical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medical.R

class DichVuAdapter(imageUrl: ArrayList<String>, title : ArrayList<String> ): RecyclerView.Adapter<DichVuAdapter.MyViewHolder>() {

    private var image= imageUrl
    private var title = title

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.textView)
        val item: CardView = itemView.findViewById(R.id.item_dichvu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_dichvu, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        val ItemsViewModel = image[position]
        Glide.with(holder.itemView).load(image[position]).fitCenter()
            .into(holder.imageView)

        holder.title.text = title[position]

    }

}
