import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brk.basic.data.TextToImageRequest
import com.brk.basic.data.repositories.GenelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.brk.basic.model.responses.ImageResponse

class GenelViewModel(private val apiHelper: GenelRepository) : ViewModel() {

    val imageLiveData: MutableLiveData<String?> = MutableLiveData()

    fun generateImageFromText(text: String) {
        val request = TextToImageRequest(
            key = "YOUR API KEY ",  // Gerekli API anahtarını buraya ekleyin
            prompt = text,
            negative_prompt = "bad quality",
            width = "512",
            height = "512",
            safety_checker = false,
            seed = 2345,
            sample = 1,
            webhook = null,
            track_id = 20
        )
        apiHelper.generateImage(request).enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.proxy_links?.firstOrNull()
                    Log.d("GenelViewModel", "Image URL: $imageUrl")
                    imageLiveData.postValue(imageUrl)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("GenelViewModel", "Error: $errorBody")
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.e("GenelViewModel", "Failure: ${t.message}")
            }
        })
    }
}
