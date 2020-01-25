package com.rajnish.sharedanimationdemo

import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.api.load

fun Fragment.waitForTansition(targetView : View){
    postponeEnterTransition()
    targetView.doOnPreDraw {
        startPostponedEnterTransition()
    }
}

@BindingAdapter("app:loadPoster")
fun loadPoster(view : ImageView, url : String?){
    val drawable = CircularProgressDrawable(view.context)
    drawable.strokeWidth = 5f
    drawable.centerRadius = 30f
    drawable.start()
    view.load(Constants.IMAGE_URL+url){
        placeholder(drawable)
    }
}