import infra.Asserts
import infra.Endpoints
import infra.Request
import io.qameta.allure.Allure
import io.restassured.response.Response
import models.CommentModel
import models.PostModel
import org.apache.http.HttpStatus
import kotlin.test.Test


class PostsApiTests : BaseApiTests() {

    //PS по хорошему перед каждым тестом надо бы генерировать и создавать сущьности а после удалять но здесь это опущено так как это мок

    @Test
    fun `GET all posts`() {
        val posts:List<PostModel> = Request.get(Endpoints.POSTS)
        Asserts.notNull(posts, "Список постов не null")
        Asserts.toBeTrue(posts.isNotEmpty(), "Количество постов > 0")
        Asserts.toBeTrue(posts.size == 100, "Количество постов = 100")
    }

    @Test
    fun `GET post by id`() {
        val postId = 1
        val post: PostModel = Request.get(Endpoints.POST, postId)
        Asserts.notNull(post.title, "Title не должен быть null")
        Asserts.notNull(post.body, "Body не должен быть null")
        Asserts.notNull(post.id, "id не должен быть null")
        Asserts.toBeTrue(post.id == postId, "PostId должен быть равен $postId")
    }
    @Test
    fun `GET post by nonValid id`() {
        val postId = 0
        val response: Response = Request.getUncheck(Endpoints.POST, postId)
            .extract()
            .response()
        Asserts.statusCode(response, HttpStatus.SC_NOT_FOUND)
        Asserts.toBeTrue(response.asString() == "{}", "Ответ должен быть пустым объектом {}")
    }

    @Test
    fun `GET all post's comments by post id`() {
        val postId = 1
        val comments: List<CommentModel> = Request.get(Endpoints.POST_COMMENTS, postId)
        if (comments.isNotEmpty()) {
            for (comment in comments) {
                Allure.step("Проверка комментария с id ${comment.id}", Allure.ThrowableRunnableVoid {
                    Asserts.notNull(comment.email, "Email не должен быть null")
                    Asserts.notNull(comment.name, "Name не должен быть null")
                    Asserts.notNull(comment.body, "Body не должен быть null")
                    Asserts.notNull(comment.id, "id не должен быть null")
                    Asserts.notNull(comment.postId, "PostId не должен быть null")
                    Asserts.toBeTrue(comment.postId == postId, "PostId комментария должен быть равен $postId")
                })

            }
        }
    }

    @Test
    fun `GET all comments`() {
        val comments:List<CommentModel> = Request.get(Endpoints.COMMENTS)
        Asserts.notNull(comments, "Список постов не null")
        Asserts.toBeTrue(comments.isNotEmpty(), "Количество постов > 0")
        Asserts.toBeTrue(comments.size == 500, "Количество постов = 500")
    }

    @Test
    fun `GET all comments with parameter post id`() {
        val postId = 1
        val queryParams = mapOf("postId" to postId)
        val comments: List<CommentModel> = Request.get(Endpoints.COMMENTS, queryParams = queryParams)
        if (comments.isNotEmpty()) {
            for (comment in comments) {
                Allure.step("Проверка комментария с id ${comment.id}", Allure.ThrowableRunnableVoid {
                    Asserts.notNull(comment.email, "Email не должен быть null")
                    Asserts.notNull(comment.name, "Name не должен быть null")
                    Asserts.notNull(comment.body, "Body не должен быть null")
                    Asserts.notNull(comment.id, "id не должен быть null")
                    Asserts.notNull(comment.postId, "PostId не должен быть null")
                    Asserts.toBeTrue(comment.postId == postId, "PostId комментария должен быть равен $postId")
                })
            }
        }
    }
    @Test
    fun `POST create post`() {
        val newPost = PostModel(
            title = "New Post Title",
            body = "This is the body of the new post",
            userId = 1,
        )
        val createdPost: PostModel = Request.post(Endpoints.POST, newPost)
        Asserts.notNull(createdPost.id, "id не должен быть null")
        // проверки на соответствие передаваемых данных полученным
    }
    // Тесты на создание поста с невалидными данными

    @Test
    fun `PUT update post`() {
        val postId = 1
        val updatePost = PostModel(
            title = "Neww Postt Titlee",
            body = "Thiss iss thee bodyy off thee neww posts",
            userId = 10,
        )
        val createdPost: PostModel = Request.put(Endpoints.POST, postId, updatePost)
        // проверки на соответствие передаваемых данных полученным
    }
    // Тесты на рбновление поста с невалидными данными
    // Тест на обновление поста с несуществующем id

    @Test
    fun `PATCH update post`() {
        val postId = 1
        val updatedPost = mapOf(
            "title" to "Updated Post Title"
        )
        val createdPost: PostModel = Request.patch(Endpoints.POST, postId, updatedPost)
        // проверки на соответствие передаваемых данных полученным
    }
    // Тесты на рбновление поста с невалидными данными
    // Тест на обновление поста с несуществующем id

    @Test
    fun `DELETE post`() {
        val postId = 1
        val response = Request.delete(Endpoints.POST, postId)
        // проверки что пост удален
    }
    // Тест на удаление поста с несуществующем id (повторный запрос на удаление)
}