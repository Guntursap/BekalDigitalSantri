package com.guntur.santripunya.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun SurahItem(
    nameId:String,
    nameArb: String,
    number: String,
    revelation: String,
    numberOfAyah: String,
    modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        val numberArabic = Utils().convertToArabicNumber(number)
        Text(
            text = " \u06DD$numberArabic ",
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
        Column{
            Text(
                text = nameId
            )
            Text(
                text = "$revelation | $numberOfAyah ayat",
                color = Color.Magenta
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = nameArb,
            textAlign = TextAlign.Right,
            fontFamily = Utils().fontArabicNoto,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SurahItemPreview() {
    SantriPunyaTheme {
        SurahItem(
            nameId = "Al-Fatihah",
            nameArb = "الفاتحة",
            number = "1",
            revelation = "Makkiyyah",
            numberOfAyah = "7"
        )
    }
}