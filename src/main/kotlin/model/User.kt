package model

class User {

    private val username: String
    private val password: String
    val isAdmin: Boolean

    constructor(username: String, password: String){
        this.username = username
        this.password = password
        this.isAdmin = true
    }
}