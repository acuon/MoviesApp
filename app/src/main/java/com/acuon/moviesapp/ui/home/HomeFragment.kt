package com.acuon.moviesapp.ui.home

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acuon.moviesapp.R
import com.acuon.moviesapp.common.BaseFragment
import com.acuon.moviesapp.common.BundleKeys
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.databinding.FragmentHomeBinding
import com.acuon.moviesapp.ui.Screens
import com.acuon.moviesapp.ui.home.adapter.MoviesAdapter
import com.acuon.moviesapp.ui.home.viewmodel.HomeViewModel
import com.acuon.moviesapp.utils.extensions.addDecoration
import com.acuon.moviesapp.utils.extensions.createGridDecorator
import com.acuon.moviesapp.utils.extensions.dp
import com.acuon.moviesapp.utils.extensions.executeWithAction
import com.acuon.moviesapp.utils.extensions.gridView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: HomeViewModel by activityViewModels()
    override fun getLayoutId() = R.layout.fragment_home

    override fun onResume() {
        super.onResume()
        viewModel.getCachedResponse()
        pref.currentScreen = Screens.HOME.value
    }

    override fun setupViews() {
        binding.apply {
            rcvMovies.apply {
                gridView(context, 2)
                this.adapter = moviesAdapter
                addDecoration(createGridDecorator(8.dp, 8.dp, 24.dp, 24.dp, 2, true))
            }
        }
        setupListeners()
    }

    private fun setupListeners() {
        moviesAdapter.setOnMovieClickListener { _, movie ->
            findNavController().navigate(
                R.id.action_homeFragment_to_movieDetailFragment,
                bundleOf(BundleKeys.MOVIE_ID to movie?.trackId)
            )
            showToast("${movie?.name} clicked")
        }
        moviesAdapter.setOnFavoriteClickListener { _, movie ->
            movie?.let {
                if (it.isFavorite) {
                    viewModel.addToFavorite(it)
                } else viewModel.removeFromFavorite(it)
            }
        }
        binding.apply {
            etSearch.addTextChangedListener {
                runDelayed(100) {
                    if (it.toString().isEmpty()) {
                        viewModel.cancelSearch()
                    } else {
                        viewModel.searchMovies(it.toString())
                    }
                }
            }
        }
    }

    override fun bindViewModel() {
        viewModel.moviesListState bindTo {
            if (it is ResultOf.Success) {
                moviesAdapter.list = it.data
            }
            binding.executeWithAction { uiState = MoviesListUIState(it) }
        }
    }

    override fun onViewClicked(view: View?) {
        binding.apply {
            when (view) {

            }
        }
    }
}