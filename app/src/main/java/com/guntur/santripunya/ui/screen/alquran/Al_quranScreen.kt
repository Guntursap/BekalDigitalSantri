package com.guntur.santripunya.ui.screen.alquran

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.remote.response.SuratAllItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.component.SurahItem
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun Al_quranScreen(
    context: Context,
    navigateToDetail: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuranViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->  
        when (uiState){
            is UiState.Loading ->{
                viewModel.getAllSurat()
                LoadingScreen()
            }
            is UiState.Success -> {
                QuranContent(
                    dataItem = uiState.data,
                    modifier = modifier.padding(top = 64.dp),
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
                ComingSoonScreen()
            }
        }
    }
}

@Composable
fun QuranContent(
    dataItem: List<SuratAllItem?>,
    navigateToDetail: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
    
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(dataItem) { data ->
            Column {
                SurahItem(
                    nameId = data?.nameId!!,
                    nameArb = data.nameShort!!,
                    number = data.number!!,
                    revelation = data.revelationId!!,
                    numberOfAyah = data.numberOfVerses!!,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.number, data.numberOfVerses, data.nameId)
                    }
                )
                Divider(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
    
}

@Preview(showBackground = true)
@Composable
private fun Al_quranPreview() {
    SantriPunyaTheme {
        Al_quranScreen(
            navigateToDetail = { _, _, _ -> },
            context = LocalContext.current)
    }
}