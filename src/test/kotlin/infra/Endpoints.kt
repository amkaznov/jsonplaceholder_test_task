package infra

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import models.CommentModel
import models.PostModel

enum class Endpoints(val url: String, val secondUrl: String, val responseType: Type) {
    POSTS("/posts", "", object : TypeToken<List<PostModel>>() {}.type),
    POST("/posts", "", PostModel::class.java),
    POST_COMMENTS("/posts", "/comments", object : TypeToken<List<CommentModel>>() {}.type),
    COMMENTS("/comments", "", object : TypeToken<List<CommentModel>>() {}.type)
}