package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandler
import com.contraomnese.courses.core.design.theme.cornerRadius32
import com.contraomnese.courses.core.design.theme.padding32
import com.contraomnese.courses.core.design.theme.space16

@Composable
fun SocialMediaSection(uriHandler: UriHandler) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding32),
        horizontalArrangement = Arrangement.spacedBy(space16)
    ) {
        RoundedIcon(
            modifier = Modifier
                .background(Color(0xFF2683ED))
                .weight(1f),
            radius = cornerRadius32,
            icon = com.contraomnese.courses.core.design.R.drawable.vk,
            description = "go_to_vk"
        ) {
            uriHandler.openUri("https://vk.com/")
        }
        RoundedIcon(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFF98509),
                            Color(0xFFF95D00),
                        )
                    )
                )
                .weight(1f),
            radius = cornerRadius32,
            icon = com.contraomnese.courses.core.design.R.drawable.ok,
            description = "go_to_ok"
        ) {
            uriHandler.openUri("https://ok.ru/")
        }
    }
}