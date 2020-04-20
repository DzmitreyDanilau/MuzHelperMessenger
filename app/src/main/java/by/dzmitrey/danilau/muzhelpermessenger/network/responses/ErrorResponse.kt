package by.dzmitrey.danilau.muzhelpermessenger.network.responses

import com.squareup.moshi.Json

data class ErrorResponse(

    @Json(name = "code")
    val code: String,

    @Json(name = "message")
    val message: String

)