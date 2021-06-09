package com.ateam.herbacrop.core.utils

import com.ateam.herbacrop.core.data.source.local.entity.PlantData
import com.ateam.herbacrop.core.domain.model.PlantModel

object DataMapper {
    fun entitiesToDomain(data : List<PlantData>) : List<PlantModel> =
        data.map {
            PlantModel(
                it.id,
                it.budidaya,
                it.caraMenanam,
                it.image,
                it.manfaat,
                it.name,
                it.type,
                it.favorite
            )
        }

    fun domainToEntity(data: PlantModel) : PlantData = PlantData(
        data.id,
        data.budidaya,
        data.cara_menanam,
        data.image,
        data.manfaat,
        data.nama,
        data.type,
        true
    )
}