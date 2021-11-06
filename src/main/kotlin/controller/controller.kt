package controller

import androidx.compose.runtime.Composable
import view.LoginScreen
import view.MainScreen

import java.awt.Toolkit
import java.awt.Dimension
import java.sql.Connection

import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.res.painterResource

import model.Password
import model.User

import persistance.DatabaseConnection
import persistance.Queries

class MainController {

    private lateinit var connection: Connection
    private lateinit var queries: Queries

    public fun renderApp(APPNAME: String, DATABASE_URL: String, CLASSNAME: String) = application {

        val windowsProps: Dimension = Toolkit.getDefaultToolkit().screenSize
        val windowWidth: Float = windowsProps.width.toFloat()
        val windowHeight: Float = windowsProps.width.toFloat()

        Window(
            onCloseRequest = ::exitApplication,
            title = APPNAME,
            icon = painterResource("drawable/icon.png"),
            state = WindowState(size = WindowSize(width = Dp(windowWidth), height = Dp(windowHeight)))
        ) {

            connection = DatabaseConnection(URL = DATABASE_URL, className = CLASSNAME).startConnection()

            queries = Queries()
            queries.init(connection)

            renderMainScreen()
        }
    }

    @Composable
    private fun renderMainScreen(){

        var user = User("Icaro", "123456789")
        var data = queries.listPasswords(user, connection)

        val mainScreen = MainScreen(data)
        mainScreen.render()

    }
}