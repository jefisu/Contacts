package com.jefisu.contacts.features_contacts.presentation.recents


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
import javax.inject.Inject

@HiltViewModel
class RecentsViewModel @Inject constructor(
    private val contactUseCase: ContactsUseCase
) : ViewModel() {

    var state by mutableStateOf(RecentsState())
        private set

    private var contactJob: Job? = null

    init {
        getContacts(ContactOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: RecentsEvent) {
        when (event) {
            is RecentsEvent.Order -> {
                if (state.contactOrder::class == event.contactOrder::class &&
                    state.contactOrder.orderType == event.contactOrder.orderType
                ) {
                    return
                }
                getContacts(event.contactOrder)
            }
            is RecentsEvent.ToggleOrderSection -> {
                state = state.copy(
                    isOrderSectionVisible = !state.isOrderSectionVisible
                )
            }
        }
    }

    private fun getContacts(contactOrder: ContactOrder) {
        contactJob?.cancel()
        contactJob = contactUseCase.getContacts(contactOrder)
            .onEach {
                state = state.copy(
                    contacts = it,
                    contactOrder = contactOrder
                )
            }.launchIn(viewModelScope)
    }
}