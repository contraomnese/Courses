package com.contraomnese.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.padding32
import com.contraomnese.courses.core.ui.composition.boardCourses
import com.contraomnese.courses.core.ui.widgets.MovingBoard
import com.contraomnese.courses.navigation.CoursesHost
import kotlinx.coroutines.delay
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        actionBar?.hide()
        setContent {
            CoursesTheme {
                KoinAndroidContext {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onFinish: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MovingBoard(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = padding32),
            items = boardCourses
        )
    }

    LaunchedEffect(Unit) {
        delay(3000)
        onFinish()
    }
}

@Composable
internal fun CoursesApp(viewModel: MainActivityViewModel = koinViewModel()) {

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    Crossfade(targetState = uiState.isLoading, animationSpec = tween(1000)) { loading ->
        if (loading) {
            SplashScreen {
                viewModel.push(MainActivityAction.AnimationFinished)
            }
        } else {
            CoursesHost(
                startDestination = uiState.startDestination,
                eventFlow = viewModel.eventFlow,
                pushAction = viewModel::push
            )
        }
    }
}
