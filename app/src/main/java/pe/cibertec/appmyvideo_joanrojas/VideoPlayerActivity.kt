package pe.cibertec.appmyvideo_joanrojas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import pe.cibertec.appmyvideo_joanrojas.R

class VideoPlayerActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // Obtiene la URL del video del Intent
        val videoUrl = intent.getStringExtra("VIDEO_URL")

        // Crea un nuevo ExoPlayer
        player = SimpleExoPlayer.Builder(this).build()

        // Encuentra el PlayerView en el layout
        val playerView = findViewById<PlayerView>(R.id.video_view)

        // Asigna el ExoPlayer al PlayerView
        playerView.player = player

        // Prepara el ExoPlayer con la URL del video
        val mediaItem = MediaItem.fromUri(videoUrl!!)
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }

    // Al inciar el activity se reproduce el video.
    override fun onStart() {
        super.onStart()
        player?.playWhenReady = true
    }

    // Para detener el video.
    override fun onStop() {
        super.onStop()
        player?.playWhenReady = false
    }

    // Para destruir el activity y evitar sobrecargas
    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}
