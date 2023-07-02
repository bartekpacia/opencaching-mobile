package tech.pacia.opencaching.android

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tech.pacia.opencaching.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SignInScreenPreviewLight() {
    MyApplicationTheme {
        App()
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignInScreenPreviewDark() {
    MyApplicationTheme {
        App()
    }
}