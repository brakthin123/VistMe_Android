package kh.edu.rupp.fe.visitme.model.api.client

import com.chuckerteam.chucker.api.ChuckerInterceptor
import kh.edu.rupp.fe.visitme.BuildConfig
import kh.edu.rupp.fe.visitme.core.AppCore
import kh.edu.rupp.fe.visitme.model.api.service.ApiService2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    private val chuckerInterceptor = ChuckerInterceptor(AppCore.get().applicationContext)
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val httpClient = Retrofit.Builder()
        .baseUrl(BuildConfig.apiUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService = httpClient.create(ApiService2::class.java)

    companion object {

        private var instance: ApiClient? = null

        fun get(): ApiClient {
            if (instance == null) {
                instance = ApiClient()
            }

            return instance!!
        }

    }

}