package com.example.demo.model.dto.user

import java.io.Serializable
import javax.validation.constraints.NotBlank

data class UserResource(
    @NotBlank
    var userId: String = "",
    @NotBlank
    var password: String = ""
) : Serializable

