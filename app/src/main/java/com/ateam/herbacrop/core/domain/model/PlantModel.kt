package com.ateam.herbacrop.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantModel(
    var id: Int = 0,
    var budidaya: String = "",
    var cara_menanam: String = "",
    var image: String ="",
    var manfaat: String ="",
    var nama: String = "",
    var type : String = "",
    var favorite: Boolean = false
) : Parcelable
