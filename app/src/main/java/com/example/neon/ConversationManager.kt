package com.example.neon

import android.content.Context
import android.widget.Toast
import java.util.Calendar

object ConversationManager {
    private val commands = listOf(
        "hello", "hi", "hey",
        "how are you",
        "what is your name",
        "do you have feelings", "do you have feeling",
        "what time is it", "what's the time",
        "you are useless",
        "thank you", "thanks",
        "who created you", "who is your creator",
        "what can you do",
        "good morning", "good night",
        "tell me a joke",
        "i love you",
        "are you real"
    )

    fun canHandle(command: String): Boolean {
        return commands.any { command.contains(it, ignoreCase = true) }
    }

    fun handle(context: Context, command: String) {
        when {
            command.contains("hello", true) || command.contains("hi", true) || command.contains("hey", true) -> {
                speak(context, "Hey there! How can I help you?")
            }

            command.contains("how are you", true) -> {
                speak(context, "I'm doing great! Thanks for asking. What about you?")
            }

            command.contains("what is your name", true) -> {
                speak(context, "My name is Neon. I'm your  voice assistant.")
            }

            command.contains("do you have feelings", true) || command.contains("do you have feeling", true) -> {
                speak(context, "Not really, but I do understand emotions.")
            }

            command.contains("you are useless", true) -> {
                speak(context, "That's not very nice. I'm still learning.")
            }

            command.contains("thank you", true) || command.contains("thanks", true) -> {
                speak(context, "You're welcome!")
            }

            command.contains("who created you", true) || command.contains("who is your creator", true) -> {
                speak(context, "I was created by a very talented developer. Maybe it's you?")
            }

            command.contains("what can you do", true) -> {
                speak(context, "I can play music, tell you the time, and have basic conversations with you.")
            }

            command.contains("good morning", true) -> {
                speak(context, "Good morning! Wishing you a productive day ahead.")
            }

            command.contains("good night", true) -> {
                speak(context, "Good night! Sweet dreams.")
            }

            command.contains("tell me a joke", true) -> {
                speak(context, "Why don’t scientists trust atoms? Because they make up everything!")
            }

            command.contains("i love you", true) -> {
                speak(context, "Aww! I'm flattered, but I'm just lines of code.")
            }

            command.contains("are you real", true) -> {
                speak(context, "As real as your imagination wants me to be.")
            }

            command.contains("what time is it", true) || command.contains("what's the time", true) -> {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)
                speak(context, "It's currently $hour hours and $minute minutes.")
            }

            else -> {
                speak(context, "Sorry, I didn't understand that.")
            }
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
