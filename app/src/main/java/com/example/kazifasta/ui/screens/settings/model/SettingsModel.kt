package com.example.kazifasta.ui.screens.settings.model

import com.example.kazifasta.ui.screens.settings.SettingsRoute
import java.util.UUID

data class SettingsModel(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val route: String
)

val SettingsList = listOf<SettingsModel>(
    SettingsModel(
        title = "General",
        description = "Adjust application general look and feel",
        route = SettingsRoute.GENERAL
    ),
    SettingsModel(
        title = "Notifications & Sounds",
        description = "Set preferences for alerts and reminders.",
        route = SettingsRoute.NOTIFICATION_SOUNDS
    ),
    SettingsModel(
        title = "Privacy & Security",
        description = "Manage how your data is collected and used.",
        route = SettingsRoute.PRIVACY_SECURITY
    ),
    SettingsModel(
        title = "Language & Region",
        description = "Choose your preferred language and regional settings.",
        route = SettingsRoute.LANGUAGE_REGION
    ),
    SettingsModel(
        title = "Help & Support",
        description = "Access FAQs, troubleshooting guides, and contact information",
        route = SettingsRoute.HELP_SUPPORT
    ),
)
