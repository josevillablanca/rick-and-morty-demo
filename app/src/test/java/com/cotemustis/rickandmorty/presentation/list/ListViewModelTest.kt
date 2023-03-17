package com.cotemustis.rickandmorty.presentation.list

import com.cotemustis.rickandmorty.data.utils.Status
import com.cotemustis.rickandmorty.utils.CoroutinesTestRule
import com.cotemustis.rickandmorty.utils.FakeRmRepository
import com.cotemustis.rickandmorty.utils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var repository: FakeRmRepository

    @Before
    fun setup(){
        repository = FakeRmRepository()
    }

    @Test
    fun `Given a correct request for characters, it should return a livedata with a response success`() =
        runBlockingTest {
            //Given
            repository.setWillReturnError(false)
            val viewModel = ListViewModel(repository)

            viewModel.retrieveCharacters(true)
            val value = viewModel.charactersListStatus.getOrAwaitValueTest()

            assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `Given an error request for characters, it should return a livedata with a response error`() =
        runBlockingTest {
            repository.setWillReturnError(true)
            val viewModel = ListViewModel(repository)

            viewModel.retrieveCharacters(true)
            val value = viewModel.charactersListStatus.getOrAwaitValueTest()

            assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
        }
}