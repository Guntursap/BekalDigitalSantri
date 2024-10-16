package com.guntur.santripunya.ui.screen.kitab

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.data.remote.response.DataItem
import com.guntur.santripunya.data.remote.response.KitabByIdResponse
import com.guntur.santripunya.data.remote.response.KitabResponse
import com.guntur.santripunya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class KitabViewModel(
    private val repos : Repository
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<DataItem?>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DataItem?>>>
        get() = _uiState
    private val _uiStateDetail : MutableStateFlow<UiState<KitabByIdResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateDetail: StateFlow<UiState<KitabByIdResponse>>
        get() = _uiStateDetail

    fun getKitab(){
        viewModelScope.launch {
            try {
                val response = repos.getAllKitab()
                val dataKitab: List<DataItem?>? = response.data
                val uiState: UiState<List<DataItem?>> = if (dataKitab != null){
                    UiState.Success(dataKitab)
                }else{
                    UiState.Error("Data Not Found")
                }
                _uiState.value = uiState
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, KitabResponse::class.java)
                _uiState.value = UiState.Error("onFailure: $errorBody")
            }
        }
    }
    fun getKitabById(kitabId: Int){
        viewModelScope.launch {
            val client = repos.getKitabById(kitabId)
            client.enqueue(object : Callback<KitabByIdResponse>{
                override fun onResponse(
                    call: Call<KitabByIdResponse>,
                    response: Response<KitabByIdResponse>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as KitabByIdResponse

                        _uiStateDetail.value = UiState.Success(data)
                        Log.d("Kitab ViewModel", "onResponse: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<KitabByIdResponse>, t: Throwable) {
                    _uiStateDetail.value = UiState.Error("onFailure: ${t.message}")
                    Log.e("Kitab ViewModel", "onFailure: ${t.message}", )
                }
            })
        }
    }
}