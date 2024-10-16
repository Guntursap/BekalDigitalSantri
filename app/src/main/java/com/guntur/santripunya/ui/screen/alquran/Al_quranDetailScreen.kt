package com.guntur.santripunya.ui.screen.alquran

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.remote.response.QuranItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.component.QuranItemLazy
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun Al_quranDetailScreen(
    modifier: Modifier = Modifier,
    totalAyat: Int,
    suratId: Int,
    context: Context,
    viewModel: QuranViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    viewModel.uiStateDetail.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getYaasin(
                    total = totalAyat,
                    id = suratId
                )
                LoadingScreen()
            }
            is UiState.Success -> {
                if (suratId != 1){
                    Column(
                        modifier = modifier
                            .padding(top = 64.dp)
                    ) {
                        Text(
                            text = "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيْم",
                            fontFamily = Utils().fontArabic,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp)
                        )
                        Al_quranDetailContent(
                            dataAyat = uiState.data
                        )
                    }
                }else{
                    Al_quranDetailContent(
                        dataAyat = uiState.data,
                        modifier = modifier.padding(top = 64.dp)
                    )
                }
            }
            is UiState.Error -> {
                ComingSoonScreen(modifier = modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Al_quranDetailContent(
    dataAyat: List<QuranItem?>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(dataAyat) { item ->
            item?.arab?.let {
                QuranItemLazy(
                    ayat = it,
                    ayatLatin = item.latin!!,
                    artinya = item.text!!,
                    ayatKe = item.ayah!!
                )
            }
        }
    }
}