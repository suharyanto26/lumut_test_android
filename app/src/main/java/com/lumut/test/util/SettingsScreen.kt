package com.lumut.test.util

import android.content.Intent
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Build.BRAND
import android.provider.Settings
import android.widget.Toast


class SettingsScreen(val context: Context) {

    private fun _showSettingScreen(intentStr: String) {
        try {
            val intent = Intent(intentStr)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    fun showSettingScreen() {
        _showSettingScreen("android.settings.SETTINGS")
    }

    fun showAPNScreen() {
        _showSettingScreen("android.settings.APN_SETTINGS")
    }

    fun showLocationScreen() {
        _showSettingScreen("android.settings.LOCATION_SOURCE_SETTINGS")
    }

    fun showSecurityScreen() {
        _showSettingScreen("android.settings.SECURITY_SETTINGS")
    }

    fun showWifiScreen() {
        _showSettingScreen("android.settings.WIFI_SETTINGS")
    }

    fun showBluetoothScreen() {
        _showSettingScreen("android.settings.BLUETOOTH_SETTINGS")
    }

    fun showDateScreen() {
        _showSettingScreen("android.settings.DATE_SETTINGS")
    }

    fun showSoundScreen() {
        _showSettingScreen("android.settings.SOUND_SETTINGS")
    }

    fun showDisplayScreen() {
        _showSettingScreen("android.settings.DISPLAY_SETTINGS")
    }

    fun showApplicationScreen() {
        _showSettingScreen("android.settings.APPLICATION_SETTINGS")
    }

    fun showNetworkSettingScreen() {
        showDataRoamingScreen()
    }

    fun showNetworkOperatorScreen() {
        if (Build.VERSION.SDK_INT >= 19) {
            _showSettingScreen("android.settings.NETWORK_OPERATOR_SETTINGS")
        } else {
            val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
            intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun showDataRoamingScreen() {
        if (Build.VERSION.SDK_INT >= 19) {
            _showSettingScreen("android.settings.DATA_ROAMING_SETTINGS")
        } else {
            val intent = Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)
            val cName = ComponentName("com.android.phone", "com.android.phone.Settings")
            intent.setComponent(cName)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    fun showDataMobileScreen() {
        if (Build.VERSION.SDK_INT >= 19) {
            val intent =
                Intent(Settings.ACTION_WIRELESS_SETTINGS)//android.provider.Settings.ACTION_SETTINGS //Intent.ACTION_MAIN
            intent.setClassName("com.android.settings", "com.android.settings.Settings\$DataUsageSummaryActivity")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {
            showDataRoamingScreen()
        }
    }

    fun showNotificationScreen() {
        _showSettingScreen("android.settings.NOTIFICATION_SETTINGS")
    }

    fun showBatterySaverScreen() {
        _showSettingScreen("android.settings.BATTERY_SAVER_SETTINGS")
    }

    fun showNfcScreen() {
        _showSettingScreen("android.settings.NFC_SETTINGS")
    }

    fun showInternalStorageScreen() {
        _showSettingScreen("android.settings.INTERNAL_STORAGE_SETTINGS")
    }

    fun showDictionarySettingScreen() {
        _showSettingScreen("android.settings.USER_DICTIONARY_SETTINGS")
    }

    fun showManageApplicationsScreen() {
        _showSettingScreen("android.settings.MANAGE_APPLICATIONS_SETTINGS")
    }

    fun showManageAllApplicationsScreen() {
        _showSettingScreen("android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS")
    }

    fun showMemoryCardScreen() {
        _showSettingScreen("android.settings.MEMORY_CARD_SETTINGS")
    }

    fun showAirPlaneScreen() {
        if (Build.VERSION.SDK_INT >= 19) {
            if (BRAND.equals("Lenovo", true)) {
                showSettingScreen()
            } else {
                _showSettingScreen("android.settings.WIRELESS_SETTINGS")
            }
        } else {
            _showSettingScreen("android.settings.AIRPLANE_MODE_SETTINGS")
        }
    }

    fun showWirelessScreen() {
        _showSettingScreen("android.settings.WIRELESS_SETTINGS")
    }

    fun showWifiScreenSafe() {
        try {
            val intent = Intent(Intent.ACTION_MAIN, null)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val cn = ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings")
            intent.component = cn
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (e: Exception) {
        }

    }
}

