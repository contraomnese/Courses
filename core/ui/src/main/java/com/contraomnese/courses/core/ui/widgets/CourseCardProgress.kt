package com.contraomnese.courses.core.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius16
import com.contraomnese.courses.core.design.theme.elevation4
import com.contraomnese.courses.core.design.theme.itemHeight32
import com.contraomnese.courses.core.design.theme.padding10
import com.contraomnese.courses.core.design.theme.padding16
import com.contraomnese.courses.core.design.theme.padding4
import com.contraomnese.courses.core.design.theme.padding6
import com.contraomnese.courses.core.design.theme.padding8
import com.contraomnese.courses.core.design.theme.padding84
import com.contraomnese.courses.ui.R
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import com.contraomnese.courses.core.design.R as Design

@Composable
fun CourseCardProgress(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    rate: Float,
    publishDate: String,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
) {
    val hazeState = rememberHazeState()

    Card(
        shape = RoundedCornerShape(cornerRadius16),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation4),
        modifier = modifier
            .fillMaxWidth()
            .height(204.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val customAlignment = Alignment { size, space, layoutDirection ->
                IntOffset(
                    x = ((space.width - size.width) * 0.2f).toInt(),
                    y = ((space.height - size.height) * 0.2f).toInt()
                )
            }

            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = BiasAlignment(-0.6f, -0.6f),
                modifier = Modifier
                    .hazeSource(hazeState, zIndex = 0f)
                    .clip(RoundedCornerShape(cornerRadius16))
                    .fillMaxWidth()
                    .height(120.dp)
            )
            CircleGlassIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp, top = 8.dp)
                    .size(itemHeight32),
                description = "add to favorite",
                icon = if (isFavorite) Design.drawable.favorite_inside else Design.drawable.favorite_outline,
                hazeState = hazeState,
                isFilled = isFavorite,
                onClick = onFavoriteClick
            )
            GlassContainer(
                modifier = Modifier
                    .padding(start = padding8, top = padding84)
                    .wrapContentWidth()
                    .height(26.dp)
                    .align(Alignment.TopStart),
                hazeState = hazeState
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            horizontal = padding6,
                            vertical = padding4
                        )
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding4)
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Image(
                            painter = painterResource(com.contraomnese.courses.core.design.R.drawable.star),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = rate.toString(),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

            }
            GlassContainer(
                modifier = Modifier
                    .padding(start = 58.dp, top = padding84)
                    .wrapContentWidth()
                    .height(26.dp)
                    .align(Alignment.TopStart),
                hazeState = hazeState
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            horizontal = padding6,
                            vertical = padding4
                        ),
                    text = publishDate,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = padding16, start = padding16, end = padding16),
                verticalArrangement = Arrangement.spacedBy(padding10)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
private fun CourseCardProgressPreview() {

    CoursesTheme {
        CourseCard(
            image = R.drawable.courses100,
            title = "Java-разработчик с нуля",
            description = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет создавать 3D-модели, текстуры и анимации, а также может строить карьеру в геймдеве, кино, рекламе или дизайне.",
            price = "999",
            rate = 4.9f,
            publishDate = "2024-05-22",
            isFavorite = false,
            onFavoriteClick = {}
        )
    }
}