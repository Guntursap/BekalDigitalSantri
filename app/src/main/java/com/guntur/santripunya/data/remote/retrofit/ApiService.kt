package com.guntur.santripunya.data.remote.retrofit

import com.guntur.santripunya.data.remote.response.DoaAllResponse
import com.guntur.santripunya.data.remote.response.DoaByIdResponse
import com.guntur.santripunya.data.remote.response.HusnaResponse
import com.guntur.santripunya.data.remote.response.KitabByIdResponse
import com.guntur.santripunya.data.remote.response.KitabResponse
import com.guntur.santripunya.data.remote.response.QuranResponse
import com.guntur.santripunya.data.remote.response.SuratAllResponse
import com.guntur.santripunya.data.remote.response.TahlilResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceKitab{
    @GET("kitabs")
    suspend fun getKitab(): KitabResponse

    @GET("kitabs/{id}")
    fun getKitabById(
        @Path("id") id : Int
    ): Call<KitabByIdResponse>

    @GET("tahlil1")
    fun getTahlil(): Call<TahlilResponse>
}

interface ApiServiceApiMyQuran{
    @GET("doa/all")
    suspend fun getAllDoa(): DoaAllResponse

    @GET("doa/{id}")
    fun getDoaByid(
        @Path("id") id : Int
    ) : Call<DoaByIdResponse>

    @GET("husna/all")
    fun getAllHusna() : Call<HusnaResponse>

    @GET("quran/ayat/{id}/{start}-{end}")
    suspend fun getAyatRange(
        @Path("id") id: Int,
        @Path("start") start: Int,
        @Path("end") end: Int
    ): QuranResponse

    @GET("quran/surat/all")
    suspend fun getAllSurat(): SuratAllResponse
}

