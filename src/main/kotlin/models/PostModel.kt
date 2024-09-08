package models

import kotlinx.serialization.Serializable

@Serializable
data class PostModel(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val body: String
)