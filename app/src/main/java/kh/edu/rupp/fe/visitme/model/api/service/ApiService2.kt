package kh.edu.rupp.fe.visitme.model.api.service

import kh.edu.rupp.fe.visitme.model.api.model.Province
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService2 {

    @GET("/provinces.json")
    suspend fun loadProvinceList(): List<Province>

    @GET("/provinces.json")
    suspend fun loadProvinceList2(): List<Province>

    @POST("/login")
    suspend fun login(): String

}