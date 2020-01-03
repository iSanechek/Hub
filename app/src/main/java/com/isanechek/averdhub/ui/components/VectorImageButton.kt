package com.isanechek.averdhub.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.material.ripple.Ripple

@Composable
fun VectorImageButton(
    @DrawableRes id: Int,
    tint: Color = Color.Transparent,
    onClick: () -> Unit
) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            SimpleVector(id, tint)
        }
    }
}