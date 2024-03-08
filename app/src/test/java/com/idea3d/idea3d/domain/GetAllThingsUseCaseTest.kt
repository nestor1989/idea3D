package com.idea3d.idea3d.domain

import com.idea3d.idea3d.MainCoroutineRule
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.model.Things
import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.any
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllThingsUseCaseTest {

    private lateinit var useCase: GetAllThingsUseCase
    private lateinit var repo: Repo
    private val things: Things? = null

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repo = mockk<Repo>()
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
