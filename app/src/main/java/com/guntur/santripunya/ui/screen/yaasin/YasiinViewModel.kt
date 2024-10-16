package com.guntur.santripunya.ui.screen.yaasin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.data.remote.response.QuranItem
import com.guntur.santripunya.data.remote.response.QuranResponse
import com.guntur.santripunya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class YasiinViewModel(
    private val repos: Repository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<QuranItem?>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<QuranItem?>>>
        get() = _uiState

    fun getYaasin(){
        viewModelScope.launch {
            try {
                val chunkSize = 30
                val totalData = 83
                val numChunks = (totalData + chunkSize -1 ) / chunkSize
                val allData: MutableList<QuranItem?> = mutableListOf()

                for (i in 0 until numChunks){
                    val start = i * chunkSize + 1
                    val end = minOf((i + 1) * chunkSize, totalData)
                    val response = repos.getYaasin(36, start, end)
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
                _uiState.value = uiState
            } catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, QuranResponse::class.java)
                _uiState.value = UiState.Error("onFailure: $errorBody")
            }
        }
    }
}