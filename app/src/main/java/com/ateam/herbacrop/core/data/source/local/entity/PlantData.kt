package com.ateam.herbacrop.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ateam.herbacrop.core.utils.Constants

@Entity(tableName = Constants.tableName)
data class PlantData(
    @PrimaryKey
    var id: Int = 0,
    var budidaya: String = "",
    var caraMenanam: String = "",
    var image: String ="",
    var manfaat: String ="",
    var name: String = "",
    var type: String = "",
    var favorite: Boolean = false
)
