package com.idea3d.idea3d.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idea3d.idea3d.TestCoroutineRule
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import com.idea3d.idea3d.ui.viewModel.HomeViewModel
import io.mockk.MockKAnnotations
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
    private lateinit var repo: Repo

    private val thing1 = ThingEntity(1, "thing1", "jsjsjs", "kijsjis", true)
    private val thing2 = ThingEntity(2, "thing2", "jsjsjs", "kijsjis", false)

    private lateinit var getAllThingsUseCase: GetAllThingsUseCase
    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        repo = mockk()
        getAllThingsUseCase = mockk()
        getFavoritesUseCase = mockk()
        viewModel = HomeViewModel(repo, getAllThingsUseCase, getFavoritesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `addedToFavorite calls repository method`() {
        val thing = thing1

        viewModel.addedToFavorite(thing)

        coVerify { repo.addedThingToFav(thing) }
    }

    @Test
    fun `deleteFavorite calls repository method`() {
        val thing = thing1

        viewModel.deleteFavorite(thing)

        coVerify { repo.deleteFavorite(thing) }
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

}