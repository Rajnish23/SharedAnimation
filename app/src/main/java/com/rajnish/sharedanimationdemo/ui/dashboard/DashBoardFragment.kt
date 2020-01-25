package com.rajnish.sharedanimationdemo.ui.dashboard

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

import com.rajnish.sharedanimationdemo.R
import com.rajnish.sharedanimationdemo.databinding.DashBoardFragmentBinding
import com.rajnish.sharedanimationdemo.ui.dashboard.adapter.PopularMovieAdapter

class DashBoardFragment : Fragment() {

    companion object {
        fun newInstance() = DashBoardFragment()
    }

    private lateinit var adapter: PopularMovieAdapter
    private lateinit var viewModel: DashBoardViewModel
    private lateinit var mBinding : DashBoardFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DashBoardFragmentBinding.inflate(inflater, container, false)

        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)


        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DashBoardViewModel::class.java)
        viewModel.getPopularMovieList()

        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PopularMovieAdapter{item, posterView, releaseDateView, ratingView ->
            val action = DashBoardFragmentDirections.actionDashBoardFragmentToDetailFragment(item)

                findNavController()
                    .navigate(action,
                        FragmentNavigator.Extras.Builder()
                            .addSharedElements(
                                mapOf(posterView to posterView.transitionName,
                                    releaseDateView to releaseDateView.transitionName,
                                    ratingView to ratingView.transitionName)
                            ).build()
                    )
        }

        mBinding.popularRecyclerview.adapter = adapter
        postponeEnterTransition()
        mBinding.popularRecyclerview.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initViewModel() {
        viewModel._popularItem.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}
