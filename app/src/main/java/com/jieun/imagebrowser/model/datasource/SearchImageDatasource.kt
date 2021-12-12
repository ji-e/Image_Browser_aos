package com.jieun.imagebrowser.model.datasource

import com.jieun.imagebrowser.model.data.SearchImageData
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색
 */
interface SearchImageDatasource {
    suspend fun getSearchImageData(map: Map<String, String>): Response<List<SearchImageData?>>
    suspend fun getImageChange(method:String?, map: Map<String, String>):Response<ResponseBody?>
}