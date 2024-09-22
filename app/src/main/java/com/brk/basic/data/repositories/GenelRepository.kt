package com.brk.basic.data.repositories


import com.brk.basic.data.TextToImageRequest
import com.brk.basic.data.api.ApiHelper

import com.brk.basic.model.responses.ImageResponse
import retrofit2.Call

class GenelRepository(private val apiHelper: ApiHelper) {

    // Metin tabanlı bir istek göndererek görsel oluşturur
    fun generateImage(request: TextToImageRequest): Call<ImageResponse> {
        return apiHelper.generateImage(request)
    }
}
