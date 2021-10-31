package model

class Password {

    private val id: Int
    private val username: String
    private val name: String
    private val password: String
    private val description: String

    constructor(id: Int, password: String, description: String, username: String, name: String){

        this.id = id
        this.username = username
        this.name = name
        this.password = password
        this.description = description
    }

    public fun getID(): Int {
        return this.id
    }
    public fun getName(): String {
        return this.name
    }
    public fun getUsername(): String {
        return this.username
    }
    public fun getPassword(): String {
        return this.password
    }
    public fun getDescription(): String {
        return this.description
    }
}