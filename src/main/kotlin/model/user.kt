package model

class User {
    private val username: String
    private val password: String

    constructor(username: String, password: String){
        this.username = username
        this.password = password
    }

    public fun getUsername(): String {
        return this.username
    }
    public fun getPassword(): String {
        return this.password
    }
}