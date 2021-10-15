package model

class Password {
    private val password: String
    private val description: String

    constructor(password: String, description: String){
        this.password = password
        this.description = description
    }

    public fun getPassword(): String {
        return this.password
    }
    public fun getDescription(): String {
        return this.description
    }
}