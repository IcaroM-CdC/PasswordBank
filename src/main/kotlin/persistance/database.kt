package persistance

import java.sql.DriverManager
import java.sql.Connection
import java.sql.ConnectionBuilder

/* URL: "jdbc:sqlite:sample.db" */
/* ClassName "org.sqlite.JDBC" */

abstract class DatabaseConnection {

    abstract var URL: String
    abstract var className: String
    abstract var connection: Connection

    constructor(URL: String, className: String){
        this.URL = URL
        this.className = className
    }

    public fun startConnection(): Connection {
        try {
            Class.forName(className)
            connection = DriverManager.getConnection(URL)

        } catch (error: Exception) {
            error.printStackTrace()
            println("connection refused")
        }
        return connection
    }
}

abstract class DatabaseQueries {

    val initUserTable = """
        CREATE TABLE IF NOT EXISTS user (
            id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
            username TEXT NOT NULL,
            password TEXT NOT NULL
        );
    """.trimIndent()

    val initPasswordTable = """
        CREATE TABLE IF NOT EXISTS password (
            id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
            ownerID INTEGER NOT NULL,
            password TEXT NOT NULL,
            description TEXT NOT NULL,
            FOREIGN KEY(ownerID) REFERENCES user(id)
        );
    """.trimIndent()

    constructor(connection: Connection){
        val initUserTableQuery = connection.prepareStatement(initUserTable)
        val initPasswordTableQuery = connection.prepareStatement(initPasswordTable)

        initPasswordTableQuery.execute()
        initPasswordTableQuery.execute()
    }

    public fun fetchPasswordByUser(){

    }


}