package com.contraomnese.courses.core.ui.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder

@Composable
fun PhotoImage(
    modifier: Modifier = Modifier,
    url: String,
) {

    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .animateContentSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = Builder(context)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = "High quality preview",
        )
    }
}

