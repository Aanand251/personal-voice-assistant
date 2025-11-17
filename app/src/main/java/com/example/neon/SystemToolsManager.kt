package com.example.neon
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.hardware.camera2.CameraManager
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.IOException

object SystemToolsManager {

    private val commands = listOf(
        "turn on Wi-Fi", "turn off Wi-Fi",
        "turn on torch", "turn off torch",
        "enable flashlight", "disable flashlight",
        "turn on mobile data", "turn off mobile data",
        "enable dnd", "disable dnd",
        "flight mode on", "flight mode off",
        "take screenshot",
        "turn on hotspot", "turn off hotspot",
        "open camera", "camera"
    )

    fun canHandle(command: String): Boolean {
        return commands.any { command.contains(it) }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun handle(context: Context, command: String) {
        when {
            command.contains("turn on Wi-Fi") -> toggleWiFi(context, true)
            command.contains("turn off Wi-Fi") -> toggleWiFi(context, true)

            command.contains("turn on torch") || command.contains("enable flashlight") -> toggleTorch(context, true)
            command.contains("turn off torch") || command.contains("disable flashlight") -> toggleTorch(context, false)

            command.contains("turn on mobile data") -> openMobileDataSettings(context)
            command.contains("turn off mobile data") -> openMobileDataSettings(context)

            command.contains("enable dnd") -> enableDnd(context)
            command.contains("disable dnd") -> disableDnd(context)

            command.contains("flight mode on") -> openFlightModeSettings(context)
            command.contains("flight mode off") -> openFlightModeSettings(context)

            command.contains("take screenshot") -> speak(context, "I can't take a screenshot directly, but I can open the screenshot menu.")

            command.contains("turn on hotspot") || command.contains("turn off hotspot") -> openHotspotSettings(context)

            command.contains("open camera") || command.contains("camera") ->
                openCamera(context)
        }

    }

    @Suppress("DEPRECATION")
    private fun toggleWiFi(context: Context, enable: Boolean) {
        try {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager.isWifiEnabled = enable
            if (enable) speak(context, "WiFi turned on.")
            else speak(context, "WiFi turned off.")
        } catch (e: Exception) {
            speak(context, "Unable to toggle WiFi. Opening WiFi settings.")
            openWiFiSettings(context)
        }
    }

    private fun openWiFiSettings(context: Context) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }

    private fun toggleTorch(context: Context, enable: Boolean) {
        try {
            val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, enable)
            if (enable) speak(context, "Flashlight turned on.")
            else speak(context, "Flashlight turned off.")
        } catch (e: Exception) {
            speak(context, "Unable to toggle flashlight.")
            Log.e("TorchError", e.message ?: "Unknown error")
        }
    }

    private fun openMobileDataSettings(context: Context) {
        val intent = Intent(Settings.ACTION_DATA_USAGE_SETTINGS)
        context.startActivity(intent)
        speak(context, "Opening mobile data settings.")
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun enableDnd(context: Context) {
        try {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (!notificationManager.isNotificationPolicyAccessGranted) {
                speak(context, "I need permission to control Do Not Disturb.")
                val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return
            }

            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
            speak(context, "Do Not Disturb enabled.")
        } catch (e: Exception) {
            speak(context, "Unable to enable Do Not Disturb.")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun disableDnd(context: Context) {
        try {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (!notificationManager.isNotificationPolicyAccessGranted) {
                speak(context, "I need permission to control Do Not Disturb.")
                val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return
            }

            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
            speak(context, "Do Not Disturb disabled.")
        } catch (e: Exception) {
            speak(context, "Unable to disable Do Not Disturb.")
        }
    }


    private fun openFlightModeSettings(context: Context) {
        val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
        context.startActivity(intent)
        speak(context, "Opening flight mode settings.")
    }

    private fun openHotspotSettings(context: Context) {
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        context.startActivity(intent)
        speak(context, "Opening hotspot settings.")
    }

    private fun openCamera(context: Context) {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            speak(context, "Opening camera.")
        } catch (e: Exception) {
            speak(context, "Camera app not found.")
        }
    }

    private fun speak(context: Context, text: String) {
        if (context is MainActivity) {
            context.speak(text)
        } else {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

}