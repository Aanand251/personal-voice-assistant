package com.example.neon

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.util.Log

object MusicManager {

    private var tts: TextToSpeech? = null

    fun init(textToSpeech: TextToSpeech) {
        tts = textToSpeech
    }

    fun canHandle(command: String): Boolean {
        return command.contains("play music") ||
                command.contains("music") ||
                command.contains("open spotify") ||
                command.contains("play")
    }

    fun handle(context: Context, command: String) {
        Log.d("MusicManager", "Handling music command: $command")
        val songName = extractSongName(command)

        if (songName != null) {
            playSpecificSong(context, songName)
        } else {
            openSpotifyApp(context)
        }
    }

    private fun extractSongName(command: String): String? {
        return if (command.startsWith("play")) {
            command.replace("play", "")
                .replace("on spotify", "")
                .trim()
                .takeIf { it.isNotEmpty() }
        } else null
    }

    private fun playSpecificSong(context: Context, songName: String) {
        try {
            speak("Playing $songName on Spotify.")
            val encodedSong = Uri.encode(songName)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("spotify:search:$encodedSong")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            speak("Spotify is not installed on this device.")
            Log.e("MusicManager", "Error playing specific song", e)
        }
    }

    private fun openSpotifyApp(context: Context) {
        try {
            speak("Opening Spotify.")
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("spotify:")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            speak("Spotify is not installed on this device.")
            Log.e("MusicManager", "Error opening Spotify", e)
        }
    }

    private fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}
