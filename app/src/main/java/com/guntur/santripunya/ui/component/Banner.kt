package com.guntur.santripunya.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guntur.santripunya.R

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.dhsc),
        contentDescription = "Logo DHSC",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    )
}