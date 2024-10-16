package com.guntur.santripunya.ui.screen.asmaulhusna

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.data.remote.response.HusnaItem
import com.guntur.santripunya.data.remote.response.HusnaResponse
import com.guntur.santripunya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AsmaViewModel (
    private val repos: Repository
): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<HusnaItem?>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<HusnaItem?>>>
        get() = _uiState

    fun getAllHusna(){
        viewModelScope.launch {
            val client = repos.getAllHusna()
            client.enqueue(object : Callback<HusnaResponse> {
                override fun onResponse(
                    call: Call<HusnaResponse>,
                    response: Response<HusnaResponse>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()?.data as List<HusnaItem>
                        _uiState.value = UiState.Success(data)
                        Log.d("AsmaViewModel", "onResponse: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<HusnaResponse>, t: Throwable) {
                    _uiState.value = UiState.Error("onFailure: ${t.message}")
                    Log.e("AsmaViewModel", "onFailure: ${t.message}", )
                }
            })
        }
    }
}