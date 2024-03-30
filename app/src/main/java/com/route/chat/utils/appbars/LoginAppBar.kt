package com.route.chat.utils.appbars

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.route.chat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.login),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(
                Color.Transparent,
                titleContentColor = Color.White
            )
    )
}