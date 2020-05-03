package com.davidups.starwars.features.authentication.models.view

import com.davidups.skell.core.extensions.empty
import com.davidups.starwars.features.authentication.models.data.User
import java.io.Serializable

data class UserView (val email: String, val password: String): Serializable {

    companion object {
        fun empty() = UserView(String.empty(), String.empty())
    }

    fun toUser() = User(email, password)
}