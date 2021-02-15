package com.simple.pos.shared.model

open class User(var email: String, var password: String) {
    var id: Int = -1
    var username: String? = null
}