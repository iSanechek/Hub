package com.isanechek.averdhub.ui.components

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.tooling.preview.Preview


@Composable
fun SmallInstallButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = (+MaterialTheme.colors()).surface
) {
    Button(
        text = text,
        onClick = onClick,
        style = ButtonStyle(
            color = backgroundColor,
            rippleColor = (+MaterialTheme.colors()).onSurface,
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp
            )
        )
    )
}

@Preview
@Composable
fun CustomButtonPreview() {
    SmallInstallButton(text = "Test", onClick = {})
}