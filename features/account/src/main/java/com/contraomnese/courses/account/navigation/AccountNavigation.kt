package com.contraomnese.courses.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.account.di.accountModule
import com.contraomnese.courses.account.presentation.AccountRoute
import com.contraomnese.courses.account.presentation.AccountViewModel
import com.contraomnese.courses.core.navigation.TopLevelDestination
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object AccountDestination: MviDestination

data class AccountTopLevelDestination(
    override val iconId: Int = com.contraomnese.courses.core.design.R.drawable.account,
    override val titleId: Int = com.contraomnese.courses.navigation.R.string.account,
    override val route: AccountDestination = AccountDestination,
    override val destinationRoute: String = AccountDestination::class.java.name
) : TopLevelDestination

fun NavController.navigateToAccount(navOptions: NavOptions? = null) =
    navigate(AccountDestination, navOptions)

interface AccountNavigator {
    fun onNavigateToAccount()
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.account(
    navigateToAccount: () -> Unit
) {
    composable<AccountDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(accountModule) }

        val viewModel: AccountViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        AccountRoute(
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
        )
    }

}