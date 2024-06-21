package com.example.medical.ui.lichhen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.adapters.QLLichHenAdapter
import com.example.medical.databinding.FragmentQlyLichHenBinding
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status
import com.example.medical.viewmodel.LichHenViewModel
import java.util.ArrayList


class QLyLichHenFragment : Fragment() {
    private lateinit var mLichHenViewModel: LichHenViewModel
    private var _binding: FragmentQlyLichHenBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQlyLichHenBinding.inflate(inflater, container, false)
        val view = binding.root
        mLichHenViewModel = ViewModelProvider(this).get(LichHenViewModel::class.java)

        val recyclerView : RecyclerView = binding.recycleView
        val adapter = QLLichHenAdapter()


        var lichhen = ArrayList<LichHen>()
        var bacsi = ArrayList<BacSi>()
        var status = ArrayList<Status>()
        var benhnhan = ArrayList<BenhNhan>()
        mLichHenViewModel.readAllData.observe(viewLifecycleOwner, Observer { lichhenList ->

            lichhenList?.forEach { lichhen ->
                lichhen.id_kh?.let { idKh ->
                    mLichHenViewModel.getBacSiByIdKh(idKh).observe(viewLifecycleOwner, Observer { bs->

//                        if(bacsi.size >=  lichhenList.size) {
//                            Log.d("sss2", bacsi.toString())
//                            adapter.setData(lichhenList,benhnhan,bacsi,status)
//                        }
                        bacsi.add(bs)
                    })
                }}
            lichhenList?.forEach { lichhen ->
                lichhen.id_kh?.let { idKh ->
                    mLichHenViewModel.getStatusByIdKh(idKh)
                    .observe(viewLifecycleOwner, Observer { s->
//                        if(status.size >=  lichhenList.size) {
//                            Log.d("sss2", status.toString())
//                            adapter.setData(lichhenList,benhnhan,bacsi,status)
//                        }

                        status.add(s)

                    })
                }}
            lichhenList?.forEach { lichhen ->
                lichhen.id_bn?.let { idBn ->
                    mLichHenViewModel.getBenhNhanByIdBn(idBn)
                        .observe(viewLifecycleOwner, Observer { bn ->
                                benhnhan.add(bn)
                            if(bacsi.size ==  lichhenList.size && status.size ==  lichhenList.size && benhnhan.size ==  lichhenList.size){
                                Log.d("sss1_", bacsi.size.toString())
                                Log.d("sss2_", status.size.toString())
                                Log.d("sss3_", benhnhan.size.toString())
                                Log.d("sss1_", bacsi.toString())
                                Log.d("sss2_", status.toString())
                                Log.d("sss3_", benhnhan.toString())
                                adapter.setData(lichhenList,benhnhan,bacsi,status)
                                recyclerView.adapter = adapter
                                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            }
                        })
                }}

            if(bacsi.size ==  lichhenList.size && status.size ==  lichhenList.size && benhnhan.size ==  lichhenList.size){
                Log.d("sss1_", bacsi.size.toString())
                Log.d("sss2_", status.size.toString())
                Log.d("sss3_", benhnhan.size.toString())
                adapter.setData(lichhenList,benhnhan,bacsi,status)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }

        })



        return view
    }

}