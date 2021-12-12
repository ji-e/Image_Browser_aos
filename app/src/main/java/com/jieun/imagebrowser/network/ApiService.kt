package com.jieun.imagebrowser.network

import com.jieun.imagebrowser.model.data.SearchImageData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * date 2021-12-12
 * create by jieun
 */
interface ApiService {
    // 이미지 검색
    @GET(NetworkConstants.LIST)
    suspend fun getSearchImages(@QueryMap mapString: Map<String, String>?): Response<List<SearchImageData?>>

    @GET("{method}")
    suspend fun getImageChange(@Path("method") method: String, @QueryMap mapString: Map<String, String>?):Response<ResponseBody?>
}