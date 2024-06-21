package com.example.medical.ui.kehoach

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.adapters.QLyKeHoachAdapter
import com.example.medical.databinding.FragmentAddKeHoachBinding
import com.example.medical.entity.BacSi
import com.example.medical.entity.KeHoach
import com.example.medical.entity.Status
import com.example.medical.viewmodel.KeHoachViewModel


class AddKeHoachFragment : Fragment() {
    private lateinit var mKeHoachViewModel: KeHoachViewModel
    private var _binding: FragmentAddKeHoachBinding?= null
    private val binding get() = _binding!!
    private var bacsi = ArrayList<String>()
    private var bacsiList = ArrayList<BacSi>()
    private var status =  ArrayList<String>()
    private var statusList =  ArrayList<Status>()

    private var keHoach = KeHoach(0,0,0,0)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddKeHoachBinding.inflate(inflater, container, false)
        val view = binding.root
        mKeHoachViewModel = ViewModelProvider(this).get(KeHoachViewModel::class.java)

        val adapterBacsi = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, bacsi)

        adapterBacsi.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        binding.spinnerBacsi.adapter = adapterBacsi
        mKeHoachViewModel.readAllDataBacSi.observe(viewLifecycleOwner, Observer {item->
            item.forEach{bs -> bs.name?.let { bacsi.add(it) } }
            bacsiList.addAll(item)
            adapterBacsi.notifyDataSetChanged()
        })

        val adapterStatus = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, status)

        adapterStatus.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        binding.spinnerTime.adapter = adapterStatus
        mKeHoachViewModel.readAllDataStatus.observe(viewLifecycleOwner, Observer {item->
            item.forEach{ s -> status.add(s.ngay+ " " + s.gio)

             }
            statusList.addAll(item)
            adapterStatus.notifyDataSetChanged()
        })

        binding.spinnerBacsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedId = bacsiList[position].id_bs
                keHoach.id_bs = selectedId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        binding.spinnerTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedId = statusList[position].id_status
                keHoach.id_status = selectedId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val adapter = QLyKeHoachAdapter()

        binding.addkehoach.setOnClickListener{
            mKeHoachViewModel.addKeHoach(keHoach)
            adapter.updateData()
            findNavController().navigate(R.id.QLyKeHoachFragment)

        }


        return view
    }

}