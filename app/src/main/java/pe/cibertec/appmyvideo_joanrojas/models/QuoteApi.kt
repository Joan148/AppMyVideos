package pe.cibertec.appmyvideo_joanrojas.models

import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {

    @GET("listaVideos")
    suspend fun getQuotes(): Response<QuotesList>
}
