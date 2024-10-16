package com.guntur.santripunya.ui.screen.tahlil

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun TahlilScreen(
    context: Context,
    viewModel: TahlilViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getTahlil()
                LoadingScreen()
            }
            is UiState.Success -> {
                uiState.data?.content?.let { TahlilContent(
                    content = it,
                    modifier = modifier.padding(top = 64.dp, start = 8.dp, end = 8.dp)
                ) }
            }
            is UiState.Error -> {
                ComingSoonScreen(modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun TahlilContent(
    content: String,
    modifier: Modifier = Modifier) {
    Text(
        text = content,
        fontFamily = Utils().fontArabic,
        fontSize = 24.sp,
        textAlign = TextAlign.Right,
        lineHeight = 50.sp,
        modifier = modifier.verticalScroll(state = rememberScrollState())
    )
}