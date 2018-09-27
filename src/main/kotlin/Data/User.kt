package main.kotlin.Data

import org.springframework.data.annotation.Id

data class User(
        @Id
        var email: String,
        var UserName: String,
        var words: ArrayList<Definition>
)