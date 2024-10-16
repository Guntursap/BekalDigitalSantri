package com.guntur.santripunya.ui.screen.yaasin

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guntur.santripunya.data.Injection
import com.guntur.santripunya.data.remote.response.QuranItem
import com.guntur.santripunya.ui.ViewModelFactory
import com.guntur.santripunya.ui.common.UiState
import com.guntur.santripunya.ui.component.ComingSoonScreen
import com.guntur.santripunya.ui.component.LoadingScreen
import com.guntur.santripunya.ui.component.QuranItemLazy
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils
import kotlinx.coroutines.launch

@Composable
fun YaasinScreen(
    context: Context,
    modifier: Modifier = Modifier,
    viewModel: YasiinViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getYaasin()
                LoadingScreen()
            }
            is UiState.Success -> {
                YaasinContent(
                    dataAyat = uiState.data,
                    modifier = modifier.padding(top = 64.dp)
                    )
            }
            is UiState.Error -> {
                ComingSoonScreen()
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun YaasinContent(
    modifier: Modifier = Modifier,
    dataAyat: List<QuranItem?>
) {
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    val scope = rememberCoroutineScope()

    val tabTitles = listOf("Halaman 1", "Halaman 2")
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title)},
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.scrollToPage(index) }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(dataAyat) { data ->
                            data?.arab?.let {
                                QuranItemLazy(
                                    ayat = it,
                                    ayatLatin = data.latin!!,
                                    artinya = data.text!!,
                                    ayatKe = data.ayah!!
                                )
                            }
                        }
                    }
                }

                1 -> {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        val ayat = dataAyat.mapNotNull { it?.arab }
                        val utils = Utils()
                        val ayatNumbers = dataAyat.map { it?.ayah }
                        val ayatNumbersInArabic =
                            ayatNumbers.map { utils.convertToArabicNumber(it ?: "") }

                        // Menggabungkan ayat dengan pemisah berupa nomor ayat dalam format Arab
                        val ayatString =
                            ayat.zip(ayatNumbersInArabic).joinToString(separator = " ") {
                                "${it.first} \u06DD${it.second}"
                            }

                        val ayatClean = utils.removeSpecialCharacters(ayatString)
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ",
                                fontFamily = Utils().fontArabic,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = ayatClean,
                                fontFamily = Utils().fontArabic,
                                fontSize = 24.sp,
                                lineHeight = 46.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun YasinContentPreview() {
    SantriPunyaTheme {
        YaasinContent(dataAyat = listOf(
            QuranItem(
                arab = "al",
                latin = "tes",
                text = "testing",
                ayah = "1"
            )
        ))
    }
}