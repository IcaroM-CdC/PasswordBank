package persistance

import model.Password
import model.User

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException

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
            id           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
            ownerID      INTEGER NOT NULL,
            name         TEXT NOT NULL,
            username     TEXT NOT NULL,
            password     TEXT NOT NULL,
            description  TEXT NOT NULL,
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
                 INSERT INTO user (username, password) 
                 VALUES (?,?);
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
                SELECT * FROM user 
                WHERE username = ?
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

    public fun newPassword(password: Password, userID: Int, connection: Connection): Boolean {
        try {
            val newPasswordSQL = """
                INSERT INTO password (ownerID, name, username, password, description)
                VALUES (?,?,?,?,?);
            """.trimIndent()

            val newPasswordQuery = connection.prepareStatement(newPasswordSQL)

            newPasswordQuery.setInt(1, userID)
            newPasswordQuery.setString(2, password.getName())
            newPasswordQuery.setString(3, password.getUsername())
            newPasswordQuery.setString(4, password.getPassword())
            newPasswordQuery.setString(5, password.getDescription())

            newPasswordQuery.execute()

            return true

        } catch (error: SQLException){
            return false
        }
    }

    public fun listPasswords(user: User, connection: Connection): MutableList<Password> {
        try {
            val leftJoinPasswordSQL = """
                SELECT password.id, 
                       password.ownerID, 
                       password.password, 
                       password.description, 
                       password.name, 
                       password.username
                FROM password LEFT JOIN user 
                ON password.ownerID = user.id 
                WHERE user.username = ?
            """.trimIndent()

            val leftJoinPasswordQuery = connection.prepareStatement(leftJoinPasswordSQL)
            leftJoinPasswordQuery.setString(1, user.getUsername())
            val result = leftJoinPasswordQuery.executeQuery()

            val data: MutableList<Password> = mutableListOf<Password>()

            /*
                while the result of query contain a password, the loop run filling
                the list os passwords, and finnaly return the data which contains
                all fetched passwords.
            */

            while (result.next()){
                var id = result.getInt("id")
                var password = result.getString("password")
                var description = result.getString("description")
                var username = result.getString("username")
                var name = result.getString("name")

                var newPassword: Password = Password(id, password, description, username, name)

                data.add(newPassword)
            }
            return data

        } catch (error: SQLException) {
            val emptyData: MutableList<Password> = mutableListOf<Password>()
            return emptyData
        }
    }

    public fun deletePassword(passwordID: Int, connection: Connection) {
        try {
            val deletePasswordSQL = """
                DELETE FROM password
                WHERE password.id = ?
            """.trimIndent()

            val deletePasswordQuery = connection.prepareStatement(deletePasswordSQL)
            println(passwordID)
            deletePasswordQuery.setInt(1, passwordID)
            deletePasswordQuery.executeUpdate()

            println("foi")

        } catch (error: SQLException) {
            println(error)
        }
    }
}