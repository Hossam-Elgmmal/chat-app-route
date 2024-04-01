package com.route.chat.utils.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.model.Message

@Composable
fun ReceivedMessageCard(message: Message) {


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f, false)
        ) {
            Text(text = message.senderName, modifier = Modifier.padding(vertical = 8.dp))
            Card(
                shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp),
                modifier = Modifier.padding(bottom = 8.dp),
                colors = CardColors(Color.LightGray, Color.Black, Color.DarkGray, Color.LightGray)
            ) {

                Text(
                    text = message.content,
                    modifier = Modifier.padding(16.dp, 8.dp)
                )

            }
        }
        Text(
            text = message.getFormattedTime(),
            overflow = TextOverflow.Clip,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceivedMessageCardPreview() {
    ReceivedMessageCard(Message("hello", "", "ahmed"))
}