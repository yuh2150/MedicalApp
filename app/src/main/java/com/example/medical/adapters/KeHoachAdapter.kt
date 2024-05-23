package com.example.medical.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.entity.KeHoach
import com.example.medical.entity.KeHoachWithStatus
import com.example.medical.ui.bacsi.ItemBacSiFragmentDirections

class KeHoachAdapter : RecyclerView.Adapter<KeHoachAdapter.MyViewHolder>() {

    private var kehoachList = emptyList<KeHoach>()
    private var gioList = emptyList<KeHoachWithStatus>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val lich : TextView = itemView.findViewById(R.id.text_lich)
        val item: CardView = itemView.findViewById(R.id.item_Lich)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_lich, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = gioList[position]
        Log.d("gio",currentItem.gio)
        holder.lich.text = currentItem.gio

        holder.item.setOnClickListener {
            val action = currentItem.keHoach?.let { it1 ->
                ItemBacSiFragmentDirections.actionItemBacSiFragmentToKeHoachFragment(
                    it1
                )
            }
            if (action != null) {
                holder.itemView.findNavController().navigate(action)
            }
        }

    }

    override fun getItemCount(): Int {
        return gioList.size
    }
    fun setDataGio(gio: List<KeHoachWithStatus>) {
        this.gioList = gio
        notifyDataSetChanged()
        Log.d("112",gioList.toString())
    }

}