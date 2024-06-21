package com.example.medical.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan


import com.example.medical.entity.LichHen
import com.example.medical.entity.Status
import com.example.medical.ui.chuyenkhoa.ChuyenKhoaFragmentDirections
import com.example.medical.ui.home.HomeFragmentDirections
import com.example.medical.ui.lichhen.QLyLichHenFragment
import com.example.medical.ui.lichhen.QLyLichHenFragmentDirections
import java.util.ArrayList


class QLLichHenAdapter:  RecyclerView.Adapter<QLLichHenAdapter.MyViewHolder>() {

    private var lichhenList = emptyList<LichHen>()
    private var bacsiList = emptyList<BacSi>()
    private var benhnhanList = emptyList<BenhNhan>()
    private var statusList = emptyList<Status>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val bacsi: TextView = itemView.findViewById(R.id.bacsi)
        val benhnhan: TextView = itemView.findViewById(R.id.benhnhan)
        val date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
        val status: TextView = itemView.findViewById(R.id.status)
        val item: CardView = itemView.findViewById(R.id.item_Layout2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_lichhen, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
//        bacsiList.size
//        benhnhanList.size
//        statusList.size
        return lichhenList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val lichHen = lichhenList[position]
        val benhNhan = benhnhanList[position]
        val status = statusList[position]
        val bacSi = bacsiList[position]

        holder.id.text = lichHen.id_lh.toString()
        holder.bacsi.text = bacSi.name
        holder.benhnhan.text = benhNhan.name
        holder.date.text = status.ngay
        holder.time.text = status.gio
        holder.status.text = lichHen.trangthai.toString()


        holder.item.setOnClickListener{
            val currentDestinationId = holder.item.findNavController().currentDestination?.id
//            val currentDestinationId = holder.itemView.findNavController().currentDestination?.id
            val action = when (currentDestinationId) {
                R.id.QLyLichHenFragment -> QLyLichHenFragmentDirections.actionQLyLichHenFragmentToItemQLyLichFragment(lichHen)
                else -> null
            }
            action?.let {
                holder.itemView.findNavController().navigate(it)
            }

        }

    }

    fun setData(lichhen: List<LichHen>, benhNhan: List<BenhNhan>, bacSi: List<BacSi>, status: List<Status>) {
        this.lichhenList = lichhen
        this.benhnhanList = benhNhan
        this.bacsiList = bacSi
        this.statusList = status
        notifyDataSetChanged()
    }

}
