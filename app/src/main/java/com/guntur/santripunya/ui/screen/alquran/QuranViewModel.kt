package com.guntur.santripunya.ui.screen.alquran

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.data.remote.response.QuranItem
import com.guntur.santripunya.data.remote.response.QuranResponse
import com.guntur.santripunya.data.remote.response.SuratAllItem
import com.guntur.santripunya.data.remote.response.SuratAllResponse
import com.guntur.santripunya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class QuranViewModel(
    private val repos : Repository
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<SuratAllItem?>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<SuratAllItem?>>>
        get() = _uiState

    private val _uiStateDetail : MutableStateFlow<UiState<List<QuranItem?>>> = MutableStateFlow(UiState.Loading)
    val uiStateDetail : StateFlow<UiState<List<QuranItem?>>>
        get() = _uiStateDetail

    fun getAllSurat(){
        viewModelScope.launch {
            try {
                val response = repos.getAllSurat()
                val dataSurat : List<SuratAllItem?>? = response.data
                val uiState : UiState<List<SuratAllItem?>> = if (dataSurat != null) {
                    UiState.Success(dataSurat)
                }else{
                    UiState.Error("Data Not Found")
                }
                _uiState.value = uiState
            } catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, SuratAllResponse::class.java)
                _uiState.value = UiState.Error("onFailure: $errorBody")
            }

        }
    }

    fun getYaasin(total: Int, id: Int){
        viewModelScope.launch {
            try {
                val chunkSize = 30
                val numChunks = (total + chunkSize - 1) / chunkSize
                val allData: MutableList<QuranItem?> = mutableListOf()

                for (i in 0 until numChunks){
                    val start = i * chunkSize + 1
                    val end = minOf((i + 1) * chunkSize, total)
                    val response = repos.getYaasin(id, start, end)
                    val dataAyat: List<QuranItem?>? = response.data
                    if (dataAyat != null){
                        allData.addAll(dataAyat)
                    }
                }
                val uiState: UiState<List<QuranItem?>> = if (allData.isNotEmpty()){
                    UiState.Success(allData)
                }else{
                    UiState.Error("Data Not Found")
                }
                _uiStateDetail.value = uiState
            } catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, QuranResponse::class.java)
                _uiStateDetail.value = UiState.Error("onFailure: $errorBody")
            }
        }
    }
}