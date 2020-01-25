package com.rajnish.sharedanimationdemo.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajnish.sharedanimationdemo.APIEndpoints
import com.rajnish.sharedanimationdemo.APIService
import com.rajnish.sharedanimationdemo.model.PopularItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashBoardViewModel : ViewModel() {

    private val popularItem  = MutableLiveData<List<PopularItem>>()
    val _popularItem : LiveData<List<PopularItem>>
        get() = popularItem

    private var apiEndpoints : APIEndpoints

    init {
        apiEndpoints = APIService.getApiService()
    }

    fun getPopularMovieList(){
        apiEndpoints.getPopularMovies()
            .enqueue(object : Callback<com.rajnish.sharedanimationdemo.model.Response>{

                override fun onFailure(
                    call: Call<com.rajnish.sharedanimationdemo.model.Response>,
                    t: Throwable
                ) {
                    Log.d("TAG","Error : ${t.message} ");
                }

                override fun onResponse(
                    call: Call<com.rajnish.sharedanimationdemo.model.Response>,
                    response: Response<com.rajnish.sharedanimationdemo.model.Response>
                ) {
                    val popularList = response.body()?.results!!
                    popularItem.value = popularList
                }

            })
    }
}
