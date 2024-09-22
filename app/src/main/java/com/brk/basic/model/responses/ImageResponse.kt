package com.brk.basic.model.responses
data class ImageResponse(
    val status: String,
    val generationTime: Double,
    val id: Int,
    val output: List<String>, // Görsel URL'lerini içeren liste
    val proxy_links: List<String>, // Proxy linkleri
    val meta: MetaData // Ekstra meta veri
)

data class MetaData(
    val base64: String,
    val enhance_prompt: String,
    val enhance_style: String?,
    val file_prefix: String,
    val guidance_scale: Double,
    val height: Int,
    val instant_response: String,
    val n_samples: Int,
    val negative_prompt: String,
    val outdir: String,
    val prompt: String,
    val safety_checker: String,
    val safety_checker_type: String,
    val seed: Int,
    val temp: String,
    val width: Int
)