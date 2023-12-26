package com.example.funfacts.ui.screen.user_input

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.funfacts.data.UserInputScreenState
import com.example.funfacts.data.UserUiDataEvents

class UserInputViewModel : ViewModel() {

    companion object {
        const val TAG = "UserInputViewModel"
    }

    var uiState = mutableStateOf(UserInputScreenState())

    fun onEvent(event: UserUiDataEvents) {
        when (event) {
            is UserUiDataEvents.UserNameEntered -> {
                uiState.value = uiState.value.copy(nameEntered = event.name)
                Log.d(TAG, "onEvent: UserNameEntered->>")
                Log.d(TAG, "${uiState.value}")
            }

            is UserUiDataEvents.AnimalSelected -> {
                uiState.value = uiState.value.copy(animalSelected = event.animalValue)
                Log.d(TAG, "onEvent: UserNameEntered->>")
                Log.d(TAG, "${uiState.value}")
            }
        }
    }

    fun isValidState(): Boolean {
        return uiState.value.nameEntered.isNotEmpty() && uiState.value.animalSelected.isNotEmpty()
    }

}
