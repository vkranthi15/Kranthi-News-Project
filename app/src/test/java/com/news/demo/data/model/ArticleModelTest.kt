package com.news.demo.data.model

import org.junit.After
import org.junit.Assert
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test


class ArticleModelTest {
    private val title = "Testing Title"
    private val author = "author"
    private val url = "www.google.com/image"
    private val urlToImage = "urlToImage"

    @Mock
    var articleModel: ArticleModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(articleModel?.title).thenReturn(title)
        Mockito.`when`(articleModel?.url).thenReturn(url)
        Mockito.`when`(articleModel?.urlToImage).thenReturn(urlToImage)
        Mockito.`when`(articleModel?.author).thenReturn(author)
    }

    @Test
    fun testNewsTitle() {
        Mockito.`when`(articleModel?.title).thenReturn(title)
        Assert.assertEquals("Testing Title", articleModel?.title)
    }

    @Test
    fun testNewsUrl() {
        Mockito.`when`(articleModel?.url).thenReturn(url)
        Assert.assertEquals("Testing url", articleModel?.url)
    }

    @Test
    fun testNewsArticleUrl() {
        Mockito.`when`(articleModel?.urlToImage).thenReturn(urlToImage)
        Assert.assertEquals("www.google.com/image", articleModel?.urlToImage)
    }

    @Test
    fun testNewsAuthor() {
        Mockito.`when`(articleModel?.author).thenReturn(author)
        Assert.assertEquals("Testing byline", articleModel?.author)
    }


    @Test
    fun testNewsTitleIncorrect() {
        Mockito.`when`(articleModel?.title).thenReturn(title)
        Assert.assertEquals("Testing Title", articleModel?.title)
    }

    @Test
    fun testNewsUrlIncorrect() {
        Mockito.`when`(articleModel?.url).thenReturn(url)
        Assert.assertEquals("Testing url", articleModel?.url)
    }

    @Test
    fun testNewsArticleUrlIncorrect() {
        Mockito.`when`(articleModel?.urlToImage).thenReturn(urlToImage)
        Assert.assertEquals("www.google.com/image", articleModel?.urlToImage)
    }

    @Test
    fun testNewsAuthorIncorrect() {
        Mockito.`when`(articleModel?.author).thenReturn(author)
        Assert.assertEquals("Testing byline", articleModel?.author)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        articleModel = null
    }
}