package com.sopt.dive.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.theme.MainPinkText


enum class CardState(val imageResId: Int) {
    Front(com.sopt.dive.R.drawable.card_front_image),
    Back(com.sopt.dive.R.drawable.card_image)
}

@Composable
fun CardScreen(paddingValues: PaddingValues) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }
    var isInitial by remember { mutableStateOf(true) }


    LaunchedEffect(isFlipped) {
        if (isInitial) {
            isInitial = false
            return@LaunchedEffect
        }

        val currentRotation = if (isFlipped) 180f else 0f
        rotation.animateTo(
            targetValue = currentRotation,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize().padding(paddingValues)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "오늘의 부적",
            color = MainPinkText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "👇🏻 눌러서 확인하기  ",
            color = Color.Gray,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(250.dp)
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .clickable {
                    isFlipped = !isFlipped
                }
                .graphicsLayer(
                    rotationY = rotation.value % 360f,
                    cameraDistance = 12f * 80
                )
                .border(2.dp, Color.Gray,RoundedCornerShape(40.dp))
                .shadow(8.dp, RoundedCornerShape(40.dp))
                .padding(2.dp)
        ) {
            val rotationMod = rotation.value % 360f
            val isFront = rotationMod <= 90f || rotationMod >= 270f

            Image(
                painter = painterResource(
                    id = if (isFront) CardState.Front.imageResId else CardState.Back.imageResId
                ),
                contentDescription = "카드 이미지",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        if (!isFront) rotationY = 180f
                    },
                contentScale = ContentScale.Crop
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun CardScreenPreview() {
    CardScreen(paddingValues = PaddingValues.Zero)
}

