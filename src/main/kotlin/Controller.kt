package main.kotlin

import main.kotlin.Data.User
import main.kotlin.Data.UserDAO
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@CrossOrigin
@RestController
class Controller {

    @Autowired
    lateinit var requests: Requests

    @Autowired
    lateinit var userDao: UserDAO

    @Value("\${definitions.path}")
    private val definitionsPath: String? = null

    @Value("\${examples.path}")
    private val examplesPath: String? = null

    @GetMapping("/{userName}" + "/en" + "/{word}", produces = ["application/json"])
    fun getDefinition(@PathVariable userName: String, @PathVariable word: String): String {
        val response: JSONObject = requests.getWordData(word)
        userDao.addWord(userName, word)
        return response.toString()
    }

    @GetMapping("/{userName}", produces = ["application/json"])
    fun getUserData(@PathVariable userName: String): User {
        return userDao.getUserData(userName)
    }

    @GetMapping()
    fun test(): String {
        return "test"
    }

    @GetMapping("/{email}" + "/{userName}")
    fun createUser(@PathVariable email:String, @PathVariable userName: String): ResponseEntity<String> {
        userDao.createUser(email, userName)
        return ResponseEntity(HttpStatus.CREATED)
    }

    /*
        Converts Json data to usable ArrayList
     */
    fun parseJson(json: ArrayList<String>): ArrayList<String> {

        val list = ArrayList<String>()
        (0 until json.size).forEach {
            i -> list.add(json[i])
        }

        return list
    }


}