package com.mertgundoganx.simplecaching.data

import androidx.room.withTransaction
import com.mertgundoganx.simplecaching.api.RestaurantApi
import com.mertgundoganx.simplecaching.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val restaurantDao: RestaurantDao,
    private val db: RestaurantDatabase
) {

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()
        },
        fetch = {
            delay(2000)
            api.getRestaurants()
        },
        saveFetchResult = { restaurants ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(restaurants)
            }
        }
    )
}