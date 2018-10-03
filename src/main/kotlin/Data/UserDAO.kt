package main.kotlin.Data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.ArrayList

@Component
class UserDAO {

    @Autowired
    lateinit var repository: UserRepository

    fun createUser(email: String, userName: String) {
        val definitions = ArrayList<Definition>()
        repository.save(User(email,userName, definitions))
    }

    fun addWord(userName: String, word: String, definitions: ArrayList<String>) {
        val user: User = repository.findByUserName(userName)
        user.words.add(Definition(word, "", definitions))
        repository.save(user)
    }

    fun getUserData(userName: String) :User {
        return repository.findByUserName(userName)
    }

}