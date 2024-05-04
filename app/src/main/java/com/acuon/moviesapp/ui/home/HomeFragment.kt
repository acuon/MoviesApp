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
            header.apply {
                tvHeading.text = getString(R.string.app_name)
                ivRightIcon.setImageResource(R.drawable.ic_favorite)
                ivRightIcon.setOnClickListener(clickListener)
            }
            rcvMovies.apply {
                gridView(context, 2)
                this.adapter = moviesAdapter
                addDecoration(createGridDecorator(8.dp, 8.dp, 24.dp, 24.dp, 2, true))
            }
        }
        setupListeners()
    }

    private var previousQuery: String? = null

    private fun setupListeners() {
        moviesAdapter.setOnMovieClickListener { _, movie ->
            findNavController().navigate(
                R.id.action_homeFragment_to_movieDetailFragment,
                bundleOf(
                    BundleKeys.MOVIE_ID to movie?.trackId,
                    BundleKeys.FAVORITE_MOVIE_KEY to false
                )
            )
        }
        moviesAdapter.setOnFavoriteClickListener { _, movie ->
            movie?.let {
                viewModel.updateFavoriteStatus(it, it.isFavorite)
            }
        }
        binding.apply {
            etSearch.addTextChangedListener {
                val query = it.toString()
                if (query != previousQuery) {
                    runDelayed(100) {
                        viewModel.searchMovies(query)
                    }
                    previousQuery = query
                }
            }
            btnRetry.setOnClickListener(clickListener)
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
        when (view) {
            binding.header.ivRightIcon -> {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }

            binding.btnRetry -> {
                viewModel.searchMovies(previousQuery.toString())
            }
        }
    }
}