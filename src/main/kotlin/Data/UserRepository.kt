package main.kotlin.Data


import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

@Component
interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): User
}