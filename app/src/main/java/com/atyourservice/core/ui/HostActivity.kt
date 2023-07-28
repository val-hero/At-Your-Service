package com.atyourservice.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.atyourservice.R
import com.example.atyourservice.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding

    //Код для отображения Main_Search
//    private lateinit var bindingExample: FragmentSearchHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //для отображения Main_Search и фрагментов
//        bindingExample = FragmentSearchHostBinding.inflate(layoutInflater)
//        setContentView(bindingExample.root)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_container_view, SearchEmptyInputFragment())
//            .commit()

        val navHost =
            supportFragmentManager.findFragmentById(R.id.host_fragment_container) as NavHostFragment
        val navController = navHost.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationVisibility(destination.id)
        }
    }

    private fun bottomNavigationVisibility(screenId: Int) {
        binding.bottomNavigation.isVisible = when (screenId) {
            // сюда добавляйте id экранов, на которых нужно скрыть нижнюю навигацию

            R.id.auth_fragment,
            R.id.registration_fragment -> { false }

            else -> true

        }
    }
}