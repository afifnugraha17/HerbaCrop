package com.ateam.herbacrop.core.domain.model

data class PlantModel(
    var id: Int = 0,
    var budidaya: String = "",
    var caraMenanam: String = "",
    var image: String ="",
    var manfaat: String ="",
    var name: String = "",
    var favorite: Boolean = false
)
