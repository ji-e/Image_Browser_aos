package com.jieun.imagebrowser.model.repository

import com.jieun.imagebrowser.model.data.SearchImageData
import com.jieun.imagebrowser.model.datasource.SearchImageDatasource
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색 구현체
 */
class SearchImageRepositoryImpl(private val searchImageDatasource: SearchImageDatasource) : SearchImageRepository {

    override suspend fun getSearchImageData(map: Map<String, String>): Response<List<SearchImageData?>> {
        return searchImageDatasource.getSearchImageData(map)
    }

    override suspend fun getImageChange(methode:String?, map: Map<String, String>): Response<ResponseBody?> {
        return searchImageDatasource.getImageChange(methode, map)
    }

}