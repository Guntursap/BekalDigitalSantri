package com.guntur.santripunya.data

import android.content.Context
import com.guntur.santripunya.data.local.WiridDatabase
import com.guntur.santripunya.data.remote.retrofit.ApiConfig
import com.guntur.santripunya.data.remote.retrofit.ApiConfigApiQuran

object Injection {
    fun provideRepository(context: Context): Repository{
        val database = WiridDatabase.getInstance(context)
        val dao = database.wiridDao()
        val apiService = ApiConfig.getApiService()
        val apiServiceApiMyQuran = ApiConfigApiQuran.getApiService()
        return Repository.getInstance(dao, apiService, apiServiceApiMyQuran)
    }
}