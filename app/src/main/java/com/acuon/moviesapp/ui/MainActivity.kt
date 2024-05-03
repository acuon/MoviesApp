package com.acuon.moviesapp.ui

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.acuon.moviesapp.R
import com.acuon.moviesapp.common.BaseActivity
import com.acuon.moviesapp.common.BundleKeys
import com.acuon.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId() = R.layout.activity_main
    private lateinit var navController: NavController

    override fun setupViews() {
        navController = findNavController(this, R.id.nav_host_fragment_container)
        when (pref.currentScreen) {
            Screens.FAVORITES.value -> {
                navController.navigate(R.id.action_homeFragment_to_favoriteFragment)
            }

            Screens.MOVIE_DETAIL.value -> {
                navController.navigate(
                    R.id.action_homeFragment_to_movieDetailFragment,
                    bundleOf(BundleKeys.MOVIE_ID to pref.lastMovieId)
                )
            }

            else -> {}
        }
    }

    override fun onViewClicked(view: View?) {}

    override fun bindViewModel() {}
}