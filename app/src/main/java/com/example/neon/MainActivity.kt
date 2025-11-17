package com.example.neon


import android.hardware.camera2.CameraManager
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.neon.callmanager.CallManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val RECORD_AUDIO_PERMISSION_CODE = 1

    private lateinit var micButton: FloatingActionButton
    private lateinit var resultTextView: TextView

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        micButton = findViewById(R.id.micButton)
        resultTextView = findViewById(R.id.resultTextView)


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_PERMISSION_CODE)
        }


        textToSpeech = TextToSpeech(this, this)


        setupSpeechRecognizer()


        micButton.setOnClickListener {
            resultTextView.text = "Listening..."
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN")
            speechRecognizer.startListening(intent)
        }
    }

    private fun setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (data != null && data.isNotEmpty()) {
                    val spokenText = data[0]
                    resultTextView.text = spokenText
                    processCommand(spokenText)
                }
            }

            override fun onError(error: Int) {

                Log.e("SpeechRecognizer", "Error: $error")
                resultTextView.text = "Error listening, please try again."
            }


            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun processCommand(command: String) {
        val processedCommand = command.toLowerCase(Locale.ROOT).trim()

        when {
            CallManager.canHandle(processedCommand) -> CallManager.handle(this, processedCommand)
            MusicManager.canHandle(processedCommand) -> MusicManager.handle(this, processedCommand)
            AppManager.canHandle(processedCommand) -> AppManager.handle(this, processedCommand)
            ConversationManager.canHandle(processedCommand) -> ConversationManager.handle(this, processedCommand)
            SystemToolsManager.canHandle(processedCommand) -> SystemToolsManager.handle(this, processedCommand)
            GeneralKnowledgeManager.canHandle(processedCommand) -> GeneralKnowledgeManager.handle(this, processedCommand)
            else -> {
                speak("I'm sorry, I did not understand that. Can you please repeat?")
            }

        }
    }



    internal fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result = textToSpeech.setLanguage(Locale("en", "IN"))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {

                setMaleVoice()
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE) {
            if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                speak("Permission Denied. I cannot function without microphone access.")
                finish()
            }
        }
    }


    private fun setMaleVoice() {
        val availableVoices = textToSpeech.voices
        if (availableVoices.isNullOrEmpty()) {
            Log.d("TTS", "No voices found.")
            return
        }

        val maleVoice = availableVoices.find {
            it.name.contains("male", ignoreCase = true) && it.locale == Locale("en", "IN")
        }

        if (maleVoice != null) {
            textToSpeech.voice = maleVoice
            Log.d("TTS", "Male voice set: ${maleVoice.name}")
        } else {
            Log.d("TTS", "No suitable male voice found for en-IN. Using default.")
        }
    }

    fun makeCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        speechRecognizer.destroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroy()
    }
}