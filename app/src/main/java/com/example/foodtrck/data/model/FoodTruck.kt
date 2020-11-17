package com.example.foodtrck.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FoodTrucks")
data class FoodTruck(
    @SerializedName("identifier")
    @PrimaryKey
    val id: String,
    val rating: Int,
    val name: String,
    val url: String?,
    val phone: String?,
    val description: String?,
    val description_short: String?,
    val images: Images? = null
) {
    data class Images(
        var logo: String,
        var logo_small: String,
        var header: List<String>
    )
}