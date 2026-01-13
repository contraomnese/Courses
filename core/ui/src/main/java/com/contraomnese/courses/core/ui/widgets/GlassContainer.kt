package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius16
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState


@Composable
fun GlassContainer(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = cornerRadius16,
    hazeState: HazeState,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val shape: Shape = RoundedCornerShape(cornerRadius)

    val hazeStyle = HazeStyle(
        backgroundColor = Color.Black,
        tints = listOf(HazeTint(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))),
        blurRadius = 8.dp,
        noiseFactor = -1f,
    )

    Box(
        modifier = modifier
            .background(Color.Transparent)
            .hazeSource(hazeState, zIndex = 1f)
            .clip(shape)
            .hazeEffect(state = hazeState) {
                blurEnabled = true
                style = hazeStyle
            },
    ) {
        content()
    }
}

@Preview(locale = "ru")
@Composable
private fun GlassContainerPreview() {

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


            GlassContainer(
                modifier = Modifier
                    .height(160.dp),
                hazeState = hazeState
            ) {
                Text(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    text = "Not Glass",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
        }

    }
}