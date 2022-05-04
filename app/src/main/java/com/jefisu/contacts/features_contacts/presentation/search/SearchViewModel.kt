package com.jefisu.contacts.features_contacts.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private var contactJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ClearSearchText -> {
                state = state.copy(
                    query = "",
                    contacts = emptyList()
                )
            }
            is SearchEvent.OnFilterContacts -> {
                onFilterContacts(event.query)
            }
        }
    }

    private fun onFilterContacts(query: String) {
        state = state.copy(query = query)
        if (query.isNotBlank()) {
            contactJob?.cancel()
            contactJob = viewModelScope.launch {
                delay(300)
                repository.getContactsByName(query)
                    .onEach { contacts ->
                        state = state.copy(
                            contacts = contacts
                        )
                    }.launchIn(this)
            }
        }
    }
}