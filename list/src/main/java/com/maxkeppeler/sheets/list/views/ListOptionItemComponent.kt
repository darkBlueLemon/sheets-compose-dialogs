package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.views.IconComponent
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.core.R as RC

/**
 * The item component for an option.
 * @param selection The selection configuration.
 * @param option The option that will be displayed.
 * @param onClick Listener that is invoked when the option is clicked.
 */
@Composable
internal fun ListOptionItemComponent(
    selection: ListSelection,
    option: ListOption,
    onClick: (ListOption) -> Unit
) {

    val showCheckBox = selection is ListSelection.Multiple && selection.showCheckBoxes
    val showRadioButtons = selection is ListSelection.Single && selection.showRadioButtons
    val showSelectionView = showCheckBox || showRadioButtons

    val backgroundColor =
        if (!showSelectionView && option.selected) MaterialTheme.colorScheme.secondaryContainer
        else MaterialTheme.colorScheme.surfaceVariant

    val iconColor = if (!showSelectionView && option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val textColor = if (!showSelectionView && option.selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    val containerModifier = Modifier
        .padding(bottom = dimensionResource(id = RC.dimen.scd_small_50))
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .clickable { onClick(option) }
        .then(if (!showSelectionView && option.selected) Modifier.background(backgroundColor) else Modifier)

    Row(
        modifier = containerModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val selectionModifier = Modifier
        when {
            showCheckBox -> Checkbox(
                modifier = selectionModifier,
                checked = option.selected,
                onCheckedChange = { onClick(option) }
            )
            showRadioButtons -> RadioButton(
                modifier = selectionModifier,
                selected = option.selected,
                onClick = { onClick(option) }
            )
        }

        option.icon?.let {
            IconComponent(
                modifier = Modifier
                    .padding(start = dimensionResource(RC.dimen.scd_normal_100))
                    .size(dimensionResource(RC.dimen.scd_size_150)),
                iconSource = it,
                tint = iconColor
            )
        }

        Column(
            modifier = Modifier
                .padding(vertical = dimensionResource(RC.dimen.scd_small_150))
                .padding(start = dimensionResource(RC.dimen.scd_normal_100))
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {

            Text(
                maxLines = 1,
                text = option.titleText,
                style = MaterialTheme.typography.labelLarge.copy(color = textColor)
            )

            option.subtitleText?.let { text ->
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall.copy(color = textColor)
                )
            }
        }
    }
}