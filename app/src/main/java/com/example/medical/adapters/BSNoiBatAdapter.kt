package com.example.medical.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.entity.BacSi
import com.example.medical.ui.home.HomeFragmentDirections

class BSNoiBatAdapter(private val context: Context): RecyclerView.Adapter<BSNoiBatAdapter.MyViewHolder>() {

    private var bacsiList = emptyList<BacSi>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view1)
        val title: TextView = itemView.findViewById(R.id.text_view1)
        val item: CardView = itemView.findViewById(R.id.item_Layout_bs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_bsnoibat, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bacsiList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val ItemsViewModel = bacsiList[position]
        val resourceName = ItemsViewModel.hinhanh +  "_foreground"
        val resourceId = context.resources.getIdentifier(resourceName, "mipmap", context.packageName)

        holder.imageView.setImageResource(resourceId)
        holder.title.setText(ItemsViewModel.name)

        holder.item.setOnClickListener{
            val currentDestinationId = holder.itemView.findNavController().currentDestination?.id

            // Chọn hành động điều hướng dựa trên điểm đến hiện tại
            val action = when (currentDestinationId) {
                R.id.nav_home -> HomeFragmentDirections.actionNavHomeToItemBacSiFragment(ItemsViewModel)
                else -> null
            }

            // Thực hiện điều hướng nếu hành động không phải null
            action?.let {
                holder.itemView.findNavController().navigate(it)
            }
        }
    }

    fun setData(bacsi: List<BacSi>) {
        this.bacsiList = bacsi
        notifyDataSetChanged()
    }
}
