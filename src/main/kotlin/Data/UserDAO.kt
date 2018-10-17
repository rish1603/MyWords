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

    /**
     * Used to add word to user collection when initial query is made
     */
    fun addWord(userName: String, word: String) {
        val user: User = repository.findByUserName(userName)
        val definitions = ArrayList<String>()
        val definition = Definition(word, "", definitions)
        user.words.add(definition)
        repository.save(user)
    }

    fun addWord(userName: String, word: String, definitions: ArrayList<String>):Definition  {
        val user: User = repository.findByUserName(userName)
        val definition = Definition(word, "", definitions)
        user.words.add(definition)
        repository.save(user)
        return definition
    }

    fun getUserData(userName: String):User {
        return repository.findByUserName(userName)
    }

}