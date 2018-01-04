package chrosciu.com.firstapplication

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.AudioManager.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.content.Intent
import android.content.Context.AUDIO_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.content.Context


class MainActivity : AppCompatActivity() {

    private val audioManager:AudioManager by lazy {getSystemService(AUDIO_SERVICE) as AudioManager}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askForPermission()
        readRingerMode()
        handleRadioGroup()
    }

    @SuppressLint("NewApi")
    private fun askForPermission() {
        val n = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (!n.isNotificationPolicyAccessGranted) {
            val intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivityForResult(intent, 0)
        }
    }

    private fun readRingerMode() {
        initRadios(audioManager.ringerMode)
    }

    private fun setRingerMode(ringerMode: Int) {
        audioManager.ringerMode = ringerMode
    }

    private fun handleRadioGroup() {
        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            val ringerMode = when(i) {
                R.id.radio1 -> RINGER_MODE_NORMAL
                R.id.radio2 -> RINGER_MODE_VIBRATE
                R.id.radio3 -> RINGER_MODE_SILENT
                else -> RINGER_MODE_NORMAL
            }
            setRingerMode(ringerMode)
        }
    }

    private fun initRadios(ringerMode: Int) {
        when(ringerMode) {
            RINGER_MODE_NORMAL -> {radio1.isChecked = true}
            RINGER_MODE_VIBRATE -> {radio2.isChecked = true}
            RINGER_MODE_SILENT -> {radio3.isChecked = true}
        }
    }
}
