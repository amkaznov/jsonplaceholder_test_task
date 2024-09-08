import infra.Endpoints
import infra.Request
import models.PostModel
import kotlin.test.Test

class GetTopWords: BaseApiTests() {
    @Test
    fun `Get top words`() {
        val posts:List<PostModel> = Request.get(Endpoints.POSTS)
        getTopWords(posts)
    }
    private fun getTopWords(posts: List<PostModel>) {
        val wordFrequency = HashMap<String, Int>()
        for (post in posts) {
            val words = post.body.split("\\s+".toRegex())
            for (word in words) {
                val cleanedWord = word.lowercase().replace(Regex("[^a-zA-Z0-9']"), "")
                if (cleanedWord.isNotEmpty()) {
                    wordFrequency[cleanedWord] = wordFrequency.getOrDefault(cleanedWord, 0) + 1
                }
            }
        }

        val topWords = wordFrequency.entries
            .sortedByDescending { it.value }
            .take(10)

        println("Top 10 Words by Frequency:")
        topWords.forEachIndexed { index, entry ->
            println("${index + 1}. ${entry.key} - ${entry.value}")
        }
    }
}