
import model.User
import model.Password
import persistance.Queries
import persistance.DatabaseConnection

/* URL: "jdbc:sqlite:sample.db" */
/* ClassName "org.sqlite.JDBC" */

fun main(){

    val icaro = User("pedro", "icaro")



    val database = DatabaseConnection("jdbc:sqlite:sample.db", "org.sqlite.JDBC")
    val DBqueries = Queries()

    val connection = database.startConnection()

    DBqueries.init(connection)
//    DBqueries.newUser(icaro, connection)
    DBqueries.verifyUser(icaro, connection)
}