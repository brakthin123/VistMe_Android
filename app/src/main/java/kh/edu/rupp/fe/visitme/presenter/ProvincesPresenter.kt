package kh.edu.rupp.fe.visitme.presenter

import android.widget.Toast
import kh.edu.rupp.fe.visitme.model.api.model.Province
import kh.edu.rupp.fe.visitme.model.api.service.ApiService
import kh.edu.rupp.fe.visitme.view.ProvincesView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvincesPresenter(private val view: ProvincesView) {

    fun loadProvinces(){

        // Create retrofit client
        val httpClient = Retrofit.Builder()
            .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create service object
        val apiService = httpClient.create(ApiService::class.java)

        // Load province list from server
        val task = apiService.loadProvinceList()
        task.enqueue(object : Callback<List<Province>> {
            override fun onResponse(
                call: Call<List<Province>>,
                response: Response<List<Province>>
            ) {
                if(response.isSuccessful) {
                    view.showProvinceList(response.body())
                } else {
                    view.showError("Error while load data from server!")
                }
            }

            override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                view.showError("Error while load data from server!")
            }

        })

    }

    fun onProvinceClick(province: Province) {
        view.showProvinceDetail(province)
    }

}