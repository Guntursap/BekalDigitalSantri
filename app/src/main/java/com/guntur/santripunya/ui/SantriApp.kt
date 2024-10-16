package com.guntur.santripunya.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guntur.santripunya.ui.navigation.AppDrawer
import com.guntur.santripunya.ui.navigation.AppNavGraph
import com.guntur.santripunya.ui.navigation.NavigationAction
import com.guntur.santripunya.ui.navigation.Screen
import com.guntur.santripunya.ui.theme.SantriPunyaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SantriApp(
    context: Context,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navigationActions = remember(navController) {
        NavigationAction(navController)
    }
    val suratName = navBackStackEntry?.arguments?.getString("suratName") ?: ""
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                navigateToHome = navigationActions.navigateToHome ,
                navigateToAbout = navigationActions.navigateToAbout ,
                closeDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Box() {
            // TopAppBar
            AppNavGraph(
                context = context,
                navController = navController,
                startDestination = Screen.Home.route,
            )
            CenterAlignedTopAppBar(
                title = {
                    when (currentRoute) {
                        Screen.Home.route -> {
                            Text("")
                        }
                        Screen.AddWirid.route -> {
                            Text(
                                text = "Add Wirid",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Screen.DetailKitab.route -> {
                            Text(
                                text = "Detail Kitab",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Screen.DetailWirid.route -> {
                            Text(
                                text = "DetailWirid",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Screen.DetailDoaHarian.route -> {
                            Text(
                                text = "Detail Doa",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Screen.Al_quranDetail.route -> {
                            Text(text = suratName)
                        }
                        else -> {
                            Text(
                                text = currentRoute,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                },
                navigationIcon = {
                    if (currentRoute == Screen.Home.route){
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            modifier = Modifier
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color.White, Color.DarkGray)
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }else{
                        IconButton(
                            onClick = {
                                scope.launch {
                                    navController.navigateUp()
                                }
                            },
                            modifier = Modifier
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color.Transparent, Color.White)
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (currentRoute == Screen.Wirid.route)
                        IconButton(
                            onClick = {
                                scope.launch {
                                    navController.navigate(Screen.AddWirid.route)
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SkripsiAppPreview() {
    SantriPunyaTheme {
        SantriApp(context = LocalContext.current)
    }
}