package com.jefisu.contacts.features_contacts.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.use_case.ContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val contactUseCase: ContactsUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private var contactJob: Job? = null

    init {
        onFilterContacts(state.query)
    }

    fun clearText() {
        state = state.copy(query = "")
        onFilterContacts(state.query)
    }

    fun onFilterContacts(query: String) {
        state = state.copy(query = query)
        contactJob?.cancel()
        contactJob = viewModelScope.launch {
            delay(300)
            contactUseCase.getContactsByName()
                .onEach { contacts ->
                    val filteredContacts = contacts.filter {
                        it.name.lowercase().contains(query.lowercase())
                    }
                    state = state.copy(
                        contacts = filteredContacts
                    )
                }
                .launchIn(this)
        }
    }
}