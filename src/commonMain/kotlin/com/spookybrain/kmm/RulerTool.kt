package com.spookybrain.kmm

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class RulerTool {
    private val client = HttpClient {
        install(HttpCallValidator)
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
                isLenient = true
            })
        }
    }

    suspend fun greeting(): String {
        val response = client.head("https://ktor.io/docs/")
        return response.headers.toString()
    }
}