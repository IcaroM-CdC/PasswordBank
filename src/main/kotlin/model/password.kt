package model

class Password {

    private val id: Int
    private val arrayId: Int
    private val username: String
    private val name: String
    private val password: String
    private val description: String

    constructor(id: Int = 0, arrayId: Int = 0, password: String, description: String, username: String, name: String){

        this.id = id
        this.arrayId = arrayId
        this.username = username
        this.name = name
        this.password = password
        this.description = description
    }

    public fun getID(): Int {
        return this.id
    }
    public fun getArrayId(): Int {
        return this.arrayId
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