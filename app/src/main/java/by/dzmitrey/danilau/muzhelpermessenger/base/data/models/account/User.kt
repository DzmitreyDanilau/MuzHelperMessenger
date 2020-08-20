package by.dzmitrey.danilau.muzhelpermessenger.base.data.models.account

data class User(
    val uId: String,
    val name: String,
    val email: String,
    val isAuthanticated: Boolean
)