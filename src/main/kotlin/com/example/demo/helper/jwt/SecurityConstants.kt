package com.example.demo.helper.jwt

val SIGN_UP_URL = "/api/auth"
val SECRET = "SecretKeyToGenJWTs"
val TOKEN_PREFIX = "Bearer "
val HEADER_STRING = "Authorization"
val EXPIRATION_TIME: Long = 864_000_000 // 10 days
