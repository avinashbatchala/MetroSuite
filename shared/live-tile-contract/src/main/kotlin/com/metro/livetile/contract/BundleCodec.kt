package com.metro.livetile.contract

import android.os.Bundle

/**
 * Bundle (de)serialization for the cross-process contract. Bundles are the smallest common
 * denominator for `ContentProvider.call` IPC and keep the contract free of any serialization
 * library, so every consumer and provider agrees on the wire format.
 */

fun ClockTileState.toBundle(): Bundle = Bundle().apply {
    putInt(MetroLiveTileProtocol.EXTRA_PROTOCOL_VERSION, protocolVersion)
    putString(MetroLiveTileProtocol.EXTRA_PROVIDER_ID, providerId)
    putLong(MetroLiveTileProtocol.EXTRA_UPDATED_AT, updatedAt)
    putLong(MetroLiveTileProtocol.EXTRA_CURRENT_EPOCH, currentEpochMillis)

    nextAlarm?.let { alarm ->
        putBoolean(MetroLiveTileProtocol.EXTRA_HAS_ALARM, true)
        putInt(MetroLiveTileProtocol.EXTRA_ALARM_ID, alarm.alarmId)
        putLong(MetroLiveTileProtocol.EXTRA_ALARM_TRIGGER_EPOCH, alarm.triggerEpochMillis)
        putString(MetroLiveTileProtocol.EXTRA_ALARM_LABEL, alarm.label)
        putBoolean(MetroLiveTileProtocol.EXTRA_ALARM_ENABLED, alarm.enabled)
    }

    timer?.let { timer ->
        putBoolean(MetroLiveTileProtocol.EXTRA_HAS_TIMER, true)
        putString(MetroLiveTileProtocol.EXTRA_TIMER_STATE, timer.state.name)
        putLong(MetroLiveTileProtocol.EXTRA_TIMER_END_ELAPSED, timer.endElapsedRealtime)
        putLong(MetroLiveTileProtocol.EXTRA_TIMER_REMAINING_PAUSED, timer.remainingWhenPausedMillis)
        putString(MetroLiveTileProtocol.EXTRA_TIMER_LABEL, timer.label)
    }

    stopwatch?.let { sw ->
        putBoolean(MetroLiveTileProtocol.EXTRA_HAS_STOPWATCH, true)
        putString(MetroLiveTileProtocol.EXTRA_STOPWATCH_STATE, sw.state.name)
        putLong(MetroLiveTileProtocol.EXTRA_STOPWATCH_START_ELAPSED, sw.startElapsedRealtime)
        putLong(MetroLiveTileProtocol.EXTRA_STOPWATCH_ACCUMULATED, sw.accumulatedElapsedMillis)
    }
}

fun Bundle.toClockTileState(): ClockTileState? {
    val version = getInt(MetroLiveTileProtocol.EXTRA_PROTOCOL_VERSION, 0)
    if (version <= 0 || version > MetroLiveTileProtocol.VERSION) return null

    val nextAlarm = if (getBoolean(MetroLiveTileProtocol.EXTRA_HAS_ALARM, false)) {
        ClockNextAlarmState(
            alarmId = getInt(MetroLiveTileProtocol.EXTRA_ALARM_ID, -1),
            triggerEpochMillis = getLong(MetroLiveTileProtocol.EXTRA_ALARM_TRIGGER_EPOCH, 0L),
            label = getString(MetroLiveTileProtocol.EXTRA_ALARM_LABEL),
            enabled = getBoolean(MetroLiveTileProtocol.EXTRA_ALARM_ENABLED, false)
        )
    } else null

    val timer = if (getBoolean(MetroLiveTileProtocol.EXTRA_HAS_TIMER, false)) {
        ClockTimerState(
            state = runState(getString(MetroLiveTileProtocol.EXTRA_TIMER_STATE)),
            endElapsedRealtime = getLong(MetroLiveTileProtocol.EXTRA_TIMER_END_ELAPSED, 0L),
            remainingWhenPausedMillis = getLong(MetroLiveTileProtocol.EXTRA_TIMER_REMAINING_PAUSED, 0L),
            label = getString(MetroLiveTileProtocol.EXTRA_TIMER_LABEL)
        )
    } else null

    val stopwatch = if (getBoolean(MetroLiveTileProtocol.EXTRA_HAS_STOPWATCH, false)) {
        ClockStopwatchState(
            state = runState(getString(MetroLiveTileProtocol.EXTRA_STOPWATCH_STATE)),
            startElapsedRealtime = getLong(MetroLiveTileProtocol.EXTRA_STOPWATCH_START_ELAPSED, 0L),
            accumulatedElapsedMillis = getLong(MetroLiveTileProtocol.EXTRA_STOPWATCH_ACCUMULATED, 0L)
        )
    } else null

    return ClockTileState(
        protocolVersion = version,
        providerId = getString(MetroLiveTileProtocol.EXTRA_PROVIDER_ID)
            ?: MetroLiveTileProtocol.CLOCK_PROVIDER_ID,
        updatedAt = getLong(MetroLiveTileProtocol.EXTRA_UPDATED_AT, 0L),
        currentEpochMillis = getLong(MetroLiveTileProtocol.EXTRA_CURRENT_EPOCH, 0L),
        nextAlarm = nextAlarm,
        timer = timer,
        stopwatch = stopwatch
    )
}

fun MetroTileState.toBundle(): Bundle = Bundle().apply {
    putInt(MetroLiveTileProtocol.EXTRA_PROTOCOL_VERSION, protocolVersion)
    putString(MetroLiveTileProtocol.EXTRA_PROVIDER_ID, providerId)
    putLong(MetroLiveTileProtocol.EXTRA_UPDATED_AT, updatedAt)
    putLong(MetroLiveTileProtocol.EXTRA_VALID_UNTIL, validUntil)
    putString(MetroLiveTileProtocol.EXTRA_TEMPLATE, template)
    putString(MetroLiveTileProtocol.EXTRA_PRIMARY_TEXT, primaryText)
    putString(MetroLiveTileProtocol.EXTRA_SECONDARY_TEXT, secondaryText)
    putString(MetroLiveTileProtocol.EXTRA_TERTIARY_TEXT, tertiaryText)
    putString(MetroLiveTileProtocol.EXTRA_BADGE, badge)
    putString(MetroLiveTileProtocol.EXTRA_IMAGE_URI, imageUri)
    putString(MetroLiveTileProtocol.EXTRA_ACCESSIBILITY_TEXT, accessibilityText)
}

fun Bundle.toMetroTileState(): MetroTileState? {
    val version = getInt(MetroLiveTileProtocol.EXTRA_PROTOCOL_VERSION, 0)
    if (version <= 0 || version > MetroLiveTileProtocol.VERSION) return null
    return MetroTileState(
        protocolVersion = version,
        providerId = getString(MetroLiveTileProtocol.EXTRA_PROVIDER_ID) ?: return null,
        updatedAt = getLong(MetroLiveTileProtocol.EXTRA_UPDATED_AT, 0L),
        validUntil = getLong(MetroLiveTileProtocol.EXTRA_VALID_UNTIL, 0L),
        template = getString(MetroLiveTileProtocol.EXTRA_TEMPLATE) ?: MetroLiveTileTemplates.TEXT,
        primaryText = getString(MetroLiveTileProtocol.EXTRA_PRIMARY_TEXT),
        secondaryText = getString(MetroLiveTileProtocol.EXTRA_SECONDARY_TEXT),
        tertiaryText = getString(MetroLiveTileProtocol.EXTRA_TERTIARY_TEXT),
        badge = getString(MetroLiveTileProtocol.EXTRA_BADGE),
        imageUri = getString(MetroLiveTileProtocol.EXTRA_IMAGE_URI),
        accessibilityText = getString(MetroLiveTileProtocol.EXTRA_ACCESSIBILITY_TEXT)
    )
}

private fun runState(raw: String?): ClockRunState =
    runCatching { ClockRunState.valueOf(raw ?: "") }.getOrDefault(ClockRunState.IDLE)
