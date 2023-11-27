package pe.cibertec.appmyvideo_joanrojas.models

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.cibertec.appmyvideo_joanrojas.R
import pe.cibertec.appmyvideo_joanrojas.VideoPlayerActivity

class VideoAdapter(private val mList: List<VideoViewModel>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        // vincular variables con el diseño
        val titulo: TextView = itemView.findViewById(R.id.lbl_title)
        val subtitulo: TextView = itemView.findViewById(R.id.lbl_subtitle)
        val url: TextView = itemView.findViewById(R.id.lbl_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        //vincular las variables con los atributos el modelo de datos
        holder.titulo.text = "Titulo: ${item.title}"
        holder.subtitulo.text = "Subtitulo: ${item.subtitle}"
        holder.url.text = "Url del video: ${item.sources}"

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, VideoPlayerActivity::class.java)
            intent.putExtra("VIDEO_URL", item.sources)
            it.context.startActivity(intent)
        }
    }

}