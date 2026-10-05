package com.metro.livetile.contract

/** Next enabled alarm as understood by the launcher. */
data class ClockNextAlarmState(
    val alarmId: Int,
    val triggerEpochMillis: Long,
    val label: String?,
    val enabled: Boolean
)

/**
 * Active timer. The launcher computes the visible countdown locally:
 * `remaining = endElapsedRealtime - SystemClock.elapsedRealtime()` (RUNNING),
 * or [remainingWhenPausedMillis] (PAUSED).
 */
data class ClockTimerState(
    val state: ClockRunState,
    val endElapsedRealtime: Long,
    val remainingWhenPausedMillis: Long,
    val label: String? = null
)

/**
 * Active stopwatch. The launcher computes visible elapsed locally from the monotonic clock:
 * `elapsed = accumulatedElapsedMillis + (nowElapsed - startElapsedRealtime)` (RUNNING),
 * or [accumulatedElapsedMillis] (PAUSED).
 */
data class ClockStopwatchState(
    val state: ClockRunState,
    val startElapsedRealtime: Long,
    val accumulatedElapsedMillis: Long
)

/**
 * Clock-specific live tile snapshot. Generic fields (protocolVersion/providerId/updatedAt)
 * mirror [MetroTileState]; the launcher's Clock adapter maps this to a [MetroTileState] for
 * the generic renderer.
 */
data class ClockTileState(
    val protocolVersion: Int = MetroLiveTileProtocol.VERSION,
    val providerId: String = MetroLiveTileProtocol.CLOCK_PROVIDER_ID,
    val updatedAt: Long,
    val currentEpochMillis: Long,
    val nextAlarm: ClockNextAlarmState? = null,
    val timer: ClockTimerState? = null,
    val stopwatch: ClockStopwatchState? = null
) {
    /** Deterministic template priority: timer, stopwatch, next alarm, then plain clock. */
    val template: String
        get() = when {
            timer != null && timer.state != ClockRunState.IDLE -> MetroLiveTileTemplates.TIMER
            stopwatch != null && stopwatch.state != ClockRunState.IDLE -> MetroLiveTileTemplates.STOPWATCH
            nextAlarm != null && nextAlarm.enabled -> MetroLiveTileTemplates.ALARM
            else -> MetroLiveTileTemplates.CLOCK
        }
}
