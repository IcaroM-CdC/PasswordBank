import controller.MainController
import java.awt.desktop.AppHiddenListener

//import org.w3c.dom.events.EventListener

val APPNAME: String = "passwordBank"
val DATABASE_URL: String = "jdbc:sqlite:sample.db"
val CLASSNAME: String = "org.sqlite.JDBC"

fun main(){
    MainController().renderApp(APPNAME, DATABASE_URL, CLASSNAME)
}
