package ru.netcracker.sviridov

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.netcracker.sviridov.api.BASE_URL
import ru.netcracker.sviridov.api.MetaWeatherApi

/**
 * Created by Turkin A. on 30/10/2018.
 */
class App : Application() {

    private lateinit var mRetrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        mRetrofit = buildRetrofit()
        mApi = mRetrofit.create(MetaWeatherApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        val okHttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            okHttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(okHttpLoggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                clientBuilder
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        const val TAG = "App"

        private lateinit var mApi: MetaWeatherApi

        @JvmStatic
        fun api() = mApi
    }

}