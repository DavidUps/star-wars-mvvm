package com.davidups.starwars.core.extensions

class Constants {

    companion object {
        const val DELAY_ONE_SECOND = 1_000L
        const val VW_TAG = "viewmodel"
    }

    class Firestore{
        class Collections {
            companion object {
                const val USER = "User"
            }
        }
    }

    class People {
        companion object {
            const val PERSON = "person"
        }
    }
}