package com.guntur.santripunya.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guntur.santripunya.R


@Composable
fun CardPrayerTime(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(279.dp, 137.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(text = "Senin")
                Text(text = "15 Syawal 1444")
                Text(text = "Magrib selanjutnya")
                Text(text = "18 : 26 pm")
            }
            Image(
                painter = painterResource(id = R.drawable.mosque),
                contentDescription = "Logo Masjid",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(84.dp, 84.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}
