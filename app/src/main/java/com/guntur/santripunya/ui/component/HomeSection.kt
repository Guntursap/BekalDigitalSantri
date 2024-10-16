package com.guntur.santripunya.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeSection(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
){
    Column {
        Text(
            text = title,
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        content()
    }
}