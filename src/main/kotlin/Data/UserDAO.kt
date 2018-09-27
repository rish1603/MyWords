package main.kotlin.Data

import org.springframework.beans.factory.annotation.Autowired
import java.util.ArrayList



class UserDAO {

    @Autowired
    lateinit var repository: UserRepository

    fun createUser(email: String, userName: String) {
        val definitions = ArrayList<Definition>()
        repository.save(User(email,userName, definitions))
    }

}