package com.guntur.santripunya.ui.screen.wirid

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.local.Entity
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun WiridDetailScreen(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
    wiridId : Int,
    viewModel: WiridViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    val wiridData by viewModel.getWiridDetail(wiridId).observeAsState()
    wiridData?.let { wirid ->
        WiridDetailContent(
            wiridData = wirid,
            onClicked = {
                viewModel.updateDataWirid(
                    Entity(
                        wiridId = wirid.wiridId,
                        wiridArab = wirid.wiridArab,
                        wiridIndo = wirid.wiridIndo,
                        startCount = wirid.startCount + 1,
                        count = wirid.count
                    )
                )
            },
            onDelete = {
                viewModel.deleteDataWirid(
                    Entity(
                        wiridId = wirid.wiridId,
                        wiridIndo = wirid.wiridIndo,
                        wiridArab = wirid.wiridArab,
                        startCount = wirid.startCount,
                        count = wirid.count
                    )
                )
                navController.navigateUp()
            }
        )
    }
}

@Composable
fun WiridDetailContent(
    modifier: Modifier = Modifier,
    wiridData: Entity,
    onClicked: () -> Unit,
    onDelete: () -> Unit
    ) {
    val isButtonEnabled = wiridData.startCount < wiridData.count
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Card(
                border = BorderStroke(1.dp, Color.DarkGray),
                modifier = Modifier
                    .size(width = 265.dp, height = 161.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = wiridData.wiridArab,
                            fontFamily = Utils().fontArabic,
                            fontSize = 24.sp
                        )
                        Row {
                            Text(
                                text = "${wiridData.startCount}/",
                            )
                            Text(
                                text = "${wiridData.count}"
                            )
                        }
                    }
                }
            }
            Button(
                onClick = { onClicked() },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .padding(32.dp)
            ) {
                if (isButtonEnabled) {
                    Text(
                        text = "Tap Here",
                    )
                } else {
                    Text(
                        text = "Completed",
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = { onDelete() },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WiridDetailScreenPreview() {
    SantriPunyaTheme {
        WiridDetailContent(
            wiridData = Entity(
                wiridId = 1,
                wiridArab = "اَلحَمْدُ لِلَّه",
                wiridIndo = "Alhamdulillah",
                startCount = 33,
                count = 33
            ),
            onClicked = {},
            onDelete = {}
        )
    }
}