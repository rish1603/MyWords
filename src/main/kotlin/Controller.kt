package main.kotlin

import main.kotlin.Data.Definition
import main.kotlin.Data.User
import main.kotlin.Data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.data.mongodb.core.query.Update




@RestController
class Controller {

    @Autowired
    lateinit var requests: Requests

    @Autowired
    lateinit var repository: UserRepository

    @RequestMapping("/")
    fun index(): User {

        val definitions = arrayListOf(
                Definition("string", "I own a string", "a thin wiry thing"),
                Definition("int", "I have no int", "whole number")
        )



        repository.save(User("rish1603@gmail.com","rish1603", definitions))
        val user: User = repository.findByEmail("rish1603@gmail.com")
        user.words.add(Definition("pls work","pls work","pls work"))
        repository.save(user)

        return user
    }

    @GetMapping("/en" + "/{word}")
    fun getDefinition(@PathVariable word: String): String {
        return requests.getWordData(word).toString()
    }

}