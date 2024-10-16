package com.guntur.santripunya.ui.screen.tahlil

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.data.remote.response.TahlilResponse
import com.guntur.santripunya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TahlilViewModel(
    private val repos : Repository
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<TahlilResponse?>> = MutableStateFlow(UiState.Loading)
    val uiState : MutableStateFlow<UiState<TahlilResponse?>>
        get() = _uiState

    fun getTahlil(){
        viewModelScope.launch {
            val client = repos.getTahlil()
            client.enqueue(object : Callback<TahlilResponse>{
                override fun onResponse(
                    call: Call<TahlilResponse>,
                    response: Response<TahlilResponse>
                ) {
                    if (response.isSuccessful){
                        _uiState.value = UiState.Success(response.body() as TahlilResponse)
                        Log.d("TahlilViewModel", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TahlilResponse>, t: Throwable) {
                    _uiState.value = UiState.Error("onFailure: ${t.message}")
                    Log.e("TahlilViewModel", "onFailure: ${t.message}")
                }
            })
        }
    }
}