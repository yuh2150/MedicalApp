package com.example.medical.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R

import com.example.medical.entity.ChuyenKhoa
import com.example.medical.ui.bacsi.BacSiFragmentDirections
import com.example.medical.ui.chuyenkhoa.ChuyenKhoaFragmentDirections
import com.example.medical.ui.chuyenkhoa.ItemChuyenKhoaFragmentDirections
import com.example.medical.ui.home.HomeFragmentDirections


class ChuyenKhoaAdapter(private val context: Context):  RecyclerView.Adapter<ChuyenKhoaAdapter.MyViewHolder>() {

    private var chuyenkhoaList = emptyList<ChuyenKhoa>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView: TextView = itemView.findViewById(R.id.text_view)
        val item: CardView = itemView.findViewById(R.id.item_Layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_ck, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return chuyenkhoaList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem: ChuyenKhoa = chuyenkhoaList[position]
//        var name = currentItem.name

        val ItemsViewModel = chuyenkhoaList[position]
//        holder.imageView.setImageResource(ItemsViewModel.image)
        val resourceName = ItemsViewModel.hinhanh +  "_foreground"
        val resourceId = context.resources.getIdentifier(resourceName, "mipmap", context.packageName)
//        Log.d("Sdasdasd",resourceId.toString())
        holder.imageView.setImageResource(resourceId)
//        holder.imageView.setImageResource(R.mipmap.khoaxuongkhop)
        holder.textView.text = ItemsViewModel.name

        holder.item.setOnClickListener{

            val currentDestinationId = holder.itemView.findNavController().currentDestination?.id

            // Chọn hành động điều hướng dựa trên điểm đến hiện tại
            val action = when (currentDestinationId) {
                R.id.nav_chuyenkhoa -> ChuyenKhoaFragmentDirections.actionNavChuyenkhoaToItemChuyenKhoaFragment(ItemsViewModel)
                R.id.nav_home-> HomeFragmentDirections.actionNavHomeToItemChuyenKhoaFragment(ItemsViewModel)
                else -> null
            }

            // Thực hiện điều hướng nếu hành động không phải null
            action?.let {
                holder.itemView.findNavController().navigate(it)
            }
        }

    }

    fun setData(chuyenKhoa: List<ChuyenKhoa>) {
        this.chuyenkhoaList = chuyenKhoa
        notifyDataSetChanged()
    }
}
