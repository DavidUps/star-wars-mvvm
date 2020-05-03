package com.davidups.starwars.features.authentication.models.data

import com.davidups.starwars.features.authentication.models.entity.UserEntity
import com.davidups.starwars.features.authentication.models.view.UserView

data class User (val email: String, val password: String) {

    fun toUserView() = UserView(email, password)
    fun toUserEntity() = UserEntity(email, password)
}