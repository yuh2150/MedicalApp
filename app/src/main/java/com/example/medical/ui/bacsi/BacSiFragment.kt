package com.example.medical.ui.bacsi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.R
import com.example.medical.adapters.BacSiAdapter
import com.example.medical.databinding.FragmentBacSiBinding
import com.example.medical.entity.BacSi
import com.example.medical.viewmodel.BacSiViewModel

class BacSiFragment : Fragment() {

    private lateinit var mBacSiViewModel: BacSiViewModel

    private var _binding: FragmentBacSiBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBacSiBinding.inflate(inflater, container, false)
        val view = binding.root

        mBacSiViewModel = ViewModelProvider(this).get(BacSiViewModel::class.java)

        val recyclerView : RecyclerView = binding.recycleView
        val adapter = BacSiAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mBacSiViewModel.readAllData.observe(viewLifecycleOwner, Observer {bacsi ->
            adapter.setData(bacsi)
        })


        return view
    }

//    private fun insertData() {
//        val bacsi = BacSi(0,1,2,"qưeqweqweqweqwe","ddd","dd","dsd","","","","","",2000 )
//        mBacSiViewModel.addBacSi(bacsi)
//    }


}