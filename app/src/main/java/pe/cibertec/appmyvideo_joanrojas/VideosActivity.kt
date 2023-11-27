package pe.cibertec.appmyvideo_joanrojas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pe.cibertec.appmyvideo_joanrojas.models.*

class VideosActivity : AppCompatActivity() {
    private val videoList = mutableListOf<VideoViewModel>()
    private lateinit var adapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)

        //Se Inicializa el RecyclerView y se configura el adaptador en el hilo principal
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_videos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VideoAdapter(videoList)
        recyclerView.adapter = adapter

        // Inicializar el retrofit
        val quotesAPI = RetrofitHelper.getRetrofitInstance().create(QuoteApi::class.java)

        GlobalScope.launch {
            // Llamada a la api (interface)
            val response = quotesAPI.getQuotes()

            // Condicionamos si es exitoso la consulta
            if (response.isSuccessful) {
                val videos = response.body()?.videos
                // iterarar para recorrer el objeto VideoViewModel
                if (videos != null) {
                    for (video in videos) {
                        val videoViewModel = VideoViewModel(
                            title = video.title,
                            subtitle = video.subtitle,
                            sources = video.sources.firstOrNull()!!
                        )

                        videoList.add(videoViewModel)
                    }

                    runOnUiThread {
                        // Notificar el error
                        adapter.notifyDataSetChanged()
                    }
                }
            } else {
                // Si hay algun error depuramos el codigo para encontrar el error
                Log.e("BOOK REST ERROR: ", "Error en la solicitud de la API")
            }
        }
    }
}
