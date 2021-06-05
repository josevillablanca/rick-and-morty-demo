package cl.cotemustis.rickandmorty.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData
import cl.cotemustis.rickandmorty.data.utils.Resource
import cl.cotemustis.rickandmorty.repository.RmRepository
import cl.cotemustis.rickandmorty.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: RmRepository) : ViewModel() {

    private val _charactersListStatus = MutableLiveData<Event<Resource<CharactersResponseData>>>()
    val charactersListStatus: LiveData<Event<Resource<CharactersResponseData>>> =
        _charactersListStatus

    fun retrieveCharacters(isRefreshing: Boolean) {
        if(!isRefreshing){
            _charactersListStatus.postValue(Event(Resource.loading(null)))
        }
        viewModelScope.launch {
            val response = repository.getCharacters()
            _charactersListStatus.postValue(Event(response))
        }
    }
}