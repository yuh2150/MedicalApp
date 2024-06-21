package com.example.medical.ui.canhan

import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.medical.MainActivity
import com.example.medical.R
import com.example.medical.databinding.FragmentLoginBinding
import com.example.medical.databinding.FragmentSignupBinding
import com.example.medical.entity.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?= null
    private val binding get() = _binding!!
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        val activity = activity as? MainActivity
        binding.buttonLogin.setOnClickListener {
            val userName = binding.inputUserName.text.toString()
//            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
//            val confirmPassord = binding.inputConfirmpassword.text.toString()
            Log.d("dd2","dddd:$userName $password")
            loginUser(userName,password)
        }

        return view
    }
    private fun loginUser(userName: String, password: String){
        databaseReference.orderByChild("username").equalTo(userName).addListenerForSingleValueEvent(object  :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    Log.d("dd4","dddd:$userName $password")
                    for (userSnapshot in snapshot.children){

                        val userData = userSnapshot.getValue(User::class.java)
                        if(userData!=null && userData.password == password){
                            val activity = activity as? MainActivity

                            if (activity != null) {
                                activity.user = userData
                                activity.isLoggin_ = true
                            }
                            Toast.makeText(requireContext(),"Ok",Toast.LENGTH_LONG).show()
//                            openFragment(CaNhanFragment())
                            findNavController().navigate(R.id.nav_user)
                        }
                    }

                }
                else {
                    Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Signup $error", Toast.LENGTH_SHORT).show()
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
    private fun updateUserLoginStatus(userId: String, isLoggedIn: Boolean) {
        val userLoginRef = firebaseDatabase.reference.child("users").child(userId)
        userLoginRef.setValue(isLoggedIn)
    }
}