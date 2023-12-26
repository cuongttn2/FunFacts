package com.example.funfacts.data

sealed class UserUiDataEvents {
    data class UserNameEntered(val name: String) : UserUiDataEvents()
    data class AnimalSelected(val animalValue: String) : UserUiDataEvents()
}