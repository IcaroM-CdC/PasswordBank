import controller.MainController

//import org.w3c.dom.events.EventListener

const val APPNAME: String = "passwordBank"

fun main(){
    MainController().renderApp(APPNAME)
}

/* URL: "jdbc:sqlite:sample.db" */
/* ClassName "org.sqlite.JDBC" */