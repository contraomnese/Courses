package com.contraomnese.courses.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius16
import com.contraomnese.courses.core.design.theme.itemHeight40
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.design.theme.padding16
import com.contraomnese.courses.core.design.theme.padding8
import com.contraomnese.courses.core.design.theme.space16
import com.contraomnese.courses.core.design.theme.space8
import com.contraomnese.courses.core.design.theme.thickness1
import com.contraomnese.courses.core.ui.composition.LocalCoursesBackgrounds
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.widgets.CourseCardProgress
import com.contraomnese.courses.core.ui.widgets.LoadingIndicator
import com.contraomnese.courses.core.ui.widgets.MenuSection
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import com.contraomnese.courses.ui.R
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    eventFlow: Flow<ProfileEvent>,
    pushAction: (ProfileAction) -> Unit,
    onLogOut: () -> Unit,
) {

    val context = LocalContext.current
    val snackBarHostState = LocalSnackbarHostState.current

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    eventFlow.collectEvent { event ->
        when (event) {
            is ProfileEvent.HandleError -> snackBarHostState.showSnackbar(
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
                ProfileScreen(
                    uiState = uiState,
                    pushAction = pushAction,
                    onLogOut = onLogOut
                )
            }
        }
    }
}

@Composable
private fun ProfileScreen(
    uiState: ProfileState,
    pushAction: (ProfileAction) -> Unit,
    onLogOut: () -> Unit,
) {
    val backgrounds = LocalCoursesBackgrounds.current

    val coursesMock by remember {
        mutableStateOf(
            (100L..104L).map {id ->
                Course(
                    id = id,
                    title = "title$id",
                    description = "description$id",
                    price = "price$id",
                    rate = id.toFloat(),
                    startDate = "startDate$id",
                    publishDate = "publishDate$id"
                )
            }
        )
    }

    Column(
        modifier = Modifier.padding(horizontal = padding16)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = padding16)
                .align(Alignment.Start),
            text = stringResource(R.string.profile_title),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Surface(
            modifier = Modifier
                .padding(
                    bottom = padding16
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(cornerRadius16),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = padding16, vertical = padding8)
            ) {
                MenuSection(
                    modifier = Modifier
                        .height(itemHeight40),
                    text = R.string.support_title
                )
                Divider()
                MenuSection(
                    modifier = Modifier
                        .height(itemHeight40),
                    text = R.string.settings_title
                )
                Divider()
                MenuSection(
                    modifier = Modifier
                        .height(itemHeight40),
                    text = R.string.logout_title,
                    onClicked = {
                        onLogOut()
                    }
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(vertical = padding16)
                .align(Alignment.Start),
            text = stringResource(R.string.courses_title),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space16),
        ) {
            items(coursesMock, key = { course -> course.id }) { course ->
                CourseCardProgress(
                    image = backgrounds.getValue(course.id),
                    title = course.title,
                    rate = course.rate,
                    publishDate = course.publishDate,
                    isFavorite = false,
                    onFavoriteClick = { }
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
private fun Divider() {
    HorizontalDivider(
        modifier = Modifier
            .height(thickness1)
            .background(MaterialTheme.colorScheme.outline)
    )
}

@Preview(locale = "ru")
@Composable
private fun AccountScreenPreview() {
    CoursesTheme {
        ProfileScreen(
            uiState = ProfileState.DEFAULT,
            pushAction = {},
            onLogOut = {}
        )
    }
}
