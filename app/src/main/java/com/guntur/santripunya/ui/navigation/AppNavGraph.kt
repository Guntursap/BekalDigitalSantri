package com.guntur.santripunya.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.guntur.santripunya.ui.screen.about.AboutScreen
import com.guntur.santripunya.ui.screen.alquran.Al_quranDetailScreen
import com.guntur.santripunya.ui.screen.alquran.Al_quranScreen
import com.guntur.santripunya.ui.screen.asmaulhusna.AsmaulhusnaScreen
import com.guntur.santripunya.ui.screen.doa.Doa_DoaScreen
import com.guntur.santripunya.ui.screen.doaharian.DoaHarianDetailScreen
import com.guntur.santripunya.ui.screen.doaharian.DoaHarianScreen
import com.guntur.santripunya.ui.screen.home.HomeScreen
import com.guntur.santripunya.ui.screen.kitab.KitabDetailScreen
import com.guntur.santripunya.ui.screen.kitab.KitabScreen
import com.guntur.santripunya.ui.screen.tahlil.TahlilScreen
import com.guntur.santripunya.ui.screen.wirid.AddWiridScreen
import com.guntur.santripunya.ui.screen.wirid.WiridDetailScreen
import com.guntur.santripunya.ui.screen.wirid.WiridScreen
import com.guntur.santripunya.ui.screen.yaasin.YaasinScreen

@Composable
fun AppNavGraph(
    context: Context,
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route){
            HomeScreen(navHostController = navController)
        }
        composable(Screen.About.route){
            AboutScreen()
        }
        composable(Screen.Al_quran.route){
            Al_quranScreen(
                context = context,
                navigateToDetail = { surahId, totalAyat, suratName ->
                    navController.navigate(Screen.Al_quranDetail.createRoute(surahId, totalAyat, suratName))
                }
            )
        }
        val surahArgument = "surahId"
        val quranArgument = "totalAyat"
        composable(
            route = Screen.Al_quranDetail.route,
            arguments = listOf(
                navArgument(surahArgument) { type = NavType.StringType },
                navArgument(quranArgument) {type = NavType.StringType }
            )
        ){
            val surahId = it.arguments?.getString(surahArgument) ?: ""
            val totalAyat = it.arguments?.getString(quranArgument) ?: ""
            Al_quranDetailScreen(
                context = context,
                suratId = surahId.toInt(),
                totalAyat = totalAyat.toInt()
            )
        }
        composable(Screen.Asmaulhusna.route){
            AsmaulhusnaScreen(
                context = context,
                navigateToAddWirid = { husnaItem ->
                    navController.navigate(Screen.Wirid.route) {
                        // Menghapus semua layar sebelumnya
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = false }
                    }
                    navController.navigate(Screen.AddWirid.crateRoute(husnaItem?.latin ?: ""))
                }
            )
        }
        composable(Screen.Yaasin.route){
            YaasinScreen(context = context)
        }
        composable(Screen.Doa.route){
            Doa_DoaScreen()
        }
        composable(Screen.Kitab.route){
            KitabScreen(
                context = context,
                navigateToDetailKitab = { kitabId ->
                    navController.navigate(Screen.DetailKitab.createRoute(kitabId))
                }
            )
        }
        val kitabArgument = "kitabId"
        composable(
            route = Screen.DetailKitab.route,
            arguments = listOf(navArgument(kitabArgument) {type = NavType.LongType})
        ){
            val kitabId = it.arguments?.getLong(kitabArgument) ?: -1L
            KitabDetailScreen(
                kitabId = kitabId.toInt(),
                context = context
            )
        }
        composable(Screen.Tahlil.route){
            TahlilScreen(context = context)
        }
        composable(Screen.Wirid.route){
            WiridScreen(
                context = context,
                navigateToDetailWirid ={wiridId ->
                    navController.navigate(Screen.DetailWirid.createRoute(wiridId))
                }
            )
        }
        composable(Screen.DoaHarian.route){
            DoaHarianScreen(
                navigateToDetailDoa = { doaId ->
                    navController.navigate(Screen.DetailDoaHarian.createRoute(doaId))
                },
                context = context)
        }
        val doaHarianArgument = "doaId"
        composable(
            route = Screen.DetailDoaHarian.route,
            arguments = listOf(navArgument(doaHarianArgument){type = NavType.LongType})
        ){
            val doaId = it.arguments?.getLong(doaHarianArgument) ?: -1L
            DoaHarianDetailScreen(
                doaId = doaId.toInt(),
                context = context,
            )
        }
        composable(
            route = Screen.AddWirid.route,
            arguments = listOf(navArgument("selectedWirid") { defaultValue = "" })
        ) { backStackEntry ->
            val selectedWirid = backStackEntry.arguments?.getString("selectedWirid") ?: ""
            AddWiridScreen(
                navController = navController,
                context = context,
                preselectedWirid = selectedWirid
            )
        }
        val wiridArgument = "wiridId"
        composable(
            route = Screen.DetailWirid.route,
            arguments = listOf(navArgument(wiridArgument) {type = NavType.LongType})
        ){
            val id = it.arguments?.getLong(wiridArgument) ?: -1L
            WiridDetailScreen(
                wiridId = id.toInt(),
                context = context,
                navController = navController
            )
        }
    }
}