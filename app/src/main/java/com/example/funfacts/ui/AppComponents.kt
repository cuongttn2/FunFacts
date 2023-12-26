package com.example.funfacts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funfacts.R
import com.example.funfacts.Utils

@Composable
fun TopBar(value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value, color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    TopBar("Hi there \uD83D\uDE0A")
}

@Composable
fun TextComponent(textValue: String, textSize: TextUnit, colorValue: Color = Color.Black) {
    Text(text = textValue, fontSize = textSize, color = colorValue, fontWeight = FontWeight.Light)
}

@Preview(showBackground = true)
@Composable
private fun TextComponentPreview() {
    TextComponent("Test", 20.sp)
}


@Composable
fun TextFieldComponent(
    onTextChanged: (name: String) -> Unit,
) {
    var currentValue by rememberSaveable {
        mutableStateOf("")
    }

    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = currentValue, onValueChange = {
        currentValue = it
        onTextChanged(it)
    }, placeholder = {
        Text(text = "Enter your name", fontSize = 18.sp)
    }, textStyle = TextStyle.Default.copy(fontSize = 24.sp), keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done
    ), keyboardActions = KeyboardActions {
        localFocusManager.clearFocus()
    })

}

@Preview(showBackground = true)
@Composable
private fun TextFieldComponentPreview() {
    TextFieldComponent { "enter name" }
}

@Composable
fun AnimalCard(image: Int, selected: Boolean, animalSelected: (animalName: String) -> Unit) {
    val animalName by rememberSaveable {
        mutableStateOf(if (image == R.drawable.cat) "Cat" else "Dog")
    }
    Card(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (selected) Color.Green else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        elevation = CardDefaults.cardElevation(4.dp),

        ) {
        val localeFocusManager = LocalFocusManager.current
        Box(
            modifier = Modifier
                .fillMaxSize()

                .clickable {
                    animalSelected(animalName)
                    localeFocusManager.clearFocus()
                },
            contentAlignment = Alignment.Center,

            ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                painter = painterResource(id = image),
                contentDescription = "image cat"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimalCardPreview() {
    AnimalCard(R.drawable.cat, true, { "Cat" })
}

@Composable
fun ButtonComponent(goToDetailScreen: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = { goToDetailScreen() }) {
        TextComponent(
            textValue = "Go to Details Screen", textSize = 18.sp, colorValue = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonComponentPreview() {
    ButtonComponent({})
}

@Composable
fun TextWithShadowComponent(value: String) {
    val shadowOffset = Offset(x = 1f, y = 1f)
    Text(
        text = value,
        fontSize = 24.sp,
        fontWeight = FontWeight.Light,
        style = TextStyle(shadow = Shadow(Utils.generateRandomColor(), shadowOffset, 1f))
    )

}

@Preview(showBackground = true)
@Composable
fun TextWithShadowComponentPreview() {
    TextWithShadowComponent("sadsjd jdasjkda dasdaksda")
}


@Composable
fun FactComposable(content: String) {
    Card(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp, 24.dp)
        ) {
            TextWithShadowComponent(value = content)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FactComposablePreview() {
    FactComposable("sadsjd jdasjkda dasdaksda\nsadsjd jdasjkda dasdaksda\nsadsjd jdasjkda dasdaksda")
}

