package space.quiz.footballapp.utils

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.quiz.footballapp.api.SimpleApi

object RetrofitInstance {

    const val BASE_URL = "http://api.football-data.org"
    const val TOKEN = "d2ecf60e45614a9682ff4200649790c6"

    private val retrofit by lazy {

        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    val request = chain
                        .request()
                        .newBuilder()
                        .addHeader("X-Auth-Token", TOKEN)
                        .build()
                    chain.proceed(request)
                }.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}