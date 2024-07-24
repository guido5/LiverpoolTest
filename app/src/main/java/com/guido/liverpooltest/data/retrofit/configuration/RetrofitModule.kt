package com.guido.liverpooltest.data.retrofit.configuration

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofitInstance() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttp3 = OkHttpClient.Builder().apply {
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(20, TimeUnit.SECONDS)
            connectTimeout(20, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/")
            .client(okhttp3)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiServices(retrofit: Retrofit) : ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}