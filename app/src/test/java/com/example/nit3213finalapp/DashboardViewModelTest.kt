package com.example.nit3213finalapp.presentation.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nit3213finalapp.data.DashboardRepository
import com.example.nit3213finalapp.data.models.DashboardResponse
import com.example.nit3213finalapp.data.models.EntityItem
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    // this rule is like compulsory for livedata testing
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DashboardViewModel
    private lateinit var repository: DashboardRepository

    // test dispatcher for controlling coroutines stuff in test
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // setting main dispatcher to test one
        Dispatchers.setMain(testDispatcher)
        repository = mockk()

        // mocking log error to avoid log issue during test
        mockkStatic(android.util.Log::class)
        every { android.util.Log.e(any(), any()) } returns 0

        // init viewmodel with mocked repo
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        // resetting main dispatcher after test ends
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getDashboardData success updates entities`() = runTest {
        // here checking if api success then entities should update

        val keypass = "fashion"

        // dummy list for test
        val entityList = listOf(
            EntityItem(
                itemName = "Jeans",
                designer = "Levi Strauss",
                yearIntroduced = 1873,
                category = "Pants",
                material = "Denim",
                description = "Trousers made from denim."
            )
        )

        // mocking repo response
        coEvery { repository.getDashboardData(keypass) } returns Response.success(DashboardResponse(entityList, 1))

        // observing livedata
        val observer = mockk<Observer<List<EntityItem>>>(relaxed = true)
        viewModel.entities.observeForever(observer)

        // calling viewmodel function
        viewModel.getDashboardData(keypass)
        advanceUntilIdle()

        // verify api call happened
        coVerify { repository.getDashboardData(keypass) }

        // verify data updated in livedata
        verify { observer.onChanged(entityList) }
    }

    @Test
    fun `getDashboardData failure does not update entities`() = runTest {
        // here checking if api fails then livedata should not update

        val keypass = "fashion"

        // mocking repo to throw error
        coEvery { repository.getDashboardData(keypass) } throws Exception("Network error")

        // observer for livedata
        val observer = mockk<Observer<List<EntityItem>>>(relaxed = true)
        viewModel.entities.observeForever(observer)

        // calling viewmodel function
        viewModel.getDashboardData(keypass)
        advanceUntilIdle()

        // verify api call happened
        coVerify { repository.getDashboardData(keypass) }

        // no data should be updated because of error
        verify(exactly = 0) { observer.onChanged(any()) }
    }
}
