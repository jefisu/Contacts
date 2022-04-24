package com.jefisu.contacts.features_contacts.presentation.contact_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactInfoViewModel @Inject constructor(
    private val repository: ContactRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var isFavorite by mutableStateOf(false)
        private set

    val contactFlow = flow {
        savedStateHandle.get<String>("id")?.let { id ->
            val contact = repository.getContact(id.toInt())
            isFavorite = contact.isFavorite
            emit(contact)
        }
    }

    fun onEvent(event: ContactInfoEvent) {
        viewModelScope.launch {
            when (event) {
                is ContactInfoEvent.AddRemoveFavorite -> {
                    isFavorite = event.selected
                    repository.insertContact(
                        event.contact.copy(isFavorite = event.selected)
                    )
                }
            }
        }
    }
}