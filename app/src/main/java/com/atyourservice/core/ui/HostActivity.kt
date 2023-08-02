package com.atyourservice.core.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.atyourservice.R
import com.example.atyourservice.databinding.ActivityHostBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    private val viewModel: HostActivityViewModel by viewModel()

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setupPreDrawListener()

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

    private fun setupPreDrawListener() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check whether the initial data is ready.
                    return if (isReady) {
                        // The content is ready. Start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content isn't ready. Suspend.
                        if (viewModel.getVMState()) {
                            isReady = true
                        }
                        false
                    }
                }
            }
        )
    }
}