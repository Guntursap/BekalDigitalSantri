package com.guntur.santripunya.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.guntur.santripunya.R
import com.guntur.santripunya.model.Feature
import com.guntur.santripunya.ui.navigation.Screen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun FeatureItem(
    navHostController: NavHostController,
    feature: Feature,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 60.dp, height = 90.dp)
            .clickable(
                onClick = {
                    navHostController.navigate(feature.screen){
                        popUpTo(navHostController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
    ) {
            Image(
                painter = painterResource(id = feature.imageFeature),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(4.dp)
                    .size(40.dp)
                    .background(Color.White)
            )
            Text(
                text = stringResource(id = feature.nameFeature),
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter)
            )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FeatureItePreview() {
    SantriPunyaTheme {
        FeatureItem(
            navHostController = rememberNavController(),
            feature = Feature(
                R.drawable.doa,
                R.string.pray,
                Screen.Home.route
            )
        )
    }
}