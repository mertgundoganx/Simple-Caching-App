package com.mertgundoganx.simplecaching.api

import com.mertgundoganx.simplecaching.data.Restaurant
import retrofit2.http.GET

interface RestaurantApi {
    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): List<Restaurant>
}