package com.guntur.santripunya.ui.screen.wirid

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.local.Entity
import com.guntur.santripunya.data.remote.response.HusnaItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.navigation.Screen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import kotlinx.coroutines.launch

@Composable
fun AddWiridScreen(
    modifier: Modifier = Modifier,
    context: Context,
    preselectedWirid: String,
    viewModel: WiridViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navController: NavHostController
) {
    var selectedWirid by remember { mutableStateOf<HusnaItem?>(null) }
    var wiridCount by remember { mutableStateOf("") }
    var startWirid by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getAllHusna()
                LoadingScreen()
            }
            is UiState.Success -> {
                val addinitionalWirid = listOf(
                    HusnaItem(latin = "Alhamdulillah", arab = "اَلْحَمْدُ لِلّهِ"),
                    HusnaItem(latin = "Allahu Akbar", arab = "اَللّهُ أَكْبَر"),
                    HusnaItem(latin = "Astaghfirullah", arab = "أَسْتَغْفِرُ اللّه")
                )
                val combineData = addinitionalWirid + uiState.data
                LaunchedEffect(preselectedWirid) {
                    selectedWirid = combineData.find { it?.latin == preselectedWirid }
                }
                Box(modifier = modifier.fillMaxSize()) {
                    AddWiridContent(
                        modifier = Modifier.padding(top = 64.dp),
                        selectedWirid = selectedWirid,
                        wiridCount = wiridCount,
                        startWirid = startWirid,
                        dataRemote = combineData,
                        onWiridChange = { selectedWirid = it },
                        onStartWiridChange = { startWirid = it },
                        onWiridCountChange = { wiridCount = it },
                        onClick = {
                            val startCountInt = startWirid.toIntOrNull() ?: 0
                            val wiridCountInt = wiridCount.toIntOrNull() ?: 0
                            if (selectedWirid != null && wiridCount.isNotEmpty() && wiridCount.toIntOrNull() != null) {
                                if (startCountInt < wiridCountInt){
                                    viewModel.saveWirid(
                                        Entity(
                                            wiridArab = selectedWirid?.arab ?: "",
                                            wiridIndo = selectedWirid?.latin ?: "",
                                            startCount = startCountInt,
                                            count = wiridCountInt
                                        )
                                    )
                                    navController.navigate(Screen.Wirid.route){
                                        popUpTo(navController.graph.findStartDestination().id){inclusive = false}
                                    }
                                } else{
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Start count cannot be greater than wirid count",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Please enter valid wirid and count",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    )
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 64.dp)
                    )
                }
            }
            is UiState.Error -> {
                ComingSoonScreen()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWiridContent(
    modifier: Modifier = Modifier,
    selectedWirid: HusnaItem?,
    wiridCount: String,
    startWirid: String,
    dataRemote: List<HusnaItem?>,
    onWiridChange: (HusnaItem) -> Unit,
    onStartWiridChange: (String) -> Unit,
    onWiridCountChange: (String) -> Unit,
    onClick:  () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val optionsRemote = dataRemote.filterNotNull()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = selectedWirid?.latin ?: "",
                onValueChange = {},
                label = {
                        Text(text = "Bacaan Wirid")
                },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            )
            {
                optionsRemote.forEach{ selectOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectOption.latin!!) },
                        onClick = {
                            onWiridChange(selectOption)
                            expanded = false
                        }
                    )
                    Divider(color = Color.Magenta)
                }
            }
        }
        OutlinedTextField(
            value = wiridCount,
            onValueChange = { onWiridCountChange(it) },
            label = {
                Text(text = "Jumlah Wiridan")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = startWirid,
            onValueChange = { onStartWiridChange(it) },
            singleLine = true,
            label = {
                Text(text = "Nilai Awal")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onClick()
                }
            ),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = { onClick() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "simpan")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddWiridScreenPreview() {
    SantriPunyaTheme {
        AddWiridContent(
            selectedWirid = HusnaItem(),
            wiridCount = "",
            startWirid = "",
            dataRemote = listOf(),
            onWiridChange = {},
            onStartWiridChange = {},
            onWiridCountChange = {}
        ) {
            
        }
    }
}