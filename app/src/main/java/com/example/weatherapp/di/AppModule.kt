package com.example.weatherapp.di

import com.example.weatherapp.util.Constants
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.service.ApiService
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.data.source.datasource.IDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(Constants.DefaultValue.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.DefaultValue.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.DefaultValue.TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.metaweather.com/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDataSource(remoteDataSource: RemoteDataSource): IDataSource = remoteDataSource
}
