package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.itemHeight80
import com.contraomnese.courses.core.navigation.TopLevelDestination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun NavBar(
    currentDestination: String?,
    destinations: ImmutableList<TopLevelDestination>,
    onNavigateToTopLevel: (topRoute: Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = Modifier.height(itemHeight80),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        destinations.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = stringResource(item.titleId)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.titleId),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                selected = currentDestination == item.destinationRoute,
                onClick = { onNavigateToTopLevel(item.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    CoursesTheme {
        val destination = object : TopLevelDestination {
            override val iconId: Int
                get() = com.contraomnese.courses.core.design.R.drawable.home
            override val titleId: Int
                get() = com.contraomnese.courses.navigation.R.string.home
            override val route: Any
                get() = ""
            override val destinationRoute: String
                get() = ""
        }

        NavBar(
            currentDestination = null,
            destinations = persistentListOf(destination),
            onNavigateToTopLevel = {})
    }
}