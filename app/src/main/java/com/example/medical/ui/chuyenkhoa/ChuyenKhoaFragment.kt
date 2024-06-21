package com.example.medical.ui.chuyenkhoa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medical.adapters.ChuyenKhoaAdapter
import com.example.medical.databinding.FragmentChuyenkhoaBinding
import com.example.medical.viewmodel.ChuyenKhoaViewModel
import com.example.medical.entity.ChuyenKhoa

class ChuyenKhoaFragment : Fragment() {

    private lateinit var mChuyenKhoaViewModel: ChuyenKhoaViewModel

    private var _binding: FragmentChuyenkhoaBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChuyenkhoaBinding.inflate(inflater, container, false)
        val view = binding.root

        mChuyenKhoaViewModel = ViewModelProvider(this).get(ChuyenKhoaViewModel::class.java)

//        view.text
//        binding.buttonAdd.setOnClickListener{
//            insertData()
//            Toast.makeText(requireContext(),"ok",Toast.LENGTH_LONG).show()
//
//        }
//        val dataList =

        val recyclerView :RecyclerView = binding.recycleView
        val adapter = ChuyenKhoaAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)

        mChuyenKhoaViewModel.readAllData.observe(viewLifecycleOwner, Observer {chuyenkhoa ->
//            Log.d("ChuyenKhoaFragment", "Received data from ViewModel: $chuyenkhoa")
            adapter.setData(chuyenkhoa)

//            Log.d("TAG", "Dữ liệu từ ViewModel: $chuyenkhoa")
        })


        return view
    }


}