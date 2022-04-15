package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.use_case.ContactsUseCase
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactUseCase: ContactsUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private var contactJob: Job? = null

    init {
        getContacts()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteContact -> {
                viewModelScope.launch {
                    contactUseCase.deleteContact(event.contact)
                }
            }
            is HomeEvent.Order -> {
                if (state.contactOrder::class == event.contactOrder::class &&
                    state.contactOrder.orderType == event.contactOrder.orderType
                ) {
                    return
                }
                getContacts(event.contactOrder)
            }
            is HomeEvent.ToggleOrderSection -> {
                state = state.copy(
                    isOrderSectionVisible = !state.isOrderSectionVisible
                )
            }
            is HomeEvent.ClearResearchText -> {
                state = state.copy(query = event.text)
                getContacts()
            }
            is HomeEvent.OnFilterContacts -> {
                onFilterContacts(event.query)
            }
            is HomeEvent.AddRemoveFavorite -> {
                viewModelScope.launch {
                    contactUseCase.insertContact(
                        event.contact.copy(isFavorite = event.selected)
                    )
                }
            }
        }
    }

    private fun getContacts(
        contactOrder: ContactOrder = ContactOrder.Name(OrderType.Ascending)
    ) {
        contactUseCase.getContacts(contactOrder)
            .onEach { contacts ->
                state = state.copy(
                    contacts = contacts,
                    contactOrder = contactOrder
                )
            }.launchIn(viewModelScope)
    }

    private fun onFilterContacts(query: String) {
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