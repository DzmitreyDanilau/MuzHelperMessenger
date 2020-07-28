package by.dzmitrey.danilau.muzhelpermessenger.base.data.models.account

data class User(
    val ui: String,
    val name: String,
    val email: String,
    val isAuthanticated: Boolean
)