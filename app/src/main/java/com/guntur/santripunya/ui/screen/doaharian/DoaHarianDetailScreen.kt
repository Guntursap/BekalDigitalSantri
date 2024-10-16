package com.guntur.santripunya.ui.screen.doaharian

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun DoaHarianDetailScreen(
    context: Context,
    doaId: Int,
    modifier: Modifier = Modifier,
    viewModel: DoaHarianViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context)))
    ) {
    viewModel.uiStateDetail.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getDoaById(doaId)
                LoadingScreen()
            }
            is UiState.Success -> {
                uiState.data.judul?.let {
                    DoaHarianDetailContent(
                        title = it,
                        doa = uiState.data.arab!!,
                        mean = uiState.data.indo!!,
                        modifier = modifier
                            .fillMaxSize()
                            .padding(top = 64.dp)
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
fun DoaHarianDetailContent(
    title: String,
    doa: String,
    mean: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 32.dp)
    ) {
        Text(
            text = title,

            )
        Card(
            border = BorderStroke(
                1.dp,
                Brush.linearGradient(
                    listOf(MaterialTheme.colorScheme.primary, Color.Magenta))),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    lineHeight = TextUnit(1.5f, TextUnitType.Em),
                    text = doa,
                    fontFamily = Utils().fontArabic,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Divider(
                    color = Color.Transparent,
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    Color.Magenta
                                )
                            )
                        )
                )
                Text(
                    text = mean,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DoaHarianDeatailPreview() {
    SantriPunyaTheme {
        DoaHarianDetailScreen(context = LocalContext.current,
            doaId = 1)
    }
}