package com.contraomnese.courses.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.itemHeight20
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.design.theme.padding16
import com.contraomnese.courses.core.design.theme.padding4
import com.contraomnese.courses.core.design.theme.space16
import com.contraomnese.courses.core.design.theme.space8
import com.contraomnese.courses.core.ui.composition.LocalCoursesBackgrounds
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.composition.coursesBackgrounds
import com.contraomnese.courses.core.ui.widgets.CourseCard
import com.contraomnese.courses.core.ui.widgets.LoadingIndicator
import com.contraomnese.courses.core.ui.widgets.SearchTextField
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.coroutines.flow.Flow
import com.contraomnese.courses.core.design.R as Design
import com.contraomnese.courses.ui.R as UI

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel,
    eventFlow: Flow<HomeEvent>,
    pushAction: (HomeAction) -> Unit,
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
                HomeScreen(
                    uiState = uiState,
                    pushAction = pushAction,
                )
            }
        }
    }
}

@Composable
private fun HomeScreen(
    uiState: HomeState,
    pushAction: (HomeAction) -> Unit
) {
    Column {
        SearchBar()
        Favorites(
            uiState.favorites,
            uiState.courses,
            pushAction
        )
    }

}

@Composable
private fun SearchBar() {
    Row(
        modifier = Modifier
            .padding(padding16)
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space8),
    ) {

        SearchTextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            searchQuery = "",
            onSearchQueryChanged = { },
            isError = false,
            placeholder = stringResource(
                UI.string.search
            ),
            leadingIcon = null
        )

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    Design.drawable.filter
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun Favorites(
    favorites: ImmutableSet<Long>,
    courses: ImmutableList<Course>,
    pushAction: (HomeAction) -> Unit = {},
) {
    val backgrounds = LocalCoursesBackgrounds.current
    val listState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }

    LaunchedEffect(courses) {
        listState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = padding16)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space16)
    ) {
        FilterButton(
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    pushAction(HomeAction.SortByPublishDate)

                }
        )
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space16),
        ) {
            items(courses, key = { course -> course.id }) { course ->
                val isFavorite = course.id in favorites
                CourseCard (
                    image = backgrounds.getValue(course.id),
                    title = course.title,
                    description = course.description,
                    price = course.price,
                    rate = course.rate,
                    publishDate = course.publishDate,
                    isFavorite = isFavorite,
                    onFavoriteClick = {
                        if (isFavorite) {
                            pushAction(HomeAction.RemoveFavorite(course.id))
                        }
                        else {
                            pushAction(HomeAction.AddFavorite(course.id))
                        }
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
}

@Composable
private fun FilterButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(UI.string.by_date_filter),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Box(
            modifier = Modifier.size(itemHeight20),
        ) {
            Image(
                modifier = Modifier
                    .padding(padding4)
                    .fillMaxSize(),
                painter = painterResource(Design.drawable.arrows),
                contentDescription = null,
            )
        }
    }
}

@Preview(locale = "ru")
@Composable
private fun HomeScreenPreview() {
    CoursesTheme {
        CompositionLocalProvider(
            LocalCoursesBackgrounds provides coursesBackgrounds
        ) {
            HomeScreen(
                uiState = HomeState.DEFAULT,
                pushAction = {},
            )
        }
    }
}
