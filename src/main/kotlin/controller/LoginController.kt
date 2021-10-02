package controller

class LoginController {

    private val username: String
    private val password: String

    constructor(){
        this.username = ""
        this.password = ""
    }

    fun handleLogin(username: String, password: String): Boolean{

        println(username)
        println(password)

        return true
    }
}