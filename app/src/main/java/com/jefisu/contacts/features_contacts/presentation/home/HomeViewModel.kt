package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getContacts()
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.DeleteContact -> {
                    repository.deleteContact(event.contact)
                }
            }
        }
    }

    private fun getContacts() {
        repository.getContacts()
            .onEach { contacts ->
                state = state.copy(
                    contacts = contacts.groupBy { it.name.first().uppercase() },
                    listSize = contacts.size
                )
            }.launchIn(viewModelScope)
    }
}