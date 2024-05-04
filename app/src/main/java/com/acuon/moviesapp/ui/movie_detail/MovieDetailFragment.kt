package com.acuon.moviesapp.ui.movie_detail

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acuon.moviesapp.R
import com.acuon.moviesapp.common.BaseFragment
import com.acuon.moviesapp.common.BundleKeys
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.databinding.FragmentMovieDetailBinding
import com.acuon.moviesapp.ui.Screens
import com.acuon.moviesapp.ui.movie_detail.viewmodel.MovieDetailViewModel
import com.acuon.moviesapp.utils.extensions.executeWithAction
import com.acuon.moviesapp.utils.extensions.gone
import com.acuon.moviesapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    override fun getLayoutId() = R.layout.fragment_movie_detail

    private val viewModel: MovieDetailViewModel by activityViewModels()

    private val trackId by lazy { arguments?.getLong(BundleKeys.MOVIE_ID) }
    private val fromFavorite by lazy { arguments?.getBoolean(BundleKeys.FAVORITE_MOVIE_KEY) }

    override fun onResume() {
        super.onResume()
        pref.currentScreen = Screens.MOVIE_DETAIL.value
    }

    override fun onPause() {
        super.onPause()
        pref.lastMovieId = trackId ?: 0
    }

    override fun setupViews() {
        viewModel.getMovieById(trackId ?: pref.lastMovieId, fromFavorite == true)
        binding.apply {
            header.apply {
                ivLeftIcon.setImageResource(R.drawable.ic_back_arrow)
                ivLeftIcon.setOnClickListener(clickListener)
            }
            rgOptions.setOnCheckedChangeListener { _, id ->
                when (id) {
                    rbDescription.id -> handleOptions(false)
                    rbTrailer.id -> handleOptions(true)
                }
            }
            layoutFavorite.setOnClickListener(clickListener)
        }
    }

    private fun handleOptions(webView: Boolean) {
        binding.apply {
            if (webView) {
                webViewTrailer.show()
                tvLongDesc.gone()
                loadWebViewWithUrl()
            } else {
                webViewTrailer.gone()
                tvLongDesc.show()
                webViewTrailer.stopLoading()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebViewWithUrl() {
        binding.apply {
            webViewTrailer.settings.apply {
                javaScriptEnabled = true
                builtInZoomControls = true
                displayZoomControls = false
                setSupportZoom(true)
            }

            webViewTrailer.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url!!)
                    return false
                }
            }
            viewModel.trailerUrl.get()?.let { webViewTrailer.loadUrl(it) }
        }
    }

    override fun bindViewModel() {
        viewModel.movieItem.observe(viewLifecycleOwner) {
            if (it is ResultOf.Success) {
                viewModel.isFavorite.set(it.data?.isFavorite == true)
                viewModel.trailerUrl.set(it.data?.trailerUrl)
                viewModel.movie.set(it.data)
                val imageRes = viewModel.isFavorite.get()
                    .let { fav -> if (fav == true) R.drawable.ic_favorite_filled else R.drawable.ic_favorite }
                binding.ivFavorite.setImageResource(imageRes)
            }
            binding.executeWithAction {
                movieUiState = MovieDetailUIState(it)
            }

        }
    }

    override fun onViewClicked(view: View?) {
        when (view) {
            binding.layoutFavorite -> {
                if (viewModel.isFavorite.get() == true) {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                    viewModel.movie.get()?.let { viewModel.updateFavoriteStatus(it, false) }
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_filled)
                    viewModel.movie.get()?.let { viewModel.updateFavoriteStatus(it, true) }
                }
            }

            binding.header.ivLeftIcon -> {
                findNavController().popBackStack()
            }
        }
    }
}