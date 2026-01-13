package com.contraomnese.courses.core.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerSize16
import com.contraomnese.courses.core.design.theme.elevation4
import com.contraomnese.courses.core.design.theme.itemHeight18
import com.contraomnese.courses.core.design.theme.padding10
import com.contraomnese.courses.core.design.theme.padding16
import com.contraomnese.courses.core.design.theme.padding2
import com.contraomnese.courses.core.design.theme.padding4
import com.contraomnese.courses.core.design.theme.padding6
import com.contraomnese.courses.core.design.theme.padding8
import com.contraomnese.courses.core.design.theme.padding84
import com.contraomnese.courses.ui.R
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    description: String,
    price: String,
    rate: Float,
    publishDate: String,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
) {
    val hazeState = rememberHazeState()


    Card(
        shape = RoundedCornerShape(cornerSize16),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation4),
        modifier = modifier
            .fillMaxWidth()
            .height(236.dp)
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

            SubcomposeAsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .hazeSource(hazeState, zIndex = 0f)
                    .clip(RoundedCornerShape(cornerSize16))
                    .fillMaxWidth()
                    .fillMaxHeight(0.483f),
                alpha = 0.5f,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.surface,
                                        MaterialTheme.colorScheme.surfaceVariant,
                                        MaterialTheme.colorScheme.surface,
                                    )
                                )
                            )
                    ) {}
                },
                alignment = customAlignment
            )
            CircleGlassIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp, top = 8.dp)
                    .size(40.dp),
                description = "add to favorite",
                icon = com.contraomnese.courses.core.design.R.drawable.favorite,
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

                Text(
                    modifier = Modifier.
                        padding(top = padding2),
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "$price ".plus(stringResource(R.string.rub)),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(
                        modifier = Modifier
                            .clickable {  },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.read_more),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Box(
                            modifier = Modifier.size(itemHeight18),
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(padding4)
                                    .fillMaxSize(),
                                painter = painterResource(com.contraomnese.courses.core.design.R.drawable.arrow),
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CourseItemPreview() {

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