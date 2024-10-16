package com.guntur.santripunya.ui.screen.doaharian

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.R
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.remote.response.DoaItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.KitabItem
import com.guntur.santripunya.ui.component.LoadingScreen

@Composable
fun DoaHarianScreen(
    context: Context,
    navigateToDetailDoa: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DoaHarianViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllDoa()
                LoadingScreen()
            }
            is UiState.Success -> {
                DoaHarianContent(
                    navigateToDetailDoa = navigateToDetailDoa,
                    doa = uiState.data,
                    modifier = modifier.padding(top = 64.dp)
                )
            }
            is UiState.Error -> {
                ComingSoonScreen(modifier = modifier.fillMaxSize())
            }
        }
    }

}

@Composable
fun DoaHarianContent(
    doa: List<DoaItem?>,
    navigateToDetailDoa: (Int) -> Unit,
    modifier: Modifier = Modifier
    ) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = modifier
    ) {
        itemsIndexed(doa) { index, data ->
            data?.judul?.let{
                KitabItem(
                    idImage = R.drawable.doa,
                    title = it,
                    modifier = Modifier.clickable {
                        navigateToDetailDoa(index + 1)
                    }
                )
            }
        }
    }
}