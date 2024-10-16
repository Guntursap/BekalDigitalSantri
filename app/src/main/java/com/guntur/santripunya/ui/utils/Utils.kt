package com.guntur.santripunya.ui.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.guntur.santripunya.R

class Utils {
    val fontArabic = FontFamily(
        Font(R.font.scheherazade, FontWeight.Normal),
        Font(R.font.scheherazade, FontWeight.Bold),
        Font(R.font.scheherazade, FontWeight.Normal, style = FontStyle.Italic),
        Font(R.font.scheherazade, FontWeight.Bold, style = FontStyle.Italic)
    )
    val fontArabicNoto = FontFamily(
        Font(R.font.noto, FontWeight.Normal),
        Font(R.font.noto, FontWeight.Bold),
        Font(R.font.noto, FontWeight.Normal, style = FontStyle.Italic),
        Font(R.font.noto, FontWeight.Bold, style = FontStyle.Italic)
    )
    fun removeSpecialCharacters(text: String): String {
        return text
            .replace(" ۔", "")
            .replace("۞", "")
            .replace("ۨ", "")
    }
    fun convertToArabicNumber(number: String): String {
        val arabicNumbers = listOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
        return number.map { char ->
            if (char.isDigit()) {
                arabicNumbers[char.toString().toInt()]
            } else {
                char
            }
        }.joinToString("")
    }
}