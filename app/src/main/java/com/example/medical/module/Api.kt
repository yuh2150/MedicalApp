
import com.example.medical.entity.District
import com.example.medical.entity.DistrictResponse
import com.example.medical.entity.Province
import com.example.medical.entity.ProvinceResponse
import com.example.medical.entity.Ward
import com.example.medical.entity.WardResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/province")
    suspend fun getProvinces(): ProvinceResponse

    @GET("/api/province/district/{province_id}")
    suspend fun getDistricts(@Path("province_id") provinceId: String): DistrictResponse

    @GET("/api/province/ward/{district_id}")
    suspend fun getWards(@Path("district_id") districtId: String): WardResponse
}

object ApiClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://vapi.vnappmob.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
