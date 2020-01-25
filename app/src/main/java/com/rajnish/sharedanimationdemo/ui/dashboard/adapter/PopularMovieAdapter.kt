package com.rajnish.sharedanimationdemo.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rajnish.sharedanimationdemo.databinding.PopularItemLayoutBinding
import com.rajnish.sharedanimationdemo.model.PopularItem

class PopularMovieAdapter(private val cllback: (item : PopularItem, posterView : View, releaseDateView : View, ratingView : View )-> Unit)
    : ListAdapter<PopularItem, PopularMovieHolder>(object : DiffUtil.ItemCallback<PopularItem>(){
    override fun areItemsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
        return oldItem.id == newItem.id
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieHolder {
        val view = PopularItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMovieHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMovieHolder, position: Int) {
        val item = getItem(position)
        holder.view.rootLayout.setOnClickListener {
            cllback.invoke(item,
                holder.view.posterPathIv,
                holder.view.releaseDateTv,
                holder.view.movieRating)
        }
        holder.bind(item)
    }
}

class PopularMovieHolder(val view : PopularItemLayoutBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(item: PopularItem) {
        view.popularItem = item
        view.executePendingBindings()
    }

}
