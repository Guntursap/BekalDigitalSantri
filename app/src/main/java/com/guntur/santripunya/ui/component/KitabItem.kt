package com.guntur.santripunya.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guntur.santripunya.R
import com.guntur.santripunya.ui.theme.SantriPunyaTheme

@Composable
fun KitabItem(
    modifier: Modifier = Modifier,
    title: String,
    idImage: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = idImage),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(35.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(8.dp)
                .weight(14f)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KitabItemPreview() {
    SantriPunyaTheme {
        KitabItem(
            idImage = R.drawable.imrity, title = "biar tambah cinta karna kalau sa bilang sa takan berpindah karana su sanyang guntur saputra dari tanngerang")
    }
}