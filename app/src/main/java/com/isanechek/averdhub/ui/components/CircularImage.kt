package com.isanechek.averdhub.ui.components

import android.util.Log
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Dp
import androidx.ui.core.WithDensity
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Image
import androidx.ui.layout.Container
import com.isanechek.averdhub.ext.image
import kotlin.math.min

private const val TAG = "CircularImage"

@Composable
fun CircularImage(data: Any, size: Dp? = null) {
    val image = +image(data)
    when {
        image != null -> ShowCircleImage(image, size)
        else -> Log.e(TAG, "CircularImage: Image is null!")
    }
}

@Composable
private fun ShowCircleImage(image: Image, size: Dp? = null) {
    WithDensity {
        val newSize = size ?: min(image.width, image.height).toDp()
        Container(width = newSize, height = newSize) {
            Log.e(TAG, "ShowCircleImage: SIZE $newSize")
            Clip(shape = RoundedCornerShape(newSize / 2)) {
                DrawImage(image)
            }
        }
    }
}
