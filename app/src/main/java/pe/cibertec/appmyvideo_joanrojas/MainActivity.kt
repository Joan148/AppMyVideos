package pe.cibertec.appmyvideo_joanrojas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnVideoActivity = findViewById<Button>(R.id.btn_video_activity)

        // Evento para abrir un activity
        btnVideoActivity.setOnClickListener {
            startActivity(Intent(this, VideosActivity::class.java))
        }

    }
}