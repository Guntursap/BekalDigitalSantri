package com.guntur.santripunya.ui.screen.kitab

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.R
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.model.dummyKitab
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun KitabDetailScreen(
    modifier: Modifier = Modifier,
    kitabId: Int,
    context: Context,
    viewModel: KitabViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(context)
        )
    )
) {
    viewModel.uiStateDetail.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                LoadingScreen()
                viewModel.getKitabById(kitabId)
            }
            is UiState.Success -> {
                uiState.data.content?.let {
                    KitabDetailContent(
                        content = it,
                        modifier = modifier.padding(top = 64.dp)
                    )
                }
            }
            is UiState.Error -> {
                ComingSoonScreen()
            }
        }
    }
}

@Composable
fun KitabDetailContent(
    content : String,
    modifier: Modifier = Modifier
) {
    val kontenKitab = if (dummyKitab.isNotEmpty() && dummyKitab.size > 1) {
        content
    } else {
        stringResource(id = R.string.pray)
    }

    // Memisahkan teks pada tanda **
    val lines = kontenKitab.split("\n")
    val modifiedLines = lines.map { line ->
        if (line.contains("**")) {
            val parts = line.split("**")
            parts[0] to parts[1]
        } else {
            line to null
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center // Memposisikan konten di tengah
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            modifiedLines.forEach { (before, after) ->
                if (after != null) {
                    Text(
                        text = "۞ $before",
                        fontSize = 20.sp,
                        fontFamily = Utils().fontArabic,
                        textAlign = TextAlign.End, // Teks sebelum '**' di sebelah kanan
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = after,
                        fontSize = 20.sp,
                        fontFamily = Utils().fontArabic,
                        textAlign = TextAlign.Start, // Teks setelah '**' di sebelah kiri
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                } else {
                    Text(
                        text = before,
                        fontFamily = Utils().fontArabic,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center, // Teks tanpa '**'
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KitabDetailPreview() {
    SantriPunyaTheme {
        KitabDetailScreen(kitabId = 2, context = LocalContext.current)
    }
}