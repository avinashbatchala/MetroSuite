package com.metro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Flat Windows time picker: large light hour/minute values with thin up/down steppers and an
 * optional AM/PM column. No Material clock dial.
 */
@Composable
fun MetroTimePicker(
    hour: Int,
    minute: Int,
    use24Hour: Boolean,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Stepper(
            value = "%02d".format(hour),
            onUp = { onHourChange((hour + 1) % 24) },
            onDown = { onHourChange((hour + 23) % 24) }
        )
        Text(
            text = ":",
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.padding(horizontal = 6.dp)
        )
        Stepper(
            value = "%02d".format(minute),
            onUp = { onMinuteChange((minute + 1) % 60) },
            onDown = { onMinuteChange((minute + 59) % 60) }
        )

        if (!use24Hour) {
            Column(
                modifier = Modifier.padding(start = 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AmPmText("AM", hour < 12) { if (hour >= 12) onHourChange(hour - 12) }
                AmPmText("PM", hour >= 12) { if (hour < 12) onHourChange(hour + 12) }
            }
        }
    }
}

@Composable
private fun AmPmText(label: String, selected: Boolean, onClick: () -> Unit) {
    val color = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    Text(
        text = label,
        style = MaterialTheme.typography.titleMedium.copy(color = color, fontWeight = FontWeight.SemiBold),
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp, horizontal = 4.dp)
    )
}

@Composable
private fun Stepper(value: String, onUp: () -> Unit, onDown: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        StepperButton("\u25B2", onUp)
        Text(
            text = value,
            style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(112.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh, RectangleShape)
                .padding(vertical = 2.dp)
        )
        StepperButton("\u25BC", onDown)
    }
}

@Composable
private fun StepperButton(glyph: String, onClick: () -> Unit) {
    Text(
        text = glyph,
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 16.sp
        ),
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 12.dp)
    )
}
