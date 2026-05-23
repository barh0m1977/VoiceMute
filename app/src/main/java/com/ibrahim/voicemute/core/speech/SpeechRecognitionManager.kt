package com.ibrahim.voicemute.core.speech

import android.content.Context
import android.content.Intent
import android.util.Log
import com.ibrahim.voicemute.core.service.VoiceRecognitionForegroundService
import com.ibrahim.voicemute.core.speech.RecognitionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService
import org.vosk.android.StorageService
import java.io.File
class SpeechRecognitionManager(
    private val context: Context
) {

    companion object {
        private const val TARGET_PHRASE = "الله"
        private const val COOLDOWN = 8000L // faster response
    }

    private val _state = MutableStateFlow<RecognitionState>(RecognitionState.Idle)
    val state = _state.asStateFlow()

    private var speechService: SpeechService? = null
    private var recognizer: Recognizer? = null
    private var lastTriggerTime = 0L

    suspend fun startListening() = withContext(Dispatchers.IO) {
        try {
            _state.value = RecognitionState.Listening

            val modelDir = File(context.filesDir, "model/vosk_model_ar_mgb2_0.4")
            copyAssetFolder(context, "vosk_model_ar_mgb2_0.4", modelDir)
            Log.e("TestApp","${modelDir.isDirectory}")
            val model = Model(modelDir.absolutePath)

            initializeRecognizer(model)

        } catch (e: Exception) {
            _state.value = RecognitionState.Error(e.message ?: "Recognition Error")
        }
    }

    private fun initializeRecognizer(model: Model) {

        recognizer = Recognizer(model, 16000.0f,  """["الله"]""")

        speechService = SpeechService(recognizer, 16000.0f)

        // ⚡ enable faster / cleaner audio if possible
        speechService?.startListening(listener)
    }

    private val listener = object : RecognitionListener {
        private var lastProcessTime = 0L

        override fun onPartialResult(hypothesis: String?) {

            val now = System.currentTimeMillis()

            if (now - lastProcessTime < 250) return
            lastProcessTime = now

            if (hypothesis == null) return

            if (hypothesis.contains("الله")) {
                triggerDetected()
                Log.d("TestApp", "FINAL: $hypothesis")
                context.stopService(
                    Intent(
                        context,
                        VoiceRecognitionForegroundService::class.java
                    )
                )
                stopListening()
                return
            }
            detectKeyword(hypothesis)
        }

        override fun onResult(hypothesis: String?) {
            detectKeyword(hypothesis)
        }

        override fun onFinalResult(hypothesis: String?) {
            Log.d("TestApp", "FINAL: $hypothesis")
        }

        override fun onError(e: Exception?) {
            _state.value = RecognitionState.Error(e?.message ?: "Recognition Failed")
        }

        override fun onTimeout() {}
    }

    private fun triggerDetected() {
        val now = System.currentTimeMillis()

        if (now - lastTriggerTime < COOLDOWN) return

        lastTriggerTime = now
        _state.value = RecognitionState.Detected(TARGET_PHRASE)
    }

    private fun detectKeyword(hypothesis: String?) {

        if (hypothesis.isNullOrBlank()) return

        try {
            val json = JSONObject(hypothesis)

            val text = json.optString("text")
            val partial = json.optString("partial")

            val result = if (text.isNotBlank()) text else partial
            if (result.isBlank()) return

            val normalized = normalizeArabic(result)
            val target = normalizeArabic(TARGET_PHRASE)

            if (normalized.contains(target)) {
                triggerDetected()
            }

        } catch (e: Exception) {
            Log.e("TestApp", "Parse error", e)
        }
    }

    private fun normalizeArabic(text: String): String {
        return text
            .replace("أ", "ا")
            .replace("إ", "ا")
            .replace("آ", "ا")
            .replace("ة", "ه")
            .trim()
    }

    private fun copyAssetFolder(context: Context, assetDir: String, destDir: File) {
        if (destDir.exists()) return
        destDir.mkdirs()

        val assets = context.assets.list(assetDir) ?: return

        for (file in assets) {
            val outFile = File(destDir, file)

            context.assets.open("$assetDir/$file").use { input ->
                outFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

    fun stopListening() {
        try {
            speechService?.stop()
            speechService?.shutdown()
        } catch (e: Exception) {
            Log.e("Speech", "stop error", e)
        }

        speechService = null
        recognizer = null
        _state.value = RecognitionState.Idle
    }
}