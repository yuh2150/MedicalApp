package com.example.medical.ui.kehoach

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.adapters.QLLichHenAdapter
import com.example.medical.adapters.QLyKeHoachAdapter
import com.example.medical.databinding.FragmentAddKeHoachBinding
import com.example.medical.databinding.FragmentQlyKeHoachBinding
import com.example.medical.entity.BacSi
import com.example.medical.entity.BenhNhan
import com.example.medical.entity.LichHen
import com.example.medical.entity.Status
import com.example.medical.viewmodel.KeHoachViewModel
import com.example.medical.viewmodel.LichHenViewModel
import java.util.ArrayList


class QLyKeHoachFragment : Fragment() {

    private lateinit var mKeHoachViewModel: KeHoachViewModel
    private var _binding: FragmentQlyKeHoachBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQlyKeHoachBinding.inflate(inflater, container, false)
        val view = binding.root
        mKeHoachViewModel = ViewModelProvider(this).get(KeHoachViewModel::class.java)
        binding.addKeHoach.setOnClickListener{
            findNavController().navigate(R.id.addKeHoachFragment)
        }
        binding.addStatus.setOnClickListener{
            findNavController().navigate(R.id.addStatusFragment)
        }

        val recyclerView : RecyclerView = binding.recycleViewKeHoach
        val adapter = QLyKeHoachAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        var bacsi = ArrayList<BacSi>()
        var status = ArrayList<Status>()
        mKeHoachViewModel.readAllData.observe(viewLifecycleOwner, Observer { kehoachList ->

            kehoachList?.forEach { kehoach ->
                kehoach.id_kh?.let { idKh ->
                    mKeHoachViewModel.getBacSiByIdKh(idKh).observe(viewLifecycleOwner, Observer { bs->
                        bacsi.add(bs)
                    })
                }}

            kehoachList?.forEach { kehoach ->
                kehoach.id_kh?.let { idKh ->
                    mKeHoachViewModel.getStatusByIdKh(idKh)
                        .observe(viewLifecycleOwner, Observer { s->
                            status.add(s)
                            if(bacsi.size ==  kehoachList.size && status.size ==  kehoachList.size ){
                                adapter.setData(kehoachList,bacsi,status)
                            }


                        })
                }}

            if(bacsi.size ==  kehoachList.size && status.size ==  kehoachList.size ){
                Log.d("sss1_", bacsi.size.toString())
                Log.d("sss2_", status.size.toString())
                adapter.setData(kehoachList,bacsi,status)
            }

        })
        return view
    }
}