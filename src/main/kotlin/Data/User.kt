package main.kotlin.Data

import org.springframework.data.annotation.Id

data class User(
        @Id
        var email: String,
        var userName: String,
        var words: ArrayList<Definition>
)