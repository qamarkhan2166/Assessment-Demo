package com.example.mvvmdemoproject.di.remote

import com.example.mvvmdemoproject.BuildConfig
import com.example.mvvmdemoproject.data.remote.ApiService
import com.example.mvvmdemoproject.data.remote.NetworkingConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun providesBaseUrl(): String {
        return NetworkingConstants.PHP_BASE_URL
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

    @Provides
    @Singleton
    fun providesBasicAuthInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
            val requestBuilder = request.newBuilder()
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, basicAuthInterceptor: Interceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(20, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS)
        okHttpClient.readTimeout(20, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.addInterceptor(basicAuthInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory():
        Converter.Factory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setPrettyPrinting()
                .setLenient().create()
        )
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
