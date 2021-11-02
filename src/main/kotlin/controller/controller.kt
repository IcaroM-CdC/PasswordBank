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
import androidx.compose.ui.res.painterResource
import model.Password

class MainController {

    public fun renderApp(APPNAME: String) = application {

        val windowsProps: Dimension = Toolkit.getDefaultToolkit().screenSize
        val windowWidth: Float = windowsProps.width.toFloat()
        val windowHeight: Float = windowsProps.width.toFloat()

        Window(
            onCloseRequest = ::exitApplication,
            title = APPNAME,
            icon = painterResource("drawable/icon.png"),
            state = WindowState(size = WindowSize(width = Dp(windowWidth), height = Dp(windowHeight)))
        ) {

            val data: MutableList<Password> = mutableListOf<Password>()

            val senha1 = Password(1, "icaro.senha.senha", "abc", "icaro.email@google.com", "Senha github")
            val senha2 = Password(2, "senha123", "abc", "joao.email@facebook.com", "Senha Facebook")
            val senha3 = Password(3, "IntelprocessorI5%", "abc", "bolsonaro@cloroquina.com", "Site do banco")
            data.add(senha1)
            data.add(senha2)
            data.add(senha3)

            val loginScreen = MainScreen(data)
            loginScreen.render()
        }
    }
}