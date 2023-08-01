package com.atyourservice.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.atyourservice.R
import com.example.atyourservice.databinding.ActivityHostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    private val viewModel: HostActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.host_fragment_container) as NavHostFragment
        val navController = navHost.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationVisibility(destination.id)
        }

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile_fragment -> {
                    navController.navigate(R.id.profileUserFragment)
                    true
                }
                R.id.search_fragment -> {
                    navController.navigate(R.id.searchHostFragment)
                    true
                }
                //Добавь сюда обработку клика bottomNavigation для нового фрагмента:)
                else -> false
            }
        }

        viewModel.getAuthState().observe(this) { isSignedIn ->
            if (isSignedIn)
                navController.navigate(R.id.searchHostFragment)
            else
                navController.navigate(R.id.authFragment)
        }
    }

    private fun bottomNavigationVisibility(screenId: Int) {
        binding.bottomNavigation.isVisible = when (screenId) {
            // сюда добавляйте id экранов, на которых нужно скрыть нижнюю навигацию

            R.id.authFragment,
            R.id.registrationFragment -> {
                false
            }

            else -> true

        }
    }
}