package com.example.newsapiclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.text.Charsets.UTF_8

class NewsAPIServiceTest {
    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(StandardCharsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=22036bed1704494c806bc215fb1832d7")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articlesList = responseBody!!.articles
            val article = articlesList[0]
            assertThat(article.author).isEqualTo("Jacqueline Alemany, Tom Hamburger")
            assertThat(article.url).isEqualTo("https://www.washingtonpost.com/politics/jan-6-committee-clark-bannon/2021/10/13/2468292e-2c2a-11ec-8ef6-3ca8fe943a92_story.html")
            assertThat(article.publishedAt).isEqualTo("2021-10-13T18:21:57Z")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}