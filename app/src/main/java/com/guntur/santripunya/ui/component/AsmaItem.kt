package com.guntur.santripunya.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun AsmaItem(
    arab: String,
    indo: String,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.linearGradient(
                listOf(MaterialTheme.colorScheme.primary,
                    Color.Magenta)
            )),
        modifier = modifier
    ) {
        Text(
            text = arab,
            fontFamily = Utils().fontArabic,
            fontSize = 32.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Divider(
            color = Color.Transparent,
            modifier = Modifier
                .background(brush = Brush.linearGradient(
                    listOf(Color.Magenta, MaterialTheme.colorScheme.primary)
                ))
        )
        Text(
            text = indo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun AsmaItemPreview() {
    SantriPunyaTheme {
        AsmaItem(arab = "الرَّحْمـٰنُ", indo = "Yang Maha Penyayang")
    }
}