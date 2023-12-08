package kh.edu.rupp.fe.visitme.model.api.service;

import java.util.List;

import kh.edu.rupp.fe.visitme.model.api.model.Province;
import kh.edu.rupp.fe.visitme.model.api.model.Province2;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/provinces.json")
    Call<List<Province>> loadProvinceList();


    @GET("/provinces2.json")
    Call<List<Province2>> loadProvinceList2();

}
