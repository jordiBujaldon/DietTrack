package com.example.pis2020.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pis2020.R
import com.example.pis2020.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_DietTrack)

        super.onCreate(savedInstanceState)
        // Mostramos en pantalla el layout activity_main.xml
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        val navController = findNavController(R.id.navHostFragment)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.mainFragment || destination.id == R.id.perfilFragment) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> NavigationUI.onNavDestinationSelected(item, navController )
                R.id.perfilFragment -> NavigationUI.onNavDestinationSelected(item, navController)
                else -> false
            }
        }

    }
}
