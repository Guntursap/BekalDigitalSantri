package com.guntur.santripunya.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guntur.santripunya.R
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun AppDrawer(
    navigateToHome: () -> Unit,
    navigateToAbout: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        drawerContainerColor = Color.White,

        modifier = modifier
            .fillMaxWidth(0.75f)

    ) {
        Box{
            Image(painter = painterResource(id = R.drawable.masque2) , contentDescription = "")
            IconButton(
                onClick = { closeDrawer() })
            {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "")
            }
        }
        Spacer(modifier = modifier.padding(8.dp))
        NavigationDrawerItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                   },
            label = { Text(text = "Share") },
            selected = false,
            onClick = { navigateToHome(); closeDrawer() },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.White
            )
//            modifier = Modifier.background(Color.White)
        )
        NavigationDrawerItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text(text = "About") },
            selected = false,
            onClick = { navigateToAbout(); closeDrawer() },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.White
            )
//            modifier = Modifier.background(Color.White)
        )
        NavigationDrawerItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.privacy),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text(text = "Privacy Police") },
            selected = false,
            onClick = { navigateToAbout(); closeDrawer() },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.White
            )
//            modifier = Modifier.background(Color.White)
        )
        NavigationDrawerItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.rate),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text(text = "Rate us") },
            selected = false,
            onClick = { navigateToAbout(); closeDrawer() },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.White
            )
//
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppDrawerPreview() {
    SantriPunyaTheme {
        AppDrawer(
            navigateToHome = { /*TODO*/ },
            navigateToAbout = { /*TODO*/ },
            closeDrawer = { /*TODO*/ })
    }
}