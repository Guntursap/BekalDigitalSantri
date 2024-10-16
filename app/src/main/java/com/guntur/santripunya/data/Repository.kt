package com.guntur.santripunya.data

import androidx.lifecycle.LiveData
import com.guntur.santripunya.data.local.Dao
import com.guntur.santripunya.data.local.Entity
import com.guntur.santripunya.data.remote.retrofit.ApiServiceApiMyQuran
import com.guntur.santripunya.data.remote.retrofit.ApiServiceKitab
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(
    private val dao: Dao,
    private val apiService: ApiServiceKitab,
    private val apiServiceApiMyQuran: ApiServiceApiMyQuran,
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
) {

    suspend fun getAllDoa() = apiServiceApiMyQuran.getAllDoa()

    suspend fun getAllSurat() = apiServiceApiMyQuran.getAllSurat()

    fun getAllHusna() = apiServiceApiMyQuran.getAllHusna()

    fun getDoaById(id: Int) = apiServiceApiMyQuran.getDoaByid(id)

    suspend fun getYaasin(id: Int, string: Int, end: Int) = apiServiceApiMyQuran.getAyatRange(id, string, end)

    suspend fun getAllKitab() = apiService.getKitab()

    fun getKitabById(id: Int) = apiService.getKitabById(id)

    fun getTahlil() = apiService.getTahlil()

    fun insertDataWirid(wiridEntity: Entity){
        executorService.execute{dao.saveWirid(wiridEntity)}
    }

    fun deleteDataWirid(wiridEntity: Entity){
        executorService.execute{dao.deleteWirid(wiridEntity)}
    }

    fun getAllWirid(): LiveData<List<Entity>>{
        return dao.getAllWirid()
    }

    fun getWirid(wiridId: Int): LiveData<Entity>{
        return dao.getWirid(wiridId)
    }

    fun updateWirid(wiridEntity: Entity){
        executorService.execute{dao.updateWirid(wiridEntity)}
    }

    companion object{
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            dao: Dao,
            apiService: ApiServiceKitab,
            apiServiceApiMyQuran: ApiServiceApiMyQuran
        ): Repository =
            instance ?: synchronized(this){
                instance ?: Repository(dao, apiService, apiServiceApiMyQuran)
            }.also { instance = it }
    }
}