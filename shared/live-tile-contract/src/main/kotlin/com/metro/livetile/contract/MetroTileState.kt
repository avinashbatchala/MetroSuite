package com.metro.livetile.contract

/**
 * Generic, app-agnostic live tile snapshot. Future MetroSuite apps (Calendar, Mail, Music, …)
 * can expose this without the launcher knowing anything app-specific.
 *
 * Time-sensitive values are expressed as raw timestamps/monotonic clocks, never as a
 * pre-formatted string that would need re-sending every second.
 */
data class MetroTileState(
    val protocolVersion: Int = MetroLiveTileProtocol.VERSION,
    val providerId: String,
    val updatedAt: Long,
    val validUntil: Long = 0L,
    val template: String = MetroLiveTileTemplates.TEXT,
    val primaryText: String? = null,
    val secondaryText: String? = null,
    val tertiaryText: String? = null,
    val badge: String? = null,
    val imageUri: String? = null,
    val accessibilityText: String? = null
)
