package com.contraomnese.courses.features.favorites.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.design.theme.padding16
import com.contraomnese.courses.core.design.theme.space16
import com.contraomnese.courses.core.design.theme.space8
import com.contraomnese.courses.core.ui.composition.LocalCoursesBackgrounds
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.composition.coursesBackgrounds
import com.contraomnese.courses.core.ui.widgets.CourseCard
import com.contraomnese.courses.core.ui.widgets.LoadingIndicator
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import com.contraomnese.courses.ui.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

@Composable
internal fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    eventFlow: Flow<HomeEvent>,
    pushAction: (FavoritesAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val snackBarHostState = LocalSnackbarHostState.current

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    eventFlow.collectEvent { event ->
        when (event) {
            is HomeEvent.HandleError -> snackBarHostState.showSnackbar(
                message = event.cause.handleError(context),
                duration = SnackbarDuration.Short
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading ->
                LoadingIndicator(
                    Modifier
                        .align(Alignment.Center)
                        .width(itemWidth64)
                )

            else -> {
                FavoritesScreen(
                    uiState = uiState,
                    pushAction = pushAction,
                )
            }
        }
    }
}

@Composable
private fun FavoritesScreen(
    uiState: FavoritesState,
    pushAction: (FavoritesAction) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = padding16)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = padding16)
                .align(Alignment.Start),
            text = stringResource(R.string.favorites_title),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Favorites(
            uiState.favorites,
            pushAction
        )
    }
}

@Composable
private fun Favorites(
    favorites: ImmutableList<Course>,
    pushAction: (FavoritesAction) -> Unit = {},
) {
    val backgrounds = LocalCoursesBackgrounds.current
    val listState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }

    LazyColumn(
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space16),
    ) {
        items(favorites, key = { favorite -> favorite.id }) { course ->
            CourseCard(
                image = backgrounds.getValue(course.id),
                title = course.title,
                description = course.description,
                price = course.price,
                rate = course.rate,
                publishDate = course.publishDate,
                isFavorite = true,
                onFavoriteClick = {
                    pushAction(FavoritesAction.RemoveFavorite(course.id))
                }
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(space8)
            )
        }
    }
}

@Preview(locale = "ru")
@Composable
private fun FavoritesScreenPreview() {
    CoursesTheme {
        CompositionLocalProvider(
            LocalCoursesBackgrounds provides coursesBackgrounds
        ) {
            FavoritesScreen(
                uiState = FavoritesState.DEFAULT,
                pushAction = {},
            )
        }
    }
}
