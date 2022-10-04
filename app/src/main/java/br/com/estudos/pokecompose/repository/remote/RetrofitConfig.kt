package br.com.estudos.pokecompose.repository.remote

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(private val baseUrl: String, private val context: Context?) {

    private fun getRetroInstance() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpProvider.getOkHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokeServide(): PokeService = getRetroInstance().create(PokeService::class.java)
}