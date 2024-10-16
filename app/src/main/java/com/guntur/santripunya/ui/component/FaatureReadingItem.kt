package com.guntur.santripunya.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.guntur.santripunya.R
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun FeatureReadingItem(
    navController: NavHostController,
    screen: String,
    image: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable(
                onClick = {
                    navController.navigate(screen){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(35.dp)
        )
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FeatureReadingItemPreview() {
    SantriPunyaTheme {
        FeatureReadingItem(
            navController = rememberNavController(),
            title = "AsmaulHusna",
            image = R.drawable.yaasin,
            screen = ""
        )
    }
    
}