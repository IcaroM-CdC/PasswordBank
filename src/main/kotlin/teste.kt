
import model.User
import model.Password
import persistance.Queries
import persistance.DatabaseConnection

/* URL: "jdbc:sqlite:sample.db" */
/* ClassName "org.sqlite.JDBC" */

fun main(){

    val icaro = User("icaro", "icaro")

    val database = DatabaseConnection("jdbc:sqlite:src/sample.db", "org.sqlite.JDBC")
    val DBqueries = Queries()

    val connection = database.startConnection()

    DBqueries.init(connection)
//    DBqueries.newUser(icaro, connection)
//    DBqueries.verifyUser(icaro, connection)

    val data: MutableList<Password>
    data = DBqueries.listPasswords(icaro, connection)

    println(data.size)

}