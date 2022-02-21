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
        getContacts(ContactOrder.Name(OrderType.Ascending))
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteContact -> {
                viewModelScope.launch {
                    contactUseCase.deleteContact(event.contact)
                }
            }
        }
    }

    private fun getContacts(contactOrder: ContactOrder) {
        contactJob?.cancel()
        contactJob = contactUseCase.getContacts(contactOrder)
            .onEach {
                state = state.copy(
                    contacts = it
                )
            }.launchIn(viewModelScope)
    }
}