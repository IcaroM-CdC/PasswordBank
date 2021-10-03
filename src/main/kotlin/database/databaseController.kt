//package database
//import java.sql.Connection
//import java.sql.DriverManager
//import java.sql.SQLException
//
//class DatabaseDriverConnection {
//    public fun connect(){
//        try {
//            val newConnection: Connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite")
//
//            println("conexão estabelecida")
//
//        } catch (e: SQLException){
//            println("aa")
//            println(e.message)
//        }
//    }
//}
//
//fun main(){
//
//    val DB = DatabaseDriverConnection()
//    DB.connect()
//}