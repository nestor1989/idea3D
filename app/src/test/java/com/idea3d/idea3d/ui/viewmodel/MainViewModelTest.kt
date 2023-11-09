package com.idea3d.idea3d.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.idea3d.idea3d.TestCoroutineRule
import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.ui.viewModel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @RelaxedMockK
    private lateinit var repo: Repo
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(repo)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewmodel is created at the first time, set idThings`() = runTest{
        val things = "Relevant"
        mainViewModel.setThings(things)
        assertEquals(things, mainViewModel.searchThing.value)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        // GIVEN
        testCoroutineRule.runBlockingTest {
            // WHEN
            mainViewModel.fetchThings
            // THEN
            assertNotNull(mainViewModel.fetchThings)
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val things = "Anything"
        testCoroutineRule.runBlockingTest {
            mainViewModel.setThings(things)
            try {
                mainViewModel.setThings(things)
            }catch (e: Exception){
                assertThat(e.message, `is`("We don't recognize the parameter"))
            }
        }

    }
}