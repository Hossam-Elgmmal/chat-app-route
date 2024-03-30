package com.route.chat.utils.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.model.Category
import com.route.chat.model.Room

@Composable
fun RoomCard(room: Room, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .size(180.dp)
            .padding(8.dp),
        colors = elevatedCardColors(
            Color.White,
            Color.Unspecified,
            Color.LightGray,
            Color.Unspecified
        )
    ) {

        Image(
            painter = painterResource(id = Category.fromId(room.categoryId).imageId),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = room.name,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RoomCardPreview() {
    RoomCard(Room()) {}
}