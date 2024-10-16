package com.guntur.santripunya.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object Al_quran : Screen("Al-Qur'an")
    data object Al_quranDetail : Screen("Surah/{surahId}/{totalAyat}/{suratName}"){
        fun createRoute(surahId: String, totalAyat: String, suratName: String) = "Surah/$surahId/$totalAyat/$suratName"
    }
    data object Asmaulhusna : Screen("Asmaulhusna")
    data object About : Screen("About")
    data object Doa : Screen("Do'a-do'a")
    data object Kitab : Screen("Kitab")
    data object Tahlil : Screen("Tahlil")
    data object Wirid : Screen("Wirid")
    data object Yaasin : Screen("Yaasin")
    data object DoaHarian : Screen("Doa Harian")
    data object DetailDoaHarian: Screen("Doa Harian/{doaId}"){
        fun createRoute(doaId: Int) = "Doa Harian/$doaId"
    }
    data object AddWirid : Screen("Tambah Wiridan/{selectedWirid}"){
        fun crateRoute(selectedWirid: String) = "Tambah Wiridan/$selectedWirid"
    }
    data object DetailWirid : Screen("Wirid/{wiridId}"){
        fun createRoute(wiridId: Int) = "wirid/$wiridId"
    }
    data object DetailKitab : Screen("Kitab/{kitabId}"){
        fun createRoute(kitabId: Int) = "kitab/$kitabId"
    }
}