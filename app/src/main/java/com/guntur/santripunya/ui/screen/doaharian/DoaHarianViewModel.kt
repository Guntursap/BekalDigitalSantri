package com.guntur.santripunya.ui.screen.doaharian

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.data.remote.response.Data
import com.guntur.santripunya.data.remote.response.DoaAllResponse
import com.guntur.santripunya.data.remote.response.DoaByIdResponse
import com.guntur.santripunya.data.remote.response.DoaItem
import com.guntur.santripunya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class DoaHarianViewModel(
    private val repos: Repository): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<DoaItem?>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DoaItem?>>>
        get() = _uiState

    private val _uiStateDetail : MutableStateFlow<UiState<Data>> = MutableStateFlow(UiState.Loading)
    val uiStateDetail : StateFlow<UiState<Data>>
        get() = _uiStateDetail
    fun getAllDoa(){
        viewModelScope.launch {
            try {
                val response = repos.getAllDoa()
                val dataDoa : List<DoaItem?>? = response.data
                val uiState : UiState<List<DoaItem?>> = if (dataDoa != null) {
                    UiState.Success(dataDoa)
                }else{
                    UiState.Error("Data Not Found")
                }
                _uiState.value = uiState
            } catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, DoaAllResponse::class.java)
               _uiState.value = UiState.Error("onFailure: $errorBody")
            }
        }
    }

    fun getDoaById(id: Int){
        viewModelScope.launch {
            val client = repos.getDoaById(id)
            client.enqueue(object : Callback<DoaByIdResponse>{
                override fun onResponse(
                    call: Call<DoaByIdResponse>,
                    response: Response<DoaByIdResponse>
                ) {
                   if (response.isSuccessful){
                       val data = response.body()?.data as Data

                       _uiStateDetail.value = UiState.Success(data)
                       Log.d("DoaViewModel", "onResponse: ${response.message()}")
                   }
                }

                override fun onFailure(call: Call<DoaByIdResponse>, t: Throwable) {
                    _uiStateDetail.value = UiState.Error("onFailure: ${t.message}")
                    Log.e("DoaViewModel", "onFailure: ${t.message}", )
                }
            })
        }
    }
}