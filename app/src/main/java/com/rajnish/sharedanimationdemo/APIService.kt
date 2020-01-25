package com.rajnish.sharedanimationdemo

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {

    companion object{
        fun getApiService() : APIEndpoints {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getIntercepter())
                .build()
            return retrofit.create(APIEndpoints::class.java)
        }

        private fun getIntercepter(): OkHttpClient {
            val builder = OkHttpClient.Builder();
            builder.addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val url = chain.request().url()
                        .newBuilder()
                        .addQueryParameter("api_key", Constants.API_KEY)
                        .build()
                    val request = chain.request().newBuilder().url(url).build()
                    return chain.proceed(request)
                }

            })
            return builder.build()        }
    }

}