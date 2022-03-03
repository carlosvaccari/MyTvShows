package com.cvaccari.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cvaccari.commons.delegate.viewBinding
import com.cvaccari.features.databinding.ActivityMainBinding
import com.cvaccari.features.favorities.di.FavoritesModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment_activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(FavoritesModule.instance)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavController()
        binding.imageviewBack.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun setupNavController() {
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navView.isVisible = isMainFeature(destination)
            showBackButton(!isMainFeature(destination))
        }
    }

    private fun isMainFeature(destination: NavDestination) =
        (destination.id == R.id.navigation_home ||
                destination.id == R.id.navigation_dashboard ||
                destination.id == R.id.navigation_notifications)

    fun showBackButton(show: Boolean) {
        if (show) {
            binding.imageviewBack.isVisible = true
            binding.textviewTitle.isVisible = false
        } else {
            binding.imageviewBack.isVisible = false
            binding.textviewTitle.isVisible = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(FavoritesModule.instance)
    }

}