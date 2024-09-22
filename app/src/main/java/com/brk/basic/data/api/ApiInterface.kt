import com.brk.basic.data.TextToImageRequest
import com.brk.basic.model.responses.ImageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/v6/realtime/text2img")
    fun generateImage(@Body request: TextToImageRequest): Call<ImageResponse>
}

