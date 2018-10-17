package main.kotlin

import com.jayway.jsonpath.JsonPath
import main.kotlin.Data.Definition
import main.kotlin.Data.User
import main.kotlin.Data.UserDAO
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

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