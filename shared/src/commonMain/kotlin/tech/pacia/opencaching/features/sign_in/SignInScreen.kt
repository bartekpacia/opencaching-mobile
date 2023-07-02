package tech.pacia.opencaching.features.sign_in

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import tech.pacia.opencaching.navigation.MapPage
import tech.pacia.opencaching.navigation.NavigationStack
import tech.pacia.opencaching.navigation.Page

@Composable
fun SignInScreen(navStack: NavigationStack<Page>) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }
    var showPassword by remember { mutableStateOf(false) }

    val localFocusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = { localFocusManager.clearFocus() }
            )
        },
        topBar = { TopAppBar(title = { Text("Opencaching") }) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "Username"
                        )
                    },
                )

                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = password,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = "Password",
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { showPassword = !showPassword }
                        ) {
                            Icon(
                                imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "xd"
                            )
                        }
                    }
                )

                Button(
                    onClick = { navStack.push(MapPage()) },
                    modifier = Modifier.fillMaxWidth(fraction = 0.75f).padding(8.dp)

                ) {
                    Text("Sign in")
                }

                Button(
                    onClick = {
                        count++
                    }
                ) {
                    Text("Count: $count")
                }
            }
        }
    }
}