package com.contraomnese.courses.core.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.contraomnese.courses.core.design.R
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.itemWidth48
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@Composable
fun CircleGlassIcon(
    modifier: Modifier = Modifier,
    description: String,
    @DrawableRes icon: Int,
    isFilled: Boolean = false,
    hazeState: HazeState,
    onClick: () -> Unit = {},
) {

    val hazeStyle = HazeStyle(
        backgroundColor = Color.Black,
        tints = listOf(HazeTint(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))),
        blurRadius = 8.dp,
        noiseFactor = 0.05f,
    )

    Box(
        modifier = modifier
            .background(Color.Transparent)
            .hazeSource(hazeState, zIndex = 1f)
            .clip(CircleShape)
            .clickable { onClick() }
            .hazeEffect(state = hazeState) {
                blurEnabled = true
                style = hazeStyle
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(0.5f),
            painter = painterResource(icon),
            contentDescription = description,
            tint = if (isFilled) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground,
        )
    }

}

@Preview
@Composable
private fun CircleIconPreview() {
    CoursesTheme {

        Box {
            val hazeState = rememberHazeState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hazeSource(hazeState, zIndex = 0f)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFF98509),
                                Color(0xFF731EBD),
                                Color(0xFFF95D00),
                            )
                        )
                    )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    text = "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n" +
                            "Glass Card Glass Card Glass Card\n",
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,

                    )
            }

            CircleGlassIcon(
                modifier = Modifier
                    .size(itemWidth48),
                description = "vocibus",
                icon = R.drawable.favorite,
                isFilled = true,
                hazeState = hazeState
            )
        }

    }
}