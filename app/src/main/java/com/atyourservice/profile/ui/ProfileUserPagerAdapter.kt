package com.atyourservice.profile.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileUserPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> PersonalFragment.newInstance()
            1 -> ExpertFragment.newInstance()

            else -> CompanyFragment.newInstance()
        }

    }

}