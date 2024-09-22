package com.brk.basic.data

data class TextToImageRequest(
    val key: String,
    val prompt: String,
    val negative_prompt: String,
    val width: String,
    val height: String,
    val safety_checker: Boolean,
    val seed: Int,
    val sample: Int,
    val webhook: String?,
    val track_id: Int
)
