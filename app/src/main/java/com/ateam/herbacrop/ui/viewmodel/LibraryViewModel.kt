package com.ateam.herbacrop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ateam.herbacrop.core.domain.model.LibraryModel
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.usecase.PlantUseCase

class LibraryViewModel constructor(private val useCase: PlantUseCase) : ViewModel()  {
    fun getIndoorList(query: LibraryModel): LiveData<List<PlantModel>> = useCase.getLibraryIndoor(query)
    fun getOutdoorList(query: LibraryModel): LiveData<List<PlantModel>> = useCase.getLibraryIndoor(query)
    fun getHerbalList(query: LibraryModel): LiveData<List<PlantModel>> = useCase.getLibraryIndoor(query)
}