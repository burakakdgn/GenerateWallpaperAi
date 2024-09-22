package com.brk.basic.data.api

import ApiInterface
import com.brk.basic.model.responses.ImageResponse
import com.brk.basic.data.TextToImageRequest
import retrofit2.Call


class ApiHelper(private val apiInterface: ApiInterface) {


    fun generateImage(request: TextToImageRequest): Call<ImageResponse> {
        return apiInterface.generateImage(request)
    }


}