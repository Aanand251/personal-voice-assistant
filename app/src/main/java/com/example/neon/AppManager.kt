package com.example.neon

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.neon.MainActivity

object AppManager {
    fun canHandle(command: String): Boolean {
        return command.contains("open youtube") ||
                command.contains("open google") ||
                command.contains("open facebook") ||
                command.contains("open instagram") ||
                command.contains("open whatsapp")||
                command.contains("open maps") ||
                command.contains("open map")
    }

    fun handle(context: Context, command: String) {
        when {
            command.contains("open youtube") -> {
                speak(context, "Opening YouTube.")
                openUrl(context, "https://www.youtube.com")
            }

            command.contains("open google") -> {
                speak(context, "Opening Google.")
                openUrl(context, "https://www.google.com")
            }

            command.contains("open facebook") -> {
                speak(context, "Opening Facebook.")
                openUrl(context, "https://www.facebook.com")
            }

            command.contains("open instagram") -> {
                speak(context, "Opening Instagram.")
                openUrl(context, "https://www.instagram.com")
            }
            command.contains("open maps") -> {
                speak(context, "Opening Google Maps.")
                openMaps(context)
            }
            command.contains("open map") -> {
                speak(context, "Opening Google Maps.")
                openMaps(context)
            }

            command.contains("open whatsapp") -> {
                try {
                    speak(context, "Opening WhatsApp.")
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("whatsapp://send?text=Hello")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                } catch (e: Exception) {
                    speak(context, "WhatsApp not found on this device.")
                }

            }
        }
    }

    private fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun openMaps(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }


    private fun speak(context: Context, text: String) {
        if (context is MainActivity) {
            context.speak(text)
        } else {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}