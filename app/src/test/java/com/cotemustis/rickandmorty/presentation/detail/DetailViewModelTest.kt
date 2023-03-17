package com.cotemustis.rickandmorty.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cotemustis.rickandmorty.data.utils.Status
import com.cotemustis.rickandmorty.utils.CoroutinesTestRule
import com.cotemustis.rickandmorty.utils.FakeRmRepository
import com.cotemustis.rickandmorty.utils.getOrAwaitValueTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var repository: FakeRmRepository

    @Before
    fun setup() {
        repository = FakeRmRepository()
    }

    @Test
    fun `Given a correct request for a character by id, it should return a livedata with a response success`() =
        runBlockingTest {
            //Given
            repository.setWillReturnError(false)
            val viewModel = DetailViewModel(repository)

            viewModel.id = 1
            viewModel.getCharacterDetail()
            val value = viewModel.detailStatus.getOrAwaitValueTest()

            Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `Given an error request for a character by id, it should return a livedata with a response error`() =
        runBlockingTest {
            repository.setWillReturnError(true)
            val viewModel = DetailViewModel(repository)

            viewModel.id = 1
            viewModel.getCharacterDetail()
            val value = viewModel.detailStatus.getOrAwaitValueTest()

            Truth.assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
        }

}