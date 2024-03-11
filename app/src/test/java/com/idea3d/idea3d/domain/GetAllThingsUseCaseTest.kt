package com.idea3d.idea3d.domain

import com.idea3d.idea3d.MainCoroutineRule
import com.idea3d.idea3d.data.model.home.Things
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.data.repository.home.HomeRepositoryImpl
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllThingsUseCaseTest {

    private lateinit var useCase: GetAllThingsUseCase
    private lateinit var repo: HomeRepository
    private val things: Things? = null

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repo = mockk<HomeRepositoryImpl>()
        useCase = GetAllThingsUseCaseImpl(repo)

    }

    @Test
    fun `execute returns correct data`() = runBlocking {
        val expectedData = Things(emptyList(), 0)
        coEvery { repo.getThingsFromCat(any(), any()) } returns expectedData

        val result = useCase(1, 1)

        assertEquals(expectedData, result)
    }
}
