package com.jieun.imagebrowser.model.datasource

import com.jieun.imagebrowser.model.data.SearchImageData
import com.jieun.imagebrowser.network.ApiService
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색 구현체
 */
class SearchImageDatasourceImpl(private val apiService: ApiService) : SearchImageDatasource {
    override suspend fun getSearchImageData(map: Map<String, String>): Response<List<SearchImageData?>> {
        return apiService.getSearchImages(map)
    }

    override suspend fun getImageChange(method:String?, map: Map<String, String>):Response<ResponseBody?> {
        return apiService.getImageChange(method.toString(), map)
    }
}