package com.example.medical.ui.canhan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentSignupBinding
import com.example.medical.entity.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding?= null
    private val binding get() = _binding!!
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")


        binding.buttonSignup.setOnClickListener {
            val userName = binding.inputUserName.text.toString()
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            val confirmPassord = binding.inputConfirmpassword.text.toString()

            signupUser(userName,password,email)
            Log.d("dd1sss","dddd")
        }

        return view
    }

    private fun signupUser(userName: String, password: String,email: String){
        Log.d("dd1","dddd")
        databaseReference.orderByChild("username").equalTo(userName).addListenerForSingleValueEvent(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("dd2","dddd")
//                Log.d("dd2","dddd")
                Toast.makeText(requireContext(),"Signup Ok",Toast.LENGTH_SHORT).show()
                if(!snapshot.exists()){
                    val id = databaseReference.push().key
//                    val userId = id?.toInt()
                    val userData = User(id, userName ,password,email,"","",2)
                    databaseReference.child(id!!.toString()).setValue(userData)
                    Toast.makeText(requireContext(),"Signup Ok",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.loginFragment)
//                    startActivity(Intent(requireContext(),LoginFragment::class.java))
//                    fi
//                    openFragment(LoginFragment())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Signup $error",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun openFragment (fragment : Fragment){
        val fragmentmanager = requireActivity().supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }



}