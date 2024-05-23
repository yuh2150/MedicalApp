package com.example.medical.adapters

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
import com.example.medical.ui.bacsi.BacSiFragmentDirections
import com.example.medical.ui.chuyenkhoa.ItemChuyenKhoaFragmentDirections

class BacSiAdapter: RecyclerView.Adapter<BacSiAdapter.MyViewHolder>() {

    private var bacsiList = emptyList<BacSi>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.textView_title)
        val descript: TextView = itemView.findViewById(R.id.textView_mota)
        val item: CardView = itemView.findViewById(R.id.itemlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_bs, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bacsiList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val ItemsViewModel = bacsiList[position]
        holder.imageView.setImageResource(R.mipmap.bacsilehoang)
        holder.title.setText(ItemsViewModel.name)
        holder.descript.setText(ItemsViewModel.mota)

        holder.item.setOnClickListener{
            val currentDestinationId = holder.itemView.findNavController().currentDestination?.id

            // Chọn hành động điều hướng dựa trên điểm đến hiện tại
            val action = when (currentDestinationId) {
                R.id.nav_bacsi -> BacSiFragmentDirections.actionBacSiFragmentToItemBacSiFragment(ItemsViewModel)
                R.id.itemChuyenKhoaFragment -> ItemChuyenKhoaFragmentDirections.actionItemChuyenKhoaFragmentToItemBacSiFragment(ItemsViewModel)
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
