package com.idea3d.idea3d.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idea3d.idea3d.TestCoroutineRule
import com.idea3d.idea3d.data.model.home.ThingDTO
import com.idea3d.idea3d.data.model.home.ThingEntity
import com.idea3d.idea3d.data.model.mapper.GetNewsMapper
import com.idea3d.idea3d.data.model.mapper.GetThingMapper
import com.idea3d.idea3d.data.model.mapper.GetThingsMapper
import com.idea3d.idea3d.data.model.mapper.ThingsMapper
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.data.repository.work.WorkRepository
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCase
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import com.idea3d.idea3d.ui.viewModel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeRepository: HomeRepository
    private lateinit var workRepository: WorkRepository
    private lateinit var getAllThingsUseCase: GetAllThingsUseCase
    private lateinit var getFavoritesUseCase: GetFavoritesUseCase
    private lateinit var getNewsUseCase: GetNewsUseCase
    private lateinit var getNewsMapper: GetNewsMapper
    private lateinit var getThingMapper: GetThingMapper
    private lateinit var getThingsMapper: GetThingsMapper
    private lateinit var thingsMapper: ThingsMapper

    private val thing1 = ThingDTO(1, 1, "thing1", "jsjsjs", "kijsjis", true)
    private val thing2 = ThingEntity(2, 2,  "thing2", "jsjsjs", "kijsjis", false)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        homeRepository = mockk()
        workRepository = mockk()
        getAllThingsUseCase = mockk()
        getFavoritesUseCase = mockk()
        getNewsUseCase = mockk()
        getNewsMapper = mockk()
        getThingMapper = mockk()
        getThingsMapper = mockk()
        thingsMapper = mockk()

        viewModel = HomeViewModel(homeRepository, getAllThingsUseCase, getFavoritesUseCase, getNewsUseCase, getNewsMapper, getThingMapper, getThingsMapper, thingsMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        // GIVEN
        testCoroutineRule.runBlockingTest {
            // WHEN
            viewModel.fetchCategories()
            // THEN
            TestCase.assertNotNull(viewModel.fetchCategories())
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val page= 0
        testCoroutineRule.runBlockingTest {
            viewModel.setPagination(page)
            try {
                viewModel.fetchCategories()
            }catch (e: Exception){
                MatcherAssert.assertThat(
                    e.message,
                    CoreMatchers.`is`("We don't recognize the parameter")
                )
            }
        }

    }

    @Test
    fun `fetchNewsList calls use case`() = testCoroutineRule.runBlockingTest {
        val country = "us"
        val key = "testKey"
        coEvery { getNewsUseCase(country, key) } returns listOf()

        viewModel.fetchNewsList(country, key)
    }

}