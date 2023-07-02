package tech.pacia.opencaching

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import tech.pacia.opencaching.features.map.MapScreen
import tech.pacia.opencaching.features.sign_in.SignInScreen
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
        }
    }
}