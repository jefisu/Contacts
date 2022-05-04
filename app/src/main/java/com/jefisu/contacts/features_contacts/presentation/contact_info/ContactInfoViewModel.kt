package com.jefisu.contacts.features_contacts.presentation.contact_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactInfoViewModel @Inject constructor(
    private val repository: ContactRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var contact by mutableStateOf(Contact())
        private set

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            repository.getContact(id.toInt())
                .onEach {
                    it?.let { contact = it }
                }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: ContactInfoEvent) {
        viewModelScope.launch {
            when (event) {
                is ContactInfoEvent.AddRemoveFavorite -> {
                    contact = contact.copy(
                        isFavorite = !contact.isFavorite
                    )
                    repository.insertContact(contact)
                }
            }
        }
    }
}