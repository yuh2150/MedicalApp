package com.example.medical.ui.canhan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medical.MainActivity

import com.example.medical.R
import com.example.medical.databinding.FragmentCaNhanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CaNhanFragment : Fragment() {

    private var _binding: FragmentCaNhanBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCaNhanBinding.inflate(inflater, container, false)
        val view = binding.root

        val activity = activity as? MainActivity

        if (activity != null) {
            Log.d("ss", activity.isLoggin_.toString())
            if (activity.isLoggin_ == false) {
                findNavController().navigate(R.id.startFragment)
            }
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}