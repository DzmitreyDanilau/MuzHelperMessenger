package by.dzmitrey.danilau.muzhelpermessenger.network.responses.base

import com.squareup.moshi.Json

open class BaseResponse(

    @Json(name = "success")
    val success: Int,

    @Json(name = "message")
    val message: String
)