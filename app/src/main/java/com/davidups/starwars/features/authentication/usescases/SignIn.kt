package com.davidups.starwars.features.authentication.usescases

import com.davidups.starwars.core.functional.Result
import com.davidups.skell.core.interactor.UseCase
import com.davidups.starwars.features.authentication.models.entity.UserEntity

class SignIn(private val authenticationRepository: AuthenticationRepository): UseCase<Result<Boolean>, SignIn.Params>() {

    override fun run(params: Params?) = authenticationRepository.signIn(params!!.user)

    class Params(var user: UserEntity)

}