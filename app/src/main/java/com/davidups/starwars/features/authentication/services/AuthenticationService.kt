package com.davidups.starwars.features.authentication.services

import com.davidups.starwars.core.functional.Result
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.authentication.models.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth

class AuthenticationService {

    fun signIn(user: UserEntity): Result<Boolean> {
        val auth = FirebaseAuth.getInstance()
        return Success(auth.createUserWithEmailAndPassword(user.email,user.password).isSuccessful)
    }

    fun signUp(): Result<Boolean> {
        val auth = FirebaseAuth.getInstance()
        return Success(auth.signInWithEmailAndPassword("","").isSuccessful)
    }
}