package pl.dmardev172.movieexplorer.presentation.movie_list

import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dmardev172.movieexplorer.MainDispatcherRule
import pl.dmardev172.movieexplorer.fake.FakeMoveRepository
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeRepository: FakeMoveRepository
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        fakeRepository = FakeMoveRepository()
        viewModel = MovieListViewModel(fakeRepository)
    }

    @Test
    fun `search updates state with movies on success`() = runTest {
        viewModel.search("batman")
        val state = viewModel.state.value

        assertFalse(state.isLoading)
        assertEquals(1, state.movies.size)
        assertEquals("Batman Begins", state.movies.first().title)
        assertNull(state.error)
    }

    @Test
    fun `search updates with error when repository throws`() = runTest {
        fakeRepository.shouldThrowError = true

        viewModel.search("batman")
        val state = viewModel.state.value

        assertFalse(state.isLoading)
        assertTrue(state.movies.isEmpty())
        assertEquals("Network Error", state.error)
    }
}