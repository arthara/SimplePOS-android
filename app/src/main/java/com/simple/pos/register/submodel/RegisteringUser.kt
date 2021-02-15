package com.simple.pos.register.submodel

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.User

class RegisteringUser(username: String, passwordConfirmation: String, user: User): User(user.email, user.password) {
    @SerializedName("password_confirmation")
    var passwordConfirmation: String

    init{
        this.username = username
        this.passwordConfirmation = passwordConfirmation
    }
}