package infra

import com.google.gson.Gson
import io.qameta.allure.Allure
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import org.apache.http.HttpStatus.*

object Request {
    private val gson = Gson()

    fun <T> get(endpoint: Endpoints, id: Any? = null, queryParams: Map<String, Any>? = null): T {
        val response: Response = getUncheck(endpoint, id, queryParams)
            .extract()
            .response()
        Asserts.statusCode(response, SC_OK)
        return gson.fromJson(response.asString(), endpoint.responseType)
    }

    fun getUncheck(endpoint: Endpoints, id: Any? = null, queryParams: Map<String, Any>? = null): ValidatableResponse {
        val url = buildUrl(endpoint, id)
        var response: ValidatableResponse? = null
        Allure.step("Запрос GET $url", Allure.ThrowableRunnableVoid {
            response = RestAssured.given()
                .apply {
                    queryParams?.let {
                        queryParams(it)
                    }
                }
                .get(url)
                .then()
                .assertThat()
        })
        return response!!
    }

    fun <T> post(endpoint: Endpoints, body: Any? = null): T {
        val response: Response = postUncheck(endpoint, body)
            .extract()
            .response()
        Asserts.statusCode(response,SC_CREATED)
        return gson.fromJson(response.asString(), endpoint.responseType)
    }

    fun postUncheck(endpoint: Endpoints, body: Any? = null): ValidatableResponse {
        val url = endpoint.url
        var response: ValidatableResponse? = null

        Allure.step("Запрос POST ${url}", Allure.ThrowableRunnableVoid {
            response = RestAssured.given()
                .apply {
                    body?.let {
                        contentType("application/json")
                        body(it)
                    }
                }
                .post(url)
                .then()
                .assertThat()
        })

        return response!!
    }


    fun <T> put(endpoint: Endpoints, id: Any, body: Any? = null): T {
        val response: Response = putUncheck(endpoint, id, body)
            .extract()
            .response()
        Asserts.statusCode(response, SC_OK)
        return gson.fromJson(response.asString(), endpoint.responseType)
    }

    fun putUncheck(endpoint: Endpoints, id: Any, body: Any? = null): ValidatableResponse {
        val url = buildUrl(endpoint, id)
        var response: ValidatableResponse? = null
        Allure.step("Запрос PUT ${url}", Allure.ThrowableRunnableVoid {
            response = RestAssured.given()
                .apply {
                    body?.let {
                        contentType("application/json")
                        body(it)
                    }
                }
                .put(url)
                .then()
                .assertThat()
        })
        return response!!
    }


    fun <T> patch(endpoint: Endpoints, id: Any, body: Any? = null): T {
        val response: Response = patchUncheck(endpoint, id, body)
            .extract()
            .response()
        Asserts.statusCode(response, SC_OK)
        return gson.fromJson(response.asString(), endpoint.responseType)
    }

    fun patchUncheck(endpoint: Endpoints, id: Any, body: Any? = null): ValidatableResponse {
        val url = buildUrl(endpoint, id)
        var response: ValidatableResponse? = null
        Allure.step("Запрос PATCH ${url}", Allure.ThrowableRunnableVoid {
            response = RestAssured.given()
                .apply {
                    body?.let {
                        contentType("application/json")
                        body(it)
                    }
                }
                .patch(url)
                .then()
                .assertThat()
        })
        return response!!
    }

    fun delete(endpoint: Endpoints, id: Any) {
        val response: Response =deleteUncheck(endpoint, id)
            .extract()
            .response()
        Asserts.statusCode(response, SC_OK)
    }

    fun deleteUncheck(endpoint: Endpoints, id: Any): ValidatableResponse {
        val url = buildUrl(endpoint, id)
        var response: ValidatableResponse? = null
        Allure.step("Запрос DELETE ${url}", Allure.ThrowableRunnableVoid {
            response = RestAssured.given()
                .delete(url)
                .then()
                .assertThat()
        })
        return response!!
    }

    private fun buildUrl(endpoint: Endpoints, id: Any?): String {
        val baseUrl = if (id != null) "${endpoint.url}/$id" else endpoint.url
        return "$baseUrl${endpoint.secondUrl}"
    }
}