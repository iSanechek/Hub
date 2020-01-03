package com.isanechek.averdhub.ui.developer

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.text.ParagraphStyle
import androidx.ui.text.style.TextAlign

interface Developer {

    sealed class Routing {
        object Hello : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing) {
            Column(modifier = Spacing(40.dp), arrangement = Arrangement.SpaceAround) {
                Text(
                    text = "Welcome iSanechek",
                    style = (+MaterialTheme.typography()).h4,
                    paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center)
                )
                Container(
                    expanded = true,
                    constraints = DpConstraints(maxHeight = 48.dp),
                    alignment = Alignment.Center
                ) {
                    Button(text = "Boom", onClick = {  })
                }
            }
        }
    }

}