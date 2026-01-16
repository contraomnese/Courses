package com.contraomnese.courses.core.ui.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.contraomnese.courses.core.design.theme.itemHeight60
import com.contraomnese.courses.core.design.theme.space4
import com.contraomnese.courses.core.design.theme.space8

private enum class Direction { Left, Right }

@Composable
fun MovingBoard(
    modifier: Modifier = Modifier,
    items: List<Int>
) {
    val highlightedItemsSet = remember(items) {

        val countToHighlight = (items.size * 0.25).toInt().coerceAtLeast(1)

        items
            .shuffled()
            .take(countToHighlight)
            .toSet()
    }

    val rows = remember(items) {
        items.chunked(6).take(5)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space8)
    ) {
        rows.forEachIndexed { index, rowItems ->
            MovingBoardRow(
                items = rowItems,
                highlightedItemsSet = highlightedItemsSet,
                direction = if (index % 2 == 0) Direction.Left else Direction.Right,
                speedMultiplier = 1f + index * 0.25f
            )
        }
    }
}

@Composable
private fun MovingBoardRow(
    items: List<Int>,
    highlightedItemsSet: Set<Int>,
    direction: Direction,
    speedMultiplier: Float
) {
    val density = LocalDensity.current
    val screenWidthPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

    var rowWidth by remember { mutableFloatStateOf(0f) }

    val transition = rememberInfiniteTransition(label = "board_row")
    val targetOffset = if (rowWidth > 0) rowWidth else screenWidthPx / 2

    val offsetX by transition.animateFloat(
        initialValue = if (direction == Direction.Left) 0f else -targetOffset,
        targetValue = if (direction == Direction.Left) -targetOffset else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (20_000 / speedMultiplier).toInt(),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "offsetX"
    )

    Row(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.Start, unbounded = true)
            .onSizeChanged { rowWidth = it.width.toFloat() }
            .graphicsLayer {
                translationX = offsetX
            },
        horizontalArrangement = Arrangement.spacedBy(space4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val itemsToDisplay = items + items

        itemsToDisplay.forEach { textRes ->
            val isHighlighted = highlightedItemsSet.contains(textRes)

            val containerColor = if (isHighlighted) {
                MaterialTheme.colorScheme.secondaryContainer
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }

            val contentColor = if (isHighlighted) {
                MaterialTheme.colorScheme.onSecondaryContainer
            } else {
                MaterialTheme.colorScheme.onPrimaryContainer
            }

            BoardSection(
                modifier = Modifier
                    .height(itemHeight60)
                    .graphicsLayer {
                        if (isHighlighted) {
                            rotationZ = 10f
                            translationY = 20f
                        }
                    },
                text = textRes,
                containerColor = containerColor,
                contentColor = contentColor
            )
        }
    }
}