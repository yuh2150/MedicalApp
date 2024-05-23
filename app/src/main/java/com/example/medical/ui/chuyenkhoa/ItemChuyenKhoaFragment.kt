package com.example.medical.ui.chuyenkhoa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medical.adapters.BacSiAdapter
import com.example.medical.databinding.FragmentItemChuyenKhoaBinding
import com.example.medical.entity.BacSi
import com.example.medical.viewmodel.BacSiViewModel
import com.example.medical.viewmodel.ChuyenKhoaViewModel


class ItemChuyenKhoaFragment : Fragment() {
//    private
        private val args by navArgs<ItemChuyenKhoaFragmentArgs>()
    private lateinit var mBacSiViewModel: BacSiViewModel
    private lateinit var mChuyenKhoaViewModel: ChuyenKhoaViewModel
    private val data_bs = ArrayList<BacSi>()
    private var _binding: FragmentItemChuyenKhoaBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemChuyenKhoaBinding.inflate(inflater, container, false)
        val view = binding.root
        mChuyenKhoaViewModel = ViewModelProvider(this).get(ChuyenKhoaViewModel::class.java)
//        binding.txtTitle.setText(args.c)
        binding.textTitle.setText(args.currentChuyenKhoa.name)
        binding.textMota.setText(args.currentChuyenKhoa.mota)

        val adapter_bs = BacSiAdapter()

        args.currentChuyenKhoa.id_ck?.let {
            mChuyenKhoaViewModel.getBacSiByIdCk(it).observe(viewLifecycleOwner, Observer { bacsi ->
    //            Log.d("bacsiFragment", "Received data from ViewModel: $bacsi")
                adapter_bs.setData(bacsi)

    //            Log.d("TAG", "Dữ liệu từ ViewModel: $bacsi")
            })
        }
        val recyclerViewbs =  binding.recycleViewBs
            recyclerViewbs.adapter = adapter_bs
        recyclerViewbs.layoutManager = LinearLayoutManager(requireContext())



        return view

    }


}