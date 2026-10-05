package com.metro.livetile.contract

/**
 * Versioned, dependency-light protocol shared by MetroSuite apps for cross-APK live tiles.
 *
 * Rendering never lives here: a provider app owns its domain state, the launcher owns the
 * tile pixels. Apps query state through an Android [android.content.ContentProvider] and are
 * told to re-query via content-change notifications, so no ticking value is ever pushed over
 * IPC once per second.
 */
object MetroLiveTileProtocol {

    /** Bump only on breaking wire changes. Unknown extra fields must be ignored by readers. */
    const val VERSION = 1

    /** Signature-protected permission all MetroSuite providers/consumers share. */
    const val PERMISSION_READ_TILE_STATE = "com.metro.permission.READ_TILE_STATE"

    /** Clock's live-tile content provider authority. Fixed so the launcher can discover it. */
    const val CLOCK_AUTHORITY = "com.metro.clock.livetile"

    /** Provider method invoked through `ContentResolver.call`. */
    const val METHOD_GET_CLOCK_STATE = "getClockTileState"

    /** Stable provider id used for diagnostics and multi-provider fan-out. */
    const val CLOCK_PROVIDER_ID = "metropolis.clock"

    // Common bundle keys (generic Metro tile state).
    const val EXTRA_PROTOCOL_VERSION = "protocolVersion"
    const val EXTRA_PROVIDER_ID = "providerId"
    const val EXTRA_UPDATED_AT = "updatedAt"
    const val EXTRA_VALID_UNTIL = "validUntil"
    const val EXTRA_TEMPLATE = "template"
    const val EXTRA_PRIMARY_TEXT = "primaryText"
    const val EXTRA_SECONDARY_TEXT = "secondaryText"
    const val EXTRA_TERTIARY_TEXT = "tertiaryText"
    const val EXTRA_BADGE = "badge"
    const val EXTRA_IMAGE_URI = "imageUri"
    const val EXTRA_ACCESSIBILITY_TEXT = "accessibilityText"

    // Clock-specific keys.
    const val EXTRA_CURRENT_EPOCH = "currentEpochMillis"
    const val EXTRA_TIMER_STATE = "timerState"
    const val EXTRA_TIMER_END_ELAPSED = "timerEndElapsedRealtime"
    const val EXTRA_TIMER_REMAINING_PAUSED = "timerRemainingWhenPausedMillis"
    const val EXTRA_TIMER_LABEL = "timerLabel"
    const val EXTRA_HAS_TIMER = "hasTimer"
    const val EXTRA_STOPWATCH_STATE = "stopwatchState"
    const val EXTRA_STOPWATCH_START_ELAPSED = "stopwatchStartElapsedRealtime"
    const val EXTRA_STOPWATCH_ACCUMULATED = "stopwatchAccumulatedElapsedMillis"
    const val EXTRA_HAS_STOPWATCH = "hasStopwatch"
    const val EXTRA_ALARM_ID = "alarmId"
    const val EXTRA_ALARM_TRIGGER_EPOCH = "alarmTriggerEpochMillis"
    const val EXTRA_ALARM_LABEL = "alarmLabel"
    const val EXTRA_ALARM_ENABLED = "alarmEnabled"
    const val EXTRA_HAS_ALARM = "hasAlarm"

    // Clock deep links.
    const val CLOCK_SCHEME = "metroclock"
    const val CLOCK_HOST_ALARMS = "alarms"
    const val CLOCK_HOST_WORLD = "world"
    const val CLOCK_HOST_TIMER = "timer"
    const val CLOCK_HOST_STOPWATCH = "stopwatch"
    const val CLOCK_HOST_SETTINGS = "settings"
}

/** Well-known generic templates. Providers may add their own; consumers fall back to text. */
object MetroLiveTileTemplates {
    const val CLOCK = "clock"
    const val ALARM = "alarm"
    const val TIMER = "timer"
    const val STOPWATCH = "stopwatch"
    const val TEXT = "text"
}

/** Monotonic run state shared by timer and stopwatch snapshots. */
enum class ClockRunState {
    IDLE,
    RUNNING,
    PAUSED
}
