package com.sopt.dive.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.model.HomeProfileInfo
import com.sopt.dive.ui.theme.SubPinkBackground


@Composable
fun UserProfileCard(user: HomeProfileInfo, modifier: Modifier = Modifier) {
    var isFollowing by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_photo),
            contentDescription = "프로필 사진",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Column(modifier.padding(horizontal = 30.dp)) {
            Text(
                user.title,
                Modifier.padding(top = 2.dp, bottom = 7.dp),
                fontWeight = FontWeight.Bold
            )
            Text(user.content)
        }

        Button(
            onClick = {
                isFollowing = !isFollowing

            },
            modifier = Modifier.size(width = 120.dp, height = 40.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFollowing) Color.Gray else SubPinkBackground,
                contentColor = Color.White
            ),
        ) {
            Text(
                text = if (isFollowing) "Unfollow" else "Follow",
                color = if (isFollowing) Color.Black else Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Preview
@Composable
private fun InputPreview() {
    UserProfileCard(
        HomeProfileInfo(
            userName = "gey",
            title = "ddd",
            content = "gkdl dkssud qldhk duddnjsgl"
        )
    )
}
