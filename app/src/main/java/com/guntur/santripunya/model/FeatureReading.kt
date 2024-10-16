package com.guntur.santripunya.model

import com.guntur.santripunya.R
import com.guntur.santripunya.ui.navigation.Screen

data class FeatureReading(
    val image: Int,
    val title: String,
    val screen : String
)

val dummyFeatureReading = listOf(
    FeatureReading(R.drawable.tasbih, "Tasbih", Screen.Wirid.route),
    FeatureReading(R.drawable.yaasin, "Asmaul Husna", Screen.Asmaulhusna.route),
    FeatureReading(R.drawable.pray, "Doa Harian", Screen.DoaHarian.route)
)