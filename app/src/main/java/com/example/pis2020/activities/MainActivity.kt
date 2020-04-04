package com.example.pis2020.activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pis2020.R
import com.example.pis2020.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mostramos en pantalla el layout activity_main.xml
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        // Controlador de la navegacion
        val navController = findNavController(R.id.navHostFragment)

        // Muestra el menu de navegacion si el destino esta en un fragment que lo permita
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.mainFragment
                || destination.id == R.id.perfilFragment
                || destination.id == R.id.alimentosFragment
                || destination.id == R.id.puntuacionFragment
                || destination.id == R.id.dietasFragment) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }

        // Cada vez que el usuario cambie de navegacion con el menu, cambiamos de fragment
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> NavigationUI.onNavDestinationSelected(item, navController)
                R.id.perfilFragment -> NavigationUI.onNavDestinationSelected(item, navController)
                R.id.alimentosFragment -> NavigationUI.onNavDestinationSelected(item, navController)
                R.id.puntuacionFragment -> NavigationUI.onNavDestinationSelected(item, navController)
                R.id.dietasFragment -> NavigationUI.onNavDestinationSelected(item, navController)
                else -> false
            }
        }

    }
}
