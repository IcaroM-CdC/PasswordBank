package controller

import view.LoginScreen
import view.MainScreen

import java.awt.Toolkit
import java.awt.Dimension

import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.unit.Dp

class MainController {

    public fun renderApp(APPNAME: String) = application {

        val windowsProps: Dimension = Toolkit.getDefaultToolkit().screenSize
        val windowWidth: Float = windowsProps.width.toFloat()
        val windowHeight: Float = windowsProps.width.toFloat()

        Window(
            onCloseRequest = ::exitApplication,
            title = APPNAME,
            state = WindowState(size = WindowSize(width = Dp(windowWidth), height = Dp(windowHeight)))
        ) {

            val loginScreen = MainScreen()
            loginScreen.render()
        }
    }
}