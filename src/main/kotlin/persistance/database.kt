package persistance

import model.Password
import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException

import model.User

import persistance.PasswordList
import java.util.*

/* URL: "jdbc:sqlite:sample.db" */
/* ClassName "org.sqlite.JDBC" */

class DatabaseConnection {

    private var URL: String
    private var className: String

    constructor(URL: String, className: String){
        this.URL = URL
        this.className = className
    }

    public fun startConnection(): Connection {
        try {
            Class.forName(className)
            var connection: Connection = DriverManager.getConnection(URL)
            return connection

        } catch (error: Exception) {
            error.printStackTrace()
            println("connection refused")

            var emptyConnection: Connection =  DriverManager.getConnection("")
            return emptyConnection
        }
    }
}

class Queries {

    val initUserTableSQL = """
        CREATE TABLE IF NOT EXISTS user (
            id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
            username TEXT NOT NULL UNIQUE,
            password TEXT NOT NULL
        );
    """.trimIndent()

    val initPasswordTableSQL = """
        CREATE TABLE IF NOT EXISTS password (
            id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
            ownerID INTEGER NOT NULL,
            password TEXT NOT NULL,
            description TEXT NOT NULL,
            FOREIGN KEY(ownerID) REFERENCES user(id)
        );
    """.trimIndent()


    public fun init(connection: Connection){

        val initUserTableQuery = connection.prepareStatement(initUserTableSQL)
        val initPasswordTableQuery = connection.prepareStatement(initPasswordTableSQL)

        initUserTableQuery.execute()
        initPasswordTableQuery.execute()
    }

    public fun newUser(user: User, connection: Connection): Boolean {

        try {
            val newUserSQL = """
                 INSERT INTO user (username, password) VALUES (?,?);
            """.trimIndent()

            val newUserQuery = connection.prepareStatement(newUserSQL)

            newUserQuery.setString(1, user.getUsername())
            newUserQuery.setString(2, user.getPassword())
            newUserQuery.execute()

            return true

        } catch (error: SQLException){
            return false
        }
    }

    public fun verifyUser(user: User, connection: Connection): Boolean{

        try {
            val requestedUserSQL = """
                SELECT * FROM user WHERE username = ?
            """.trimIndent()

            val requestedUserQuery = connection.prepareStatement(requestedUserSQL)

            requestedUserQuery.setString(1, user.getUsername())

            val result = requestedUserQuery.executeQuery()

            val nameResult = result.getString("username")
            val passwordResult = result.getString("password")

            if (user.getUsername() == nameResult && user.getPassword() == passwordResult){
                return true
            }
            else {
                return false
            }

        } catch (error: SQLException){
            return false
        }
    }

    public fun newPassword(password: Password, userID: Int, connection: Connection) {

    }

    public fun listPasswords(user: User, connection: Connection): PasswordList {
        try {
            val leftJoinPasswordSQL = """
                SELECT password.id, password.ownerID, password.password, password.description 
                FROM password LEFT JOIN user 
                ON password.ownerID = user.id 
                WHERE username = ?
            """.trimIndent()

            val leftJoinPasswordQuery = connection.prepareStatement(leftJoinPasswordSQL)
            leftJoinPasswordQuery.setString(1, user.getUsername())
            val result = leftJoinPasswordQuery.executeQuery()

            val data = PasswordList()

            while (result.next()){
                var password = result.getString("password")
                var description = result.getString("description")

                data.passwordList.add(password)
                data.descriptionList.add(description)
            }
            return data

        } catch (erro: SQLException){
            val emptyData = PasswordList()
            return emptyData
        }
    }

    public fun deletePassword() {
        
    }
}
