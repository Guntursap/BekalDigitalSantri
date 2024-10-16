package com.guntur.santripunya.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.guntur.santripunya.R
import com.guntur.santripunya.ui.navigation.Screen

data class Feature(
    @DrawableRes val imageFeature: Int,
    @StringRes val nameFeature: Int,
    val screen : String
)

val dummyFeatures = listOf(
    Feature(R.drawable.quran, R.string.al_quran, Screen.Al_quran.route),
    Feature(R.drawable.man, R.string.yaasin, Screen.Yaasin.route),
    Feature(R.drawable.tahlil, R.string.tahlil, Screen.Tahlil.route),
    Feature(R.drawable.kitab, R.string.kitab, Screen.Kitab.route),
    Feature(R.drawable.doa, R.string.pray, Screen.Doa.route)
)