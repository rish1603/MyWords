package main.kotlin

import khttp.responses.Response
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Requests {

    @Value("\${app.id}")
    private val appID: String? = null

    @Value("\${app.key}")
    private val appKey: String? = null

    @Value("\${base.url}")
    private val url: String? = null

    private val lang = "en/"

    fun getWordData(word: String): JSONObject {
        val response : Response = khttp.get(url.toString() + lang + word, headers= mapOf("app_id" to appID, "app_key" to appKey) as Map<String, String>)
        val wordData : JSONObject = response.jsonObject
        return wordData
    }

}