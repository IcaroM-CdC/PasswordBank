package controller

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.defaultMinSize
import java.awt.Toolkit
import java.awt.Dimension

import java.sql.Connection

import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import model.Password
import model.User

import view.MainScreen

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
            state = WindowState(size = WindowSize(width = Dp(windowWidth), height = Dp(windowHeight))),

        ) {

            connection = DatabaseConnection(URL = DATABASE_URL, className = CLASSNAME).startConnection()

            queries = Queries()
            queries.init(connection)

//            Modifier.defaultMinSize(minHeight = (windowHeight * 0.60).dp)

            renderMainScreen()


        }
    }

    @Composable
    private fun renderMainScreen(){

        var user = User("icaro", "123")
        var data = queries.listPasswords(user, connection)

//        println(data[0].getArrayId())
//        println(data[0].getID())

        val mainScreen = MainScreen(data)
        mainScreen.render()

        if (mainScreen.getCreateNewPasswordClickState() == true){

            val password: Password = mainScreen.getNewPassword()

            queries.newPassword(password, 1, connection)

            mainScreen.setCreateNewPasswordClickState(false)
        }

        if (mainScreen.getDeletePasswordClickState() == true){

            val ID: Int = mainScreen.getCurrentPasswordId()
            println(ID)

            queries.deletePassword(ID, connection)

            mainScreen.setDeletePassWordClickState(false)
        }
    }
}
