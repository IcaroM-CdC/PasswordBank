package persistance

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException

import model.User
import model.Password

import java.util.*


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
