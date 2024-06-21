package com.example.medical.ui.lichhen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.medical.R
import com.example.medical.databinding.FragmentItemQlyLichBinding
import com.example.medical.ui.chuyenkhoa.ItemChuyenKhoaFragmentArgs

import com.example.medical.viewmodel.LichHenViewModel


class ItemQLyLichFragment : Fragment() {

    private val args by navArgs<ItemQLyLichFragmentArgs>()
    private lateinit var mLichHenViewModel: LichHenViewModel
    private var _binding: FragmentItemQlyLichBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemQlyLichBinding.inflate(inflater, container, false)
        val view = binding.root
        mLichHenViewModel = ViewModelProvider(this).get(LichHenViewModel::class.java)

        args.currentLich.id_kh?.let {
            mLichHenViewModel.getBacSiByIdKh(it).observe(viewLifecycleOwner, Observer { item ->
                binding.nameBS.setText(item.name)
            })
        }

        args.currentLich.id_kh?.let {
            mLichHenViewModel.getStatusByIdKh(it).observe(viewLifecycleOwner, Observer { item ->
                binding.time.setText(item.ngay + " " + item.gio)
            })
        }

        args.currentLich.id_bn?.let {
            mLichHenViewModel.getBenhNhanByIdBn(it).observe(viewLifecycleOwner, Observer { item ->

                binding.nameBN.setText(item.name)
            })
        }
        binding.id.text = args.currentLich.id_kh.toString()
        binding.lydo.setText(args.currentLich.lydo)

        binding.button3.setOnClickListener{

            mLichHenViewModel.confirmLich(args.currentLich.id_lh)
            args.currentLich.id_kh?.let { it1 -> mLichHenViewModel.confirmKeHoach(it1) }
        }

        return view
    }


}