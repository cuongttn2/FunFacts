package com.example.funfacts.ui.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.funfacts.ui.FactComposable
import com.example.funfacts.ui.TextComponent
import com.example.funfacts.ui.TextWithShadowComponent
import com.example.funfacts.ui.TopBar

@Composable
fun WelComeScreen(userName: String?, animalSelected: String?) {
    println("===InsideWelcomeScreen===")
    println("$userName and $animalSelected")
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(18.dp)
            ) {
                item {
                    TopBar("Welcome $userName \uD83D\uDE0D")
                    TextComponent(
                        "Thank You! for sharing your data", textSize = 24.sp
                    )

                    Spacer(modifier = Modifier.size(60.dp))

                    val finalText =
                        if (animalSelected == "Cat") "You are a Cat Lover" else "You are a Dog Lover"

                    TextWithShadowComponent("$finalText \uD83D\uDC36")

                    Spacer(modifier = Modifier.size(100.dp))

                    val factViewModel: FactViewModel = viewModel()
                    FactComposable(content = factViewModel.generateRandomFact(animalSelected!!))


                }
            }
        }
    }
}

@Preview
@Composable
fun WelComeScreenPreview() {
    WelComeScreen("John Cena", "Cat")
}