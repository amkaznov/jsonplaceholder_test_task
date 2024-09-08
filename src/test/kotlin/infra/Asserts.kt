package infra

import io.qameta.allure.Allure
import io.restassured.response.Response
import kotlin.test.assertTrue
import kotlin.test.assertNotNull


object Asserts {
    fun <T : Any>  notNull(actual: T?, message: String? = null){
        Allure.step("${message}", Allure.ThrowableRunnableVoid {
            assertNotNull(actual,message)})
    }
    fun toBeTrue(actual: Boolean, message: String? = null){
        Allure.step("${message}", Allure.ThrowableRunnableVoid {
            assertTrue(actual,message)
        })
    }
    fun statusCode(response: Response,httpStatus: Int){
        val receivedStatus: Int = response.statusCode()
        val expectedStatus: Int = httpStatus
        Allure.step("Ожидаемый статус ${expectedStatus}, полученный статус ${receivedStatus}", Allure.ThrowableRunnableVoid {
            assertTrue(receivedStatus.equals(expectedStatus))
        })

    }

}