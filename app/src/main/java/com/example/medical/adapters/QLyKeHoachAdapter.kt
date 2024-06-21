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


import com.example.medical.entity.KeHoach
import com.example.medical.entity.Status
import java.util.ArrayList


class QLyKeHoachAdapter:  RecyclerView.Adapter<QLyKeHoachAdapter.MyViewHolder>() {

    private var kehoachList = emptyList<KeHoach>()
    private var bacsiList = emptyList<BacSi>()
    private var statusList = emptyList<Status>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val bacsi: TextView = itemView.findViewById(R.id.nameBS)
        val date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
        val status: TextView = itemView.findViewById(R.id.status)
        val item: CardView = itemView.findViewById(R.id.item_Layout3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_kehoach, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return kehoachList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val kehoach = kehoachList[position]
        val status = statusList[position]
        val bacSi = bacsiList[position]

        holder.id.text = kehoach.id_kh.toString()
        holder.bacsi.text = bacSi.name
        holder.date.text = status.ngay
        holder.time.text = status.gio
        holder.status.text = kehoach.trangthai.toString()


        holder.item.setOnClickListener{

//            val currentDestinationId = holder.itemView.findNavController().currentDestination?.id

        }

    }

    fun setData(kehoach: List<KeHoach>, bacSi: List<BacSi>, status: List<Status>) {
        this.kehoachList = kehoach
        this.bacsiList = bacSi
        this.statusList = status
        notifyDataSetChanged()
    }
    fun updateData(){
        notifyDataSetChanged()
    }

}
