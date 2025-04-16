package com.example.nit3213finalapp.presentation.login

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nit3213finalapp.data.AuthRepository
import com.example.nit3213finalapp.data.models.LoginResponse
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class gitLoginViewModelTest {

    // this rule needed for livedata testing else observer won't trigger
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: AuthRepository

    // to control coroutines for test purpose
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // setting dispatcher for testing
        Dispatchers.setMain(testDispatcher)
        repository = mockk()

        // mocking logs else test will throw log error
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0

        // viewmodel init with fake repo
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        // resetting everything after test done
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `login success updates loginState`() = runTest {
        // if login success then loginState should be true

        val username = "Bibhushan"
        val password = "s8094213"
        val keypass = "fashion"

        // mocking api response
        coEvery { repository.login(username, password) } returns Response.success(LoginResponse(keypass))

        // mocking saveKeyPass call
        every { repository.saveKeyPass(keypass) } just Runs

        val observer = mockk<Observer<Boolean>>(relaxed = true)
        viewModel.loginState.observeForever(observer)

        // calling login
        viewModel.login(username, password)
        advanceUntilIdle()

        // checking api call done or not
        coVerify { repository.login(username, password) }

        // checking if keypass saved or not
        verify { repository.saveKeyPass(keypass) }

        // checking if loginState changed to true
        verify { observer.onChanged(true) }
    }

    @Test
    fun `login failure updates errorMessage`() = runTest {
        // if login fails then error msg should be updated

        val username = "wrong"
        val password = "wrong"

        // mocking failure response
        coEvery { repository.login(username, password) } returns Response.error(401, mockk(relaxed = true))

        val observer = mockk<Observer<String>>(relaxed = true)
        viewModel.errorMessage.observeForever(observer)

        // calling login with wrong creds
        viewModel.login(username, password)
        advanceUntilIdle()

        // checking api called or not
        coVerify { repository.login(username, password) }

        // checking if error msg updated properly
        verify { observer.onChanged("Login either pwd or username wrong!") }
    }
}
