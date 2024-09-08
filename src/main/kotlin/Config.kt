import java.io.IOException
import java.util.Properties

object Config {

    private const val CONFIG_PROPERTIES = "config.properties"
    private val properties: Properties = Properties()

    init {
        loadProperties(CONFIG_PROPERTIES)
    }

    private fun loadProperties(fileName: String) {
        try {
            val stream = Config::class.java.classLoader.getResourceAsStream(fileName)
            if (stream == null) {
                println("File not found $fileName")
            } else {
                properties.load(stream)
            }
        } catch (e: IOException) {
            println("Error during file reading $fileName")
            throw RuntimeException(e)
        }
    }

    @JvmStatic
    fun getProperty(key: String): String? {
        return properties.getProperty(key)
    }

}
