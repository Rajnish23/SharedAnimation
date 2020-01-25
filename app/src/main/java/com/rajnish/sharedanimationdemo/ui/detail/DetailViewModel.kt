package com.rajnish.sharedanimationdemo.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.rajnish.sharedanimationdemo.model.PopularItem

class DetailViewModel : ViewModel() {
    val popularItem = ObservableField<PopularItem>()
}
