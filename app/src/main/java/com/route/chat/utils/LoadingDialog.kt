package com.route.chat.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.route.chat.ui.theme.mainBlue

@Composable
fun LoadingDialog(isLoading: MutableState<Boolean>) {
    if (isLoading.value) {
        Dialog(onDismissRequest = { isLoading.value = false }) {

            CircularProgressIndicator(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(36.dp),
                color = mainBlue
            )
        }
    }

}