package com.maxkeppeker.sheets.core.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeler.sheets.core.R as RC

@ExperimentalMaterial3Api
@Composable
fun HeaderComponent(
    header: Header?,
    noHeaderView: @Composable () -> Unit = {}
) {
    when (header) {
        is Header.Custom -> header.header.invoke()
        is Header.Default -> CustomHeaderComponent(header)
        null -> noHeaderView.invoke()
    }
}

@Composable
private fun CustomHeaderComponent(header: Header.Default) {
    Row {
        header.icon?.let {
            IconComponent(
                modifier = Modifier.size(dimensionResource(RC.dimen.size_150)),
                imageSource = it,
            )
        }
        Column {
            Text(
                text = header.titleText,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = RC.dimen.normal_150))
                    .padding(top = dimensionResource(id = RC.dimen.normal_150)),
            )
            if (header.subtitleText != null)
                Text(
                    text = header.subtitleText,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = RC.dimen.normal_150))
                        .padding(top = dimensionResource(id = RC.dimen.small_50)),
                )
        }
    }
}