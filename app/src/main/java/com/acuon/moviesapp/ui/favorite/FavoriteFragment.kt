package com.acuon.moviesapp.ui.favorite

import android.view.View
import com.acuon.moviesapp.R
import com.acuon.moviesapp.common.BaseFragment
import com.acuon.moviesapp.databinding.FragmentFavoriteBinding
import com.acuon.moviesapp.ui.Screens

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite

    override fun onResume() {
        super.onResume()
        pref.currentScreen = Screens.FAVORITES.value
    }

    override fun setupViews() {

    }

    override fun bindViewModel() {

    }

    override fun onViewClicked(view: View?) {

    }
}