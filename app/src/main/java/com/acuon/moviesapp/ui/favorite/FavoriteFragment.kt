package com.acuon.moviesapp.ui.favorite

import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acuon.moviesapp.R
import com.acuon.moviesapp.common.BaseFragment
import com.acuon.moviesapp.common.BundleKeys
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.databinding.FragmentFavoriteBinding
import com.acuon.moviesapp.ui.Screens
import com.acuon.moviesapp.ui.favorite.adapter.FavoriteMoviesAdapter
import com.acuon.moviesapp.ui.favorite.viewmodel.FavoritesViewModel
import com.acuon.moviesapp.utils.extensions.addDecoration
import com.acuon.moviesapp.utils.extensions.createGridDecorator
import com.acuon.moviesapp.utils.extensions.dp
import com.acuon.moviesapp.utils.extensions.executeWithAction
import com.acuon.moviesapp.utils.extensions.gridView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite
    private val viewModel: FavoritesViewModel by activityViewModels()

    @Inject
    lateinit var moviesAdapter: FavoriteMoviesAdapter

    override fun onResume() {
        super.onResume()
        pref.currentScreen = Screens.FAVORITES.value
        viewModel.getFavoriteMovies()
    }

    override fun setupViews() {
        binding.apply {
            header.apply {
                tvHeading.text = getString(R.string.my_favorites)
                ivLeftIcon.setImageResource(R.drawable.ic_back_arrow)
                ivLeftIcon.setOnClickListener(clickListener)
            }
            rcvMovies.apply {
                gridView(context, 2)
                this.adapter = moviesAdapter
                addDecoration(createGridDecorator(8.dp, 8.dp, 24.dp, 24.dp, 2, true))
            }
        }
        setupListeners()
    }

    private fun setupListeners() {
        moviesAdapter.setOnFavoriteClickListener { _, movie ->
            viewModel.removeFromFavorite(movie!!)
            runDelayed(200) {
                viewModel.getFavoriteMovies()
            }
        }
        moviesAdapter.setOnMovieClickListener { _, movie ->
            findNavController().navigate(
                R.id.action_favoriteFragment_to_movieDetailFragment,
                bundleOf(
                    BundleKeys.MOVIE_ID to movie?.trackId,
                    BundleKeys.FAVORITE_MOVIE_KEY to true
                )
            )
        }
        binding.btnRetry.setOnClickListener(clickListener)
    }

    override fun bindViewModel() {
        viewModel.moviesListState bindTo {
            if (it is ResultOf.Success) {
                moviesAdapter.list = it.data
            }
            binding.executeWithAction { uiState = FavoriteMoviesUIState(it) }
        }
    }

    override fun onViewClicked(view: View?) {
        when (view) {
            binding.header.ivLeftIcon -> findNavController().popBackStack()
            binding.btnRetry -> viewModel.getFavoriteMovies()
        }
    }
}