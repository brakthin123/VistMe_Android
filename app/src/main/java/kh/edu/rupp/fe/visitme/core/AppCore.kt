package kh.edu.rupp.fe.visitme.core

import android.app.Application

class AppCore : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {

        private var instance: AppCore? = null

        fun get(): AppCore {
            return instance!!
        }

    }

}