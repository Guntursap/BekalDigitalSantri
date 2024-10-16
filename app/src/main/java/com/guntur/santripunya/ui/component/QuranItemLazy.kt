package com.guntur.santripunya.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import com.guntur.santripunya.ui.utils.Utils

@Composable
fun QuranItemLazy(
    modifier: Modifier = Modifier,
    ayat: String,
    ayatLatin: String,
    artinya: String,
    ayatKe: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val utils = Utils()
        val ayatKeArab = utils.convertToArabicNumber(ayatKe)
        val ayatKeDalamKurung = " \u06DD$ayatKeArab"
        val ayatCleaned = utils.removeSpecialCharacters(ayat)
        Text(
            text = buildAnnotatedString {
                append(ayatCleaned)
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = 20.sp
                    )
                ){
                    append(ayatKeDalamKurung)
                }
            },
            fontFamily = utils.fontArabic,
            fontSize = 28.sp,
            textAlign = TextAlign.Right,
            lineHeight = 50.sp,
            style = TextStyle(
                textDirection = TextDirection.Rtl
            ),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End)
        )
        Text(
            text = ayatLatin,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = artinya,
            modifier = Modifier.padding(8.dp)
            )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun QuranItemPreview() {
    SantriPunyaTheme {
        QuranItemLazy(
            ayat = "ۨالَّذِيْ جَعَلَ لَكُمْ مِّنَ الشَّجَرِ الْاَخْضَرِ نَارًاۙ فَاِذَآ اَنْتُمْ مِّنْهُ تُوْقِدُوْنَ",
            ayatLatin = "Fa ammal-lażīna āmanū wa ‘amiluṣ-ṣāliḥāti fa yuwaffīhim ujūrahum wa yazīduhum min faḍlih(ī), wa ammal-lażīnastankafū wastakbarū fa yu‘ażżibuhum ‘ażāban alīmā(n), wa lā yajidūna lahum min dūnillāhi waliyyaw wa lā naṣīrā(n).",
            ayatKe = "173",
            artinya = "Adapun orang-orang yang beriman dan mengerjakan kebajikan, Allah akan menyempurnakan pahala bagi mereka dan menambah sebagian dari karunia-Nya. Sementara itu, orang-orang yang enggan (menyembah Allah) dan menyombongkan diri, maka Allah akan mengazab mereka dengan azab yang pedih. Mereka pun tidak akan mendapatkan pelindung dan penolong selain Allah.",
            )
    }
}