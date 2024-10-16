package com.guntur.santripunya.ui.screen.kitab

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.R
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.remote.response.DataItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.KitabItem
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun KitabScreen(
    navigateToDetailKitab: (Int) -> Unit,
    modifier: Modifier = Modifier,
    context: Context,
    viewModel : KitabViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getKitab()
                LoadingScreen()
            }
            is UiState.Success ->{
                KitabContent(
                    navigateToDetailKitab = navigateToDetailKitab,
                    kitab = uiState.data,
                    modifier = modifier.padding(top = 64.dp)
                )
            }
            is UiState.Error -> {
                ComingSoonScreen(modifier = modifier.fillMaxSize())}
        }
    }
}

@Composable
fun KitabContent(
    navigateToDetailKitab: (Int) -> Unit,
    modifier: Modifier = Modifier,
    kitab: List<DataItem?>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = modifier
    ) {
        items(kitab) { data ->
            data?.title?.let {
                KitabItem(
                    idImage = R.drawable.imrity,
                    title = it,
                    modifier = Modifier.clickable {
                        navigateToDetailKitab(data.id!!)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KitabScreenPreview() {
    SantriPunyaTheme {
        KitabScreen(navigateToDetailKitab = {},
            context = LocalContext.current)
    }
}