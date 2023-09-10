package com.spookybrain.kmm

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val CONTENT_LENGTH_KEY = "content-length"

suspend fun measureSingleFile(url: String, sizeType: SizeType = SizeType.BYTE): Double {
    val result = client.head(url)
    val byteHeight = result.headers[CONTENT_LENGTH_KEY]?.toDouble() ?: 0.0
    return toSizeType(byteHeight, sizeType)
}

suspend fun measureSingleFileWithSizeName(url: String, sizeType: SizeType): Pair<Double, String> {
    return Pair(measureSingleFile(url, sizeType), sizeType.sizeName())
}

suspend fun measureFiles(urls: List<String>, sizeType: SizeType = SizeType.BYTE): List<Double> {
    return urls.map { measureSingleFile(it, sizeType) }
}

suspend fun measureFiles(urlFiles: Map<String, String>, sizeType: SizeType = SizeType.BYTE): Map<String, Double> {
    return urlFiles.map {  it.key to measureSingleFile(it.value, sizeType) }.toMap()
}

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

internal fun toSizeType(size: Double, type: SizeType): Double = size.div(type.times())
