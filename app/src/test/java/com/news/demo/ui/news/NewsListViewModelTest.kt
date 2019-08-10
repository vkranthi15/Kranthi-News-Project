package com.news.demo.ui.news


import org.junit.Rule
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import org.mockito.Mock
import android.arch.lifecycle.LifecycleRegistry
import com.news.demo.data.NewsServices
import com.news.demo.data.repository.NewsRepository
import com.task.utils.AppConstants
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`


class NewsListViewModelTest {
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var apiEndPoint: NewsServices? = null
    @Mock
    var apiClient: NewsRepository? = null
    private var viewModel: NewsListViewModel? = null
    @Mock
    var observer: Observer<NewsListState>? = null
    @Mock
    var lifecycleOwner: LifecycleOwner? = null
    var lifecycle: Lifecycle? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner!!)
        viewModel = NewsListViewModel(apiClient)
        viewModel!!.stateLiveData.observeForever(observer!!)
    }

    @Test
    fun testNull() {
        `when`(apiClient?.getTopNews(country = AppConstants.country, category = AppConstants.country, apiKey = AppConstants.API_KEY))
                .thenReturn(null)
        assertNotNull(viewModel?.stateLiveData)
        viewModel?.stateLiveData?.hasObservers()?.let { assertTrue(it) }
    }

    @Test
    fun testApiFetchDataSuccess() {
        // Mock API response
        `when`(apiClient?.getTopNews(country = AppConstants.country, category = AppConstants.country, apiKey = AppConstants.API_KEY)).thenReturn(Single.just(emptyList()))
        viewModel?.getNewsList()

        verify(observer).onChanged(LoadingState(emptyList(), false))
        verify(observer).onChanged(DefaultState(emptyList(), false))

    }

    @Test
    fun testApiFetchDataError() {
        `when`(apiClient?.getTopNews(country = AppConstants.country, category = AppConstants.country, apiKey = AppConstants.API_KEY)).thenReturn(Single.just(emptyList()))
        viewModel?.getNewsList()
        verify(observer).onChanged(LoadingState(emptyList(), false))
        verify(observer).onChanged(ErrorState("Error", emptyList(), false))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        apiClient = null
        viewModel = null
    }
}

