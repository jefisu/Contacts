package com.jefisu.contacts.features_contacts.presentation.add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.model.InvalidContactException
import com.jefisu.contacts.features_contacts.domain.use_case.ContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val contactUseCase: ContactsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(AddEditState())
        private set

    private var _currentContact: Contact? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                _currentContact = contactUseCase.getContact(id.toInt())
                _currentContact?.let {
                    state = state.copy(
                        name = it.name,
                        phone = it.phone,
                        email = it.email
                    )
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.AddContact -> {
                viewModelScope.launch {
                    try {
                        contactUseCase.insertContact(
                            Contact(
                                name = state.name,
                                phone = state.phone,
                                email = state.email,
                                date = _currentContact?.date ?: toFormat("MM/dd/yyyy"),
                                id = _currentContact?.id
                            )
                        )
                        _eventFlow.emit(UiEvent.AddSuccessfully)
                    } catch (e: InvalidContactException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(e.message.toString()))
                    }
                }
            }
            is AddEditEvent.EnteredName -> state = state.copy(name = event.name)
            is AddEditEvent.EnteredPhone -> state = state.copy(phone = event.phone)
            is AddEditEvent.EnteredEmail -> state = state.copy(email = event.email)
            is AddEditEvent.ClearTextName -> state = state.copy(name = "")
            is AddEditEvent.ClearTextPhone -> state = state.copy(phone = "")
            is AddEditEvent.ClearTextEmail -> state = state.copy(email = "")
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object AddSuccessfully : UiEvent()
    }

    private fun toFormat(pattern: String): String {
        val usePattern = SimpleDateFormat(pattern, Locale.getDefault())
        return usePattern.format(System.currentTimeMillis())
    }
}