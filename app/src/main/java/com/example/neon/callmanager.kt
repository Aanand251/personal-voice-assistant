package com.example.neon


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.neon.MainActivity

object callmanager {
    object CallManager {

        fun canHandle(command: String): Boolean {
            return command.contains("call")
        }

        fun handle(activity: MainActivity, command: String) {
            when {
                "call shagun"   in command -> activity.makeCall("9434182063")
                "call harsh"   in command -> activity.makeCall("9045834652")
                "call vivek patel"   in command -> activity.makeCall("9335739854")
                "call ashu beta"   in command -> activity.makeCall("7804940568")
                "call sangam"   in command -> activity.makeCall("9335740729")
                "call priyesh"   in command -> activity.makeCall("8544185740")
                "call prem"   in command -> activity.makeCall("9006444103")
                "call sachin"   in command -> activity.makeCall("9155869652")
                "call sarthak"   in command -> activity.makeCall("7300899537")
                "call ram uncle"  in command -> activity.makeCall("9334741872")
                "call kalua"   in command -> activity.makeCall("7247744956")
                "call akshat"   in command -> activity.makeCall("7217539649")
                "call ansh"   in command -> activity.makeCall("8077688382")
                "call bhai"   in command -> activity.makeCall("8797862094")
                "call mummy" in command -> activity.makeCall("9973291598")
                "call papa" in command -> activity.makeCall("8002921115")
                "call me"   in command -> activity.makeCall("9801620072")
                else -> activity.speak("Please specify a contact to call.")
            }
        }

    }
}
