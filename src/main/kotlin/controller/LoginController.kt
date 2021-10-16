package controller

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import view.LoginScreen

class LoginController {

    public fun renderLoginPage() = application {
        Window(onCloseRequest = ::exitApplication) {
            val loginScreen = LoginScreen()
            loginScreen.render()
        }
    }

}