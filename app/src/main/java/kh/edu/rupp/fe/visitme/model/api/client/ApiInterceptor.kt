package kh.edu.rupp.fe.visitme.model.api.client

import kh.edu.rupp.fe.visitme.core.AppCore
import kh.edu.rupp.fe.visitme.utility.AppEncryptedPreference
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AppEncryptedPreference.get(AppCore.get().applicationContext).getApiToken()
        val request = if(token != null) {
            chain.request().newBuilder().addHeader("Authorizationx", "Bearer $token").build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}