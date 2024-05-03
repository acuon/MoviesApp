package com.acuon.moviesapp.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.acuon.moviesapp.utils.extensions.hideSoftKeyboard
import com.acuon.moviesapp.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private var mActivity: BaseActivity<*>? = null
    private var mRootView: View? = null

    protected lateinit var binding: T

    // layout reference
    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = binding.root
        return mRootView
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.executePendingBindings()
        setupViews()
        bindViewModel()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }

    fun getViewDataBinding(): T {
        return binding
    }

    fun hideKeyboard() {
        mActivity?.hideSoftKeyboard()
    }

    protected inline infix fun <T> Flow<T>.bindTo(crossinline action: (T) -> Unit) {
        with(viewLifecycleOwner) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collect {
                        action(it)
                    }
                }
            }
        }
    }

    fun showToast(str: String) {
        mActivity?.showToast(str)
    }

    fun runDelayed(delayMilliSec: Long, job: suspend () -> Unit) =
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(delayMilliSec)
                withContext(Dispatchers.Main) {
                    job.invoke()
                }
            }
        }

    protected abstract fun setupViews()
    protected abstract fun bindViewModel()
    protected abstract fun onViewClicked(view: View?)

    var clickListener =
        View.OnClickListener { view ->
            hideKeyboard()
            onViewClicked(view)
        }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

}
