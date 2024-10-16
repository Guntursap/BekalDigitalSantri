package com.guntur.santripunya.ui.screen.wirid

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.R
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.local.Entity
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.component.WiridItem
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun WiridScreen(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: WiridViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToDetailWirid: (Int) -> Unit
) {
    val allWirid by viewModel.getALlWirid().observeAsState()
    if (allWirid.isNullOrEmpty()){ 
        WiridNotFound(modifier = modifier.fillMaxSize())
    }else{
        WiridContent(
            dataItems = allWirid!!,
            modifier = modifier.padding(top = 64.dp),
            navigateToDetailWirid = navigateToDetailWirid
        )
    }
}

@Composable
fun WiridContent(
    modifier: Modifier = Modifier,
    dataItems: List<Entity>,
    navigateToDetailWirid: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = modifier
    ) {
        items(dataItems, key = {it.wiridId}){ data ->
            WiridItem(
                title = data.wiridIndo,
                startCount = data.startCount.toString(),
                count = data.count.toString(),
                modifier = Modifier.clickable {
                    navigateToDetailWirid(data.wiridId)
                }
            )
        }
    }
}
@Composable
fun WiridNotFound(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.wiridnotfound), contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.wirid_not_found),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = stringResource(id = R.string.wirid_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WiridScreenPreview() {
    SantriPunyaTheme {
        WiridScreen(context = LocalContext.current, navigateToDetailWirid = {})
    }
}