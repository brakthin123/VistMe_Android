package kh.edu.rupp.fe.visitme.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.fe.visitme.model.api.client.ApiClient
import kh.edu.rupp.fe.visitme.model.api.model.ApiData
import kh.edu.rupp.fe.visitme.model.api.model.Province
import kh.edu.rupp.fe.visitme.model.api.model.Status
import kh.edu.rupp.fe.visitme.utility.AppEncryptedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProvincesViewModel : ViewModel() {

    private val _provincesData = MutableLiveData<ApiData<List<Province>>>()
    val provinceData: LiveData<ApiData<List<Province>>>
        get() = _provincesData

    fun loadProvinces() {
        var apiData = ApiData<List<Province>>(Status.PROCESSING, null)
        _provincesData.postValue(apiData)

        // Load province list from server
        viewModelScope.launch(Dispatchers.IO) {
            apiData = try {
                val response = ApiClient.get().apiService.loadProvinceList()
                ApiData(Status.SUCCESS, response)
            }catch (ex: Exception) {
                Log.e("ited", "Load provinces error: ${ex.message}")
                ApiData<List<Province>>(Status.ERROR, null)
            }

            withContext(Dispatchers.Main.immediate) {
                _provincesData.postValue(apiData)
            }
        }



        /*task.enqueue(object : Callback<List<Province>> {
            override fun onResponse(
                call: Call<List<Province>>,
                response: Response<List<Province>>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _provincesData.postValue(apiData)
                } else {
                    val apiData = ApiData<List<Province>>(Status.ERROR, null)
                    _provincesData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                val apiData = ApiData<List<Province>>(Status.ERROR, null)
                _provincesData.postValue(apiData)
            }

        })*/
    }

    // Login example
    fun login(context: Context, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.get().apiService.login()
                AppEncryptedPreference.get(context).storeApiToken(response)
            }catch (ex: Exception) {
                Log.e("ited", "Login error: ${ex.message}")
            }
        }
    }

}