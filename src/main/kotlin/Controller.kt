package main.kotlin

import com.jayway.jsonpath.JsonPath
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

    @GetMapping("/{userName}" + "/en" + "/{word}")
    fun getDefinition(@PathVariable userName: String, @PathVariable word: String): String {
        val response: JSONObject = requests.getWordData(word)
        val senses: ArrayList<String> = JsonPath.read(response.toString(), definitionsPath)

        val definitions = ArrayList<String>()

        (0 until senses.size).forEach {
            i -> definitions.add(senses[i])
        }
        userDao.addWord(userName, word, definitions)

        return ""
    }

    @GetMapping("/{userName}", produces = ["application/json"])
    fun getUserData(@PathVariable userName: String): User {
        return userDao.getUserData(userName)
    }


}