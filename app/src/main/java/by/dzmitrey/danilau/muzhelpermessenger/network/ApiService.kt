package by.dzmitrey.danilau.muzhelpermessenger.network

import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("register.php")
    fun register(): Call<RegisterResponse>

}