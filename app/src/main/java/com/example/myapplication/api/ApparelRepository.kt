package com.example.myapplication.api

import com.example.myapplication.model.data.Apparel

class ApparelRepository(private val apiService: APIService): APIService {
    override suspend fun getAllApparels(): List<Apparel> {
        return apiService.getAllApparels()
    }

    override suspend fun getKidsApparels(): List<Apparel> {
        return apiService.getKidsApparels()
    }

    override suspend fun getMenApparels(): List<Apparel> {
        return apiService.getMenApparels()
    }

    override suspend fun getWomenApparels(): List<Apparel> {
        return apiService.getWomenApparels()
    }
}