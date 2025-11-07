package com.sopt.dive.ui.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


enum class CardState(val imageResId: Int) {
    Front(com.sopt.dive.R.drawable.card_front_image),
    Back(com.sopt.dive.R.drawable.card_image)
}

@Composable
fun CardScreen(paddingValues: PaddingValues) {
    val context = LocalContext.current
    var currentCardState by remember { mutableStateOf(CardState.Front) }

    val cardTransition = updateTransition(targetState = currentCardState, label = "cardTransition")

    val cardRotationY by cardTransition.animateFloat(
        label = "cardRotationY",
        transitionSpec = { tween(durationMillis = 1500) }
    ) { state ->
        when (state) {
            CardState.Front -> 0f
            CardState.Back -> 180f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(
                    color = Color(229 / 255f, 99 / 255f, 97 / 255f),
                    shape = MaterialTheme.shapes.medium
                ),
            text = "오늘의 부적",
            style = MaterialTheme.typography.headlineSmall,

            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(250.dp)
                .padding(20.dp)
                .clickable {
                    currentCardState = if (currentCardState == CardState.Front) {
                        CardState.Back
                    } else {
                        CardState.Front
                    }
                }
                .graphicsLayer(
                    rotationY = cardRotationY,
                    cameraDistance = 8f
                )
                .border(1.dp, Color.Black)
                .padding(2.dp)
        ) {

            Image(
                painter = painterResource(id = currentCardState.imageResId),
                contentDescription = "카드 이미지",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun CardScreenPreview() {
    CardScreen(paddingValues = PaddingValues.Zero)
}

