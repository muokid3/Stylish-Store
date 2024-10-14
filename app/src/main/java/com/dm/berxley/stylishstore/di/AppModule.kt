package com.dm.berxley.stylishstore.di

import android.app.Application
import androidx.room.Room
import com.dm.berxley.stylishstore.data.local.AppDatabase
import com.dm.berxley.stylishstore.data.local.ImageListConverter
import com.dm.berxley.stylishstore.data.remote.AppApi
import com.dm.berxley.stylishstore.data.repositories.AuthRepositoryImpl
import com.dm.berxley.stylishstore.data.sharedprefs.LocalUserManagerImpl
import com.dm.berxley.stylishstore.domain.repositories.AuthRepository
import com.dm.berxley.stylishstore.domain.sharedprefs.LocalUserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideRetrofitApi(): AppApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(AppApi.BASE_URL)
            .build()
            .create(AppApi::class.java)

    }

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {

        return Room.databaseBuilder(
            context = application,
            klass = AppDatabase::class.java,
            name = AppDatabase.APP_DB_NAME
        )
            .addTypeConverter(ImageListConverter::class)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(appApi: AppApi): AuthRepository = AuthRepositoryImpl(appApi)
}