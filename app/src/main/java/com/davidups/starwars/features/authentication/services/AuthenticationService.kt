package com.davidups.starwars.features.authentication.services

import android.content.ContentValues.TAG
import android.util.Log
import com.davidups.starwars.core.extensions.Constants
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.features.authentication.models.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AuthenticationService {

    fun signIn(user: UserEntity): State<Boolean> {
        val auth = FirebaseAuth.getInstance()
        return Success(auth.createUserWithEmailAndPassword(user.email, user.password).isSuccessful)
    }

    fun signUp(user: UserEntity): State<Boolean> {
        val auth = FirebaseAuth.getInstance()
        return Success(auth.signInWithEmailAndPassword(user.email, user.password).isSuccessful)
    }

    fun putUser(user: UserEntity): State<Boolean> {
        val firebaseFirestore = FirebaseFirestore.getInstance()

        return Success(firebaseFirestore.collection(Constants.Firestore.Collections.USER).add(user).isSuccessful)
    }

    fun getUserReference(): DocumentReference {
        return FirebaseFirestore
            .getInstance()
            .collection(Constants.Firestore.Collections.USER)
            .document("EarGnZlTNuTXlZCCbyVa")
    }
}