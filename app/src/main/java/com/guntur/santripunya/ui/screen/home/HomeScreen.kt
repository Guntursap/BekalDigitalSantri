package com.guntur.santripunya.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.guntur.santripunya.model.dummyFeatureReading
import com.guntur.santripunya.model.dummyFeatures
import com.guntur.santripunya.ui.component.Banner
import com.guntur.santripunya.ui.component.CardPrayerTime
import com.guntur.santripunya.ui.component.FeatureItem
import com.guntur.santripunya.ui.component.FeatureReadingItem
import com.guntur.santripunya.ui.component.HomeSection
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    HomeContent(
        navHostController = navHostController,
        modifier = modifier.background(Color.White)
    )
}

@Composable
fun HomeContent(
    navHostController: NavHostController,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val bannerHeight = 260.dp
        Banner(modifier = Modifier.height(bannerHeight))
        Column(
            modifier = Modifier
                .padding(top = bannerHeight/1.5f)
        ) {
            CardPrayerTime()
            HomeSection(
                title = "Explore Feature",
                content = { FeatureRow(navHostController = navHostController) }
            )
            HomeSection(
                title = "Reading Feature",
                content = { FeatureColumn(navController = navHostController) })
        }
    }
}

@Composable
fun FeatureRow(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
    ) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyFeatures, key = {it.nameFeature}){ feature ->
            FeatureItem(feature = feature, navHostController = navHostController)
        }
    }
}

@Composable
fun FeatureColumn(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = modifier
    ){
        items(dummyFeatureReading, key = {it.title}){ featureReading ->
            FeatureReadingItem(
                navController = navController,
                title = featureReading.title,
                image = featureReading.image,
                screen = featureReading.screen
                )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePreview() {
    SantriPunyaTheme {
        HomeScreen(navHostController = rememberNavController())
    }
}