package com.rajnish.sharedanimationdemo

import com.rajnish.sharedanimationdemo.model.PopularItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIEndpoints {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query(value = "page")
        page : Int = 1
    ) : Call<com.rajnish.sharedanimationdemo.model.Response>
}