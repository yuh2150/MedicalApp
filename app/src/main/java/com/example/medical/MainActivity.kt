package com.example.medical

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.medical.databinding.ActivityMainBinding
import com.example.medical.module.DBcopy
import com.example.medical.ui.bacsi.BacSiFragment
import com.example.medical.ui.chuyenkhoa.ChuyenKhoaFragment
import com.example.medical.ui.home.HomeFragment
import com.example.medical.ui.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentmanager : FragmentManager
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    var isLoggin_ : Boolean = false

    private var db : DBcopy? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

         drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.fragmentContainer)

        db = DBcopy(this)
        db?.openDatabase()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_chuyenkhoa,R.id.nav_bacsi,R.id.nav_gioithieu,R.id.nav_lienhe,R.id.nav_user,R.id.nav_lich,R.id.nav_thongbao
            ), drawerLayout

        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_home -> {
                    Log.d("NavHostFragment", "ID của FragmentContainerView hoặc Fragment: ")
                    binding.drawerLayout.closeDrawers()
                }
                R.id.nav_lich -> {
                    Log.d("NavHostFragment", "ID của FragmentContainerView hoặc Fragment: ")
//                    val action = HomeFragmentDirections.actionNavHomeToNavChuyenkhoa()
//                    findNavController(R.id.fragmentContainer).navigate(action)
                }
            }
        }
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")


        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            checkUserData(currentUser.uid)
        } else {
            // User is not signed in, you can proceed with the login flow or any other action
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    private fun openFragment (fragment : Fragment){
        val fragmentTransaction : FragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
    private fun checkUserData(userId: String) {
        val userRef = firebaseDatabase.reference.child("users").child(userId)

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val isLoggedIn = dataSnapshot.getValue(Boolean::class.java)
                if (isLoggedIn != null && isLoggedIn) {
                    isLoggin_ = true
                } else {
                    isLoggin_ = false
//                    findNavController(R.id.nav_user).navigate(R.id.loginFragment)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }
}