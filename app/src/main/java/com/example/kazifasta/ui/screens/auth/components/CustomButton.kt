package com.example.kazifasta.ui.screens.auth.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kazifasta.data.model.UserState
import com.example.kazifasta.ui.theme.dark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

@Composable
fun CustomButton(onClick: () -> Unit, label: String, userState: Flow<UserState>) {
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(userState) {
        userState
            .onEach {
                isLoading = (it == UserState.Loading)
            }
            .collect()
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(dark)
    ) {
        Box(contentAlignment = Alignment.Center){
            if (isLoading) {
                // Show a loading indicator when isLoading is true
                CircularProgressIndicator(
                    color = Color.White
                )
            } else {
                Text(label)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    val loadingState: Flow<UserState> = flow {
        emit(UserState.Loading)
    }
    CustomButton(onClick = { /*TODO*/ }, label = "Button", userState = loadingState)
}
