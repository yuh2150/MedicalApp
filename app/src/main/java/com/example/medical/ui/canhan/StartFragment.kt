package com.example.medical.ui.canhan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medical.MainActivity

import com.example.medical.R
import com.example.medical.databinding.FragmentCaNhanBinding
import com.example.medical.databinding.FragmentStartBinding


class StartFragment: Fragment() {

    private var _binding: FragmentStartBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.signupFragment)
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}