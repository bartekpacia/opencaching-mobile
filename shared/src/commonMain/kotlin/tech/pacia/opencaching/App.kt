package tech.pacia.opencaching

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import tech.pacia.opencaching.features.geocache.GeocacheScreen
import tech.pacia.opencaching.features.map.MapScreen
import tech.pacia.opencaching.features.sign_in.SignInScreen
import tech.pacia.opencaching.navigation.GeocachePage
import tech.pacia.opencaching.navigation.MapPage
import tech.pacia.opencaching.navigation.NavigationStack
import tech.pacia.opencaching.navigation.Page
import tech.pacia.opencaching.navigation.SignInPage

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    val navigationStack = rememberSaveable(
        saver = listSaver<NavigationStack<Page>, Page>(
            restore = { NavigationStack(*it.toTypedArray()) },
            save = { it.stack },
        ),
        init = {
            NavigationStack(SignInPage())
        },
    )

    AnimatedContent(
        targetState = navigationStack.lastWithIndex(),
    ) { (_, page) ->
        when (page) {
            is SignInPage -> {
                SignInScreen(navStack = navigationStack)
            }

            is MapPage -> {
                MapScreen(navStack = navigationStack)
            }

            is GeocachePage -> {
                GeocacheScreen(navStack = navigationStack)
            }
        }
    }
}