import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter


open class BaseApiTests {

    companion object {
        private var filtersInitialized = false

        init {
            if (!filtersInitialized) {
                RestAssured.baseURI = Config.getProperty("host")
                RestAssured.filters(
                    RequestLoggingFilter(),
                    ResponseLoggingFilter(),
                    AllureRestAssured()
                )
                filtersInitialized = true
            }
        }
    }
}