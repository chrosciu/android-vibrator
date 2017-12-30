package chrosciu.com.firstapplication

import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val audioManager:AudioManager by lazy {getSystemService(AUDIO_SERVICE) as AudioManager}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        readRingerMode()
        handleToggleButton()
    }

    private fun readRingerMode() {
        displayRingerMode(audioManager.ringerMode)
    }

    private fun setRingerMode(ringerMode: Int) {
        audioManager.ringerMode = ringerMode
        displayRingerMode(ringerMode)
    }

    private fun displayRingerMode(ringerMode: Int) {
        textView.text = ringerMode.toString()
    }

    private fun handleToggleButton() {
        toggleButton.setOnCheckedChangeListener({ _, isChecked ->
            setRingerMode(if (isChecked) AudioManager.RINGER_MODE_VIBRATE else AudioManager.RINGER_MODE_NORMAL)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
