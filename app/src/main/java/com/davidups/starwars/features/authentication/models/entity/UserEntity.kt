package com.davidups.starwars.features.authentication.models.entity

import androidx.annotation.VisibleForTesting
import com.davidups.skell.core.extensions.empty

data class UserEntity (val email: String, val password: String) {

    companion object {
        fun empty() = UserEntity(String.empty(), String.empty())
    }

    fun toUser() = UserEntity(email, password)
}