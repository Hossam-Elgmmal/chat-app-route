package com.route.chat.utils.appbars

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.route.chat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.chat_app),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(
                Color.Transparent,
                titleContentColor = Color.White
            ),
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(R.string.back_to_login),
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
private fun HomeAppBarPreview() {
    HomeAppBar()
}