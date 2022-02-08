package com.mertgundoganx.simplecaching.di

import android.app.Application
import androidx.room.Room
import com.mertgundoganx.simplecaching.api.RestaurantApi
import com.mertgundoganx.simplecaching.data.RestaurantDao
import com.mertgundoganx.simplecaching.data.RestaurantDatabase
import com.mertgundoganx.simplecaching.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)

    @Provides
    @Singleton
    fun provideRestaurantDao(restaurantDatabase: RestaurantDatabase): RestaurantDao =
        restaurantDatabase.restaurantDao()

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RestaurantDatabase =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, "restaurant_database")
            .build()

}