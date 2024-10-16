package com.guntur.santripunya.ui.screen.asmaulhusna

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.remote.response.HusnaItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.AsmaItem
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun AsmaulhusnaScreen(
    modifier: Modifier = Modifier,
    context: Context,
    navigateToAddWirid: (HusnaItem?) -> Unit,
    viewModel: AsmaViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getAllHusna()
                LoadingScreen()
            }
            is UiState.Success -> {
                HusnaContent(
                    modifier = modifier.padding(top = 64.dp),
                    dataAsma = uiState.data,
                    navigateToAddWirid = navigateToAddWirid
                )
            }
            is UiState.Error -> {
                ComingSoonScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun HusnaContent(
    modifier: Modifier = Modifier,
    navigateToAddWirid: (HusnaItem?) -> Unit,
    dataAsma: List<HusnaItem?>
) {
    var selectedItem by remember { mutableStateOf<HusnaItem?>(null) }
    var isDialogOpen by remember { mutableStateOf(false) }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(dataAsma, key = { it?.id!! }){ data ->
            AsmaItem(
                arab = data?.arab!!,
                indo = data.latin!!,
                modifier = Modifier.clickable {
                    selectedItem = data
                    isDialogOpen = true
                }
            )
        }
    }
    if (isDialogOpen && selectedItem != null){
        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = { 
                TextButton(
                    onClick = { isDialogOpen = false }
                ) {
                    Text(text = "Close")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        navigateToAddWirid(selectedItem)
                        isDialogOpen = false
                    }
                ) {
                    Text(text = "Add Wirid")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = selectedItem?.arab ?: "",
                        fontSize = 32.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = selectedItem?.latin ?: "",
                        )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = selectedItem?.indo ?: "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AsmaulhusnaPreview() {
    SantriPunyaTheme {
        AsmaulhusnaScreen(
            context = LocalContext.current,
            navigateToAddWirid = {}
            )
    }
}