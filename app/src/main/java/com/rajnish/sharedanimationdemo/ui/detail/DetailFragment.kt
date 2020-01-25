package com.rajnish.sharedanimationdemo.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.rajnish.sharedanimationdemo.R
import com.rajnish.sharedanimationdemo.databinding.DetailFragmentBinding
import com.rajnish.sharedanimationdemo.model.PopularItem

class DetailFragment : Fragment() {

    private val args : DetailFragmentArgs by navArgs()
    private lateinit var mBinding : DetailFragmentBinding
    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DetailFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        return mBinding.root
    }

    private fun initViewModel() {
        mBinding.viewmodel = viewModel
        viewModel.popularItem.set(args.popularItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        initViewModel()
        mBinding.executePendingBindings()
    }


}
