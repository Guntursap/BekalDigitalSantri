package com.guntur.santripunya.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun QuranPagerItem(
    modifier: Modifier = Modifier,
    ayat: String,
    ayatNumber: String
) {
    val utils = Utils()
    val ayatNumberConverter = utils.convertToArabicNumber(ayatNumber)
    val ayatNumberClean = " \u06DD$ayatNumberConverter "
    val ayatClean = utils.removeSpecialCharacters(ayat)
    Text(
        text = buildAnnotatedString {
            append(ayatClean)
            withStyle(
                style = SpanStyle(
                fontSize = 20.sp
                )
            ){
                append(ayatNumberClean)
            }
        },
        fontFamily = Utils().fontArabic,
        fontSize = 24.sp,
        lineHeight = 40.sp,
        textAlign = TextAlign.Right,
    )
}

@Preview(showBackground = true)
@Composable
private fun QuranPagerPreview() {
    SantriPunyaTheme {
        QuranPagerItem(
            ayat = "ۨالَّذِيْ جَعَلَ لَكُمْ مِّنَ الشَّجَرِ الْاَخْضَرِ نَارًاۙ فَاِذَآ اَنْتُمْ مِّنْهُ تُوْقِدُوْنَ",
            ayatNumber = "56"
        )
    }
}