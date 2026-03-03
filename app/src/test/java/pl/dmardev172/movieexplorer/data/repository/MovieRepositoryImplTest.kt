package pl.dmardev172.movieexplorer.data.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import pl.dmardev172.movieexplorer.data.remote.OmdbApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.test.assertEquals

class MovieRepositoryImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: OmdbApi
    private lateinit var repository: MovieRepositoryImpl

    private val fakeApiKey = "test_key"

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(OmdbApi::class.java)
        repository = MovieRepositoryImpl(api, fakeApiKey)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `searchMovies returns mapped movies correctly`() = runTest {
        // Mock search response
        val searchResponse = """
            {
              "Search": [
                {
                  "imdbID": "tt0372784",
                  "Title": "Batman Begins",
                  "Year": "2005",
                  "Type": "movie",
                  "Poster": "poster_url"
                }
              ],
              "Response": "True"
            }
        """.trimIndent()

        // Mock details response
        val detailsResponse = """
            {
              "imdbID": "tt0372784",
              "imdbRating": "8.2"
            }
        """.trimIndent()

        // Order has meaning (repo makes two requests)
        mockWebServer.enqueue(MockResponse().setBody(searchResponse))
        mockWebServer.enqueue(MockResponse().setBody(detailsResponse))

        val result = repository.searchMovies("batman")

        assertEquals(1, result.size)

        val movie = result.first()

        assertEquals("tt0372784", movie.imdbId)
        assertEquals("Batman Begins", movie.title)
        assertEquals("2005", movie.year)
        assertEquals("movie", movie.type)
        assertEquals("poster_url", movie.poster)
        assertEquals("8.2", movie.rating)
    }
}