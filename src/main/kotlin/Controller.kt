package main.kotlin

import main.kotlin.Data.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    @Autowired
    lateinit var requests: Requests

    @Autowired
    lateinit var userDao: UserDAO

    @GetMapping("/{userName}" + "/en" + "/{word}")
    fun getDefinition(@PathVariable userName: String, @PathVariable word: String): String {
        userDao.addWord(userName, word, "definition")
        return "something"
    }

}