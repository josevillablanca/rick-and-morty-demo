package com.cotemustis.rickandmorty.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotemustis.rickandmorty.data.model.CharacterData
import com.cotemustis.rickandmorty.data.utils.Resource
import com.cotemustis.rickandmorty.domain.repository.RmRepository
import com.cotemustis.rickandmorty.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: RmRepository) : ViewModel() {


    private val _detailStatus = MutableLiveData<Event<Resource<CharacterData>>>()
    val detailStatus: LiveData<Event<Resource<CharacterData>>> = _detailStatus

    var id: Int = 0
    var name: String = ""

    fun getCharacterDetail() {
        _detailStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val response = repository.getCharacterById(id)
            _detailStatus.postValue(Event(response))
        }
    }
}