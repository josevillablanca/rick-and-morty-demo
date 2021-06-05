package cl.cotemustis.rickandmorty.presentation.list

import cl.cotemustis.rickandmorty.data.utils.Status
import cl.cotemustis.rickandmorty.utils.CoroutinesTestRule
import cl.cotemustis.rickandmorty.utils.FakeRMRepository
import cl.cotemustis.rickandmorty.utils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

@ExperimentalCoroutinesApi
class RmListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var repository: FakeRMRepository

    @Before
    fun setup(){
        repository = FakeRMRepository()
    }

    @Test
    fun `Given a correct request for characters, it should return a livedata with a response success`() =
        runBlockingTest {
            //Given
            repository.setWillReturnError(false)
            val viewModel = RmListViewModel(repository)

            viewModel.retrieveCharacters()
            val value = viewModel.charactersListStatus.getOrAwaitValueTest()

            assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `Given an error request for characters, it should return a livedata with a response error`() =
        runBlockingTest {
            repository.setWillReturnError(true)
            val viewModel = RmListViewModel(repository)

            viewModel.retrieveCharacters()
            val value = viewModel.charactersListStatus.getOrAwaitValueTest()

            assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
        }
}