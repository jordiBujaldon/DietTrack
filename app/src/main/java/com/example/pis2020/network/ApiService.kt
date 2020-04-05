package com.example.pis2020.network

import com.example.pis2020.network.model.NetworkFood
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import kotlinx.coroutines.Deferred

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://world.openfoodfacts.org/api/v0/product/"

// Primer construim l'objecte que ens permetra convertir l'String JSON
// a objectes Kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Despres construim l'objecte que ens permetra convertir el JSON a String
// amb les dades que ens interessen.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))   // Passem l'String directament
                                                                // a moshi
    .addCallAdapterFactory(CoroutineCallAdapterFactory())       // Gestiona les corrutines de l'API
    .baseUrl(BASE_URL)
    .build()

interface OpenFoodApiService {
    @GET("{barcode}")
    fun getFood(@Path("barcode") barcode: String): Deferred<NetworkFood>
}

object OpenFoodApi {
    val retorfitService: OpenFoodApiService by lazy {
        retrofit.create(OpenFoodApiService::class.java)
    }
}