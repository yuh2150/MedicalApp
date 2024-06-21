package com.example.medical.ui.bacsi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.adapters.LichAdapter
import com.example.medical.databinding.FragmentItemBacSiBinding
import com.example.medical.viewmodel.BacSiViewModel


class ItemBacSiFragment : Fragment() {

    private val args by navArgs<ItemBacSiFragmentArgs>()

    private var _binding: FragmentItemBacSiBinding?= null
    private val binding get() = _binding!!
    private lateinit var mBacSiViewModel: BacSiViewModel

    private var ngay = ArrayList<String>()
    private var keHoachAdapter = LichAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemBacSiBinding.inflate(inflater, container, false)
        val view = binding.root
        mBacSiViewModel = ViewModelProvider(this).get(BacSiViewModel::class.java)

        binding.txtTitle.setText(args.currentBacSi.name)

        val resourceName = args.currentBacSi.hinhanh +  "_foreground"
        val resourceId = context?.resources?.getIdentifier(resourceName, "mipmap", context!!.packageName)
        if (resourceId != null) {
            binding.imageView.setImageResource(resourceId)
        }
        binding.txtChucvu.setText(args.currentBacSi.chucvu)
        binding.txtLamviec.setText(args.currentBacSi.lamviec)

        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, ngay)

        adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        mBacSiViewModel.getNgayListByIdBs(args.currentBacSi.id_bs).observe(viewLifecycleOwner, Observer {item->
//            ngay.addAll(item)
            ngay.addAll(item.filterNot { ngay.contains(it) })
            adapter.notifyDataSetChanged()
        })
        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.adapter = keHoachAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                if (view != null) {
                    mBacSiViewModel.getGioListByIdBsAndNgay(args.currentBacSi.id_bs,ngay[position].toString())
                        .observe(viewLifecycleOwner, Observer {item->
                            keHoachAdapter.setDataGio(item)
                    })}else {
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }




        return view
    }


}