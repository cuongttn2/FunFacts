package com.example.funfacts.ui.screen.user_input

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funfacts.R
import com.example.funfacts.data.UserUiDataEvents
import com.example.funfacts.ui.AnimalCard
import com.example.funfacts.ui.ButtonComponent
import com.example.funfacts.ui.TextComponent
import com.example.funfacts.ui.TextFieldComponent
import com.example.funfacts.ui.TopBar

@Composable
fun UserInputScreen(
    userInputViewModel: UserInputViewModel,
    showWelcomeScreen: (valuesPair: Pair<String, String>) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val focusManager = LocalFocusManager.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() }
                    )
                }
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {
                item {
                    TopBar("Hi there \uD83D\uDE0A")
                    TextComponent(
                        "Let's learn about You !", textSize = 24.sp
                    )
                    Spacer(modifier = Modifier.size(20.dp))

                    TextComponent(
                        "This app will prepare a details page based on input provided by you !",
                        textSize = 18.sp
                    )
                    Spacer(modifier = Modifier.size(60.dp))
                    TextComponent(
                        "Name", textSize = 18.sp
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    TextFieldComponent(onTextChanged = {
                        userInputViewModel.onEvent(
                            UserUiDataEvents.UserNameEntered(it)
                        )
                    })
                    Spacer(modifier = Modifier.size(20.dp))
                    TextComponent(
                        "What do you like", textSize = 18.sp
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.weight(1f)) {
                            AnimalCard(image = R.drawable.cat,
                                userInputViewModel.uiState.value.animalSelected == "Cat",
                                animalSelected = {
                                    userInputViewModel.onEvent(UserUiDataEvents.AnimalSelected(it))
                                })
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            AnimalCard(image = R.drawable.dog,
                                userInputViewModel.uiState.value.animalSelected == "Dog",
                                animalSelected = {
                                    userInputViewModel.onEvent(UserUiDataEvents.AnimalSelected(it))
                                })
                        }
                    }

                }
            }

            ButtonComponent(
                goToDetailScreen = {
                    if (!userInputViewModel.isValidState()) return@ButtonComponent
                    else {
                        showWelcomeScreen(
                            Pair(
                                userInputViewModel.uiState.value.nameEntered,
                                userInputViewModel.uiState.value.animalSelected
                            )
                        )
                    }
                }
            )

        }// Column

    }// Surface
}


@Preview
@Composable
fun UserInputScreenPreview() {
    UserInputScreen(UserInputViewModel(), { Pair("name", "amimal") })
}
