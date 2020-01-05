package com.isanechek.averdhub.ui.components

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.engine.geometry.Shape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle

/*Пришлось копирнуть из сорцов, ибо других вариков выкорчивать тень не нашел*/

private const val MaxIconsInTopAppBar = 2
private val AppBarPadding = 16.dp
private val AppBarTitleStartPadding = 72.dp - AppBarPadding
private val AppBarTitleBaselineOffset = 20.sp
private val AppBarHeight = 56.dp
private val TopAppBarElevation = 0.dp

@Composable
fun <T> CustomTopAppBar(
    title: @Composable() () -> Unit,
    actionData: List<T>,
    color: Color = (+MaterialTheme.colors()).primary,
    navigationIcon: @Composable() (() -> Unit)? = null,
    action: @Composable() (T) -> Unit
) {
    CustomBaseTopAppBar(
        color = color,
        startContent = navigationIcon,
        title = {
            // Text color comes from the underlying Surface
            CurrentTextStyleProvider(value = (+MaterialTheme.typography()).h6, children = title)
        },
        endContent = getActions(actionData, MaxIconsInTopAppBar, action)
    )
}

@Composable
private fun CustomBaseTopAppBar(
    color: Color = (+MaterialTheme.colors()).primary,
    startContent: @Composable() (() -> Unit)?,
    title: @Composable() () -> Unit,
    endContent: @Composable() (() -> Unit)?
) {

    CustomBaseAppBar(color, TopAppBarElevation, RectangleShape) {
        FlexRow(
            mainAxisAlignment = MainAxisAlignment.SpaceBetween,
            crossAxisSize = LayoutSize.Expand
        ) {
            // We only want to reserve space here if we have some start content
            if (startContent != null) {
                inflexible {
                    Container(
                        modifier = ExpandedHeight,
                        width = AppBarTitleStartPadding,
                        alignment = Alignment.CenterLeft,
                        children = startContent
                    )
                }
            }
            expanded(1f) {
                Align(Alignment.BottomLeft) {
                    AlignmentLineOffset(
                        alignmentLine = LastBaseline,
                        after = withDensity(+ambientDensity()) { AppBarTitleBaselineOffset.toDp() }
                    ) {
                        // TODO: AlignmentLineOffset requires a child, so in case title() is
                        // empty we just add an empty wrap here - should be fixed when we move to
                        // modifiers.
                        Wrap(children = title)
                    }
                }
            }
            if (endContent != null) {
                inflexible {
                    Container(
                        modifier = ExpandedHeight,
                        alignment = Alignment.Center,
                        children = endContent
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomBaseAppBar(
    color: Color,
    elevation: Dp,
    shape: Shape,
    children: @Composable() () -> Unit
) {
    Surface(color = color, elevation = elevation, shape = shape) {
        Container(
            height = AppBarHeight,
            expanded = true,
            padding = EdgeInsets(left = AppBarPadding, right = AppBarPadding),
            children = children
        )
    }
}

private fun <T> getActions(
    actionData: List<T>,
    numberOfActions: Int,
    action: @Composable() (T) -> Unit
): @Composable() (() -> Unit)? {
    return if (actionData.isEmpty()) {
        null
    } else {
        @Composable {
            AppBarActions(numberOfActions, actionData, action)
        }
    }
}

@Composable
private fun <T> AppBarActions(
    actionsToDisplay: Int,
    actionData: List<T>,
    action: @Composable() (T) -> Unit
) {
    // Split the list depending on how many actions we are displaying - if actionsToDisplay is
    // greater than or equal to the number of actions provided, overflowActions will be empty.
    val (shownActions, overflowActions) = actionData.withIndex().partition {
        it.index < actionsToDisplay
    }

    Row {
        shownActions.forEach { (index, shownAction) ->
            action(shownAction)
            if (index != shownActions.lastIndex) {
                WidthSpacer(width = 24.dp)
            }
        }
        if (overflowActions.isNotEmpty()) {
            WidthSpacer(width = 24.dp)
            // TODO: use overflowActions to build menu here
            Container(width = 12.dp) {
                Text(text = "${overflowActions.size}", style = TextStyle(fontSize = 15.sp))
            }
        }
    }
}