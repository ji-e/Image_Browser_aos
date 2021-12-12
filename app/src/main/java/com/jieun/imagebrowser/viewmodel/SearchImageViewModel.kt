package com.jieun.imagebrowser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jieun.imagebrowser.model.data.SearchImageData
import com.jieun.imagebrowser.model.repository.SearchImageRepository
import com.jieun.imagebrowser.network.NetworkConstants
import com.jieun.imagebrowser.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색
 */
class SearchImageViewModel(private val searchImageRepository: SearchImageRepository) : ViewModel() {
    // 로딩
    private val _liveLoading = SingleLiveEvent<Boolean>()
    var liveLoading: SingleLiveEvent<Boolean> = _liveLoading

    // 이미지 검색 목록
    private val _liveSearchImageResult = MutableLiveData<List<SearchImageData?>?>()
    var liveSearchImageResult: LiveData<List<SearchImageData?>?> = _liveSearchImageResult

    // 이미지 상세
    private val _liveSearchImageResultDetail = SingleLiveEvent<SearchImageData?>()
    var liveSearchImageResultDetail: SingleLiveEvent<SearchImageData?> = _liveSearchImageResultDetail

    private val _liveSearchImageResultDetailImg = SingleLiveEvent<String?>()
    var liveSearchImageResultDetailImg: SingleLiveEvent<String?> = _liveSearchImageResultDetailImg



    private var searchImagePage = 1
    private var searchImageLimit = 30
    private var searchImageIsCalling = true
    fun getSearchImage(isRefresh: Boolean) {
        if (isRefresh) {
            searchImagePage = 1
            searchImageIsCalling = false
        }

        if (searchImageIsCalling && !isRefresh) return
        searchImageIsCalling = true
        viewModelScope.launch {
            val map = mapOf(
                "page" to searchImagePage.toString(),
                "limit" to searchImageLimit.toString()
            )
            val searchImage = searchImageRepository.getSearchImageData(map)
            if (searchImage.isSuccessful) {
                val tempList = mutableListOf<SearchImageData?>()
                val searchImageData = searchImage.body()
                if (searchImageData != null) {
                    if (isRefresh) {
                        tempList.addAll(searchImageData)

                    } else {
                        var searchImagerResultList =   _liveSearchImageResult.value?.toMutableList()?: mutableListOf()
                        searchImagerResultList = searchImagerResultList.filterNotNull().toMutableList()
                        tempList.addAll(searchImagerResultList)
                        tempList.addAll(searchImageData)
                    }
                    if (searchImageData.size >= searchImageLimit) {
                        tempList.add(null)
                    }
                }
                _liveSearchImageResult.postValue(tempList)
                searchImagePage++
                searchImageIsCalling = false
            } else {
                searchImageIsCalling = false
            }
        }
    }

    fun setImageChange(type:String){
        val searchImageDetail = _liveSearchImageResultDetail.value
        _liveLoading.value = true

        if(type == "origin"){
            liveSearchImageResultDetailImg.value = searchImageDetail?.download_url
            _liveLoading.value = false
            return
        }

        viewModelScope.launch {
            val method = searchImageDetail?.download_url?.replace(NetworkConstants.BASE_URL,"")
            val map = mapOf(type to "10")
            val searchImage = searchImageRepository.getImageChange(method, map)

            if (searchImage.isSuccessful) {
                _liveLoading.postValue(false)
                liveSearchImageResultDetailImg.postValue(searchImage.raw().request.url.toString())
            } else {
                _liveLoading.postValue(false)
            }
        }
    }

    fun setSearchImageDetail(id: String) {
        val searchImageResult by lazy {
            val temp = _liveSearchImageResult.value?.toMutableList() ?: mutableListOf()
            temp.firstOrNull { it?.id == id }
        }
        _liveSearchImageResultDetail.value = searchImageResult
        _liveSearchImageResultDetailImg.value = searchImageResult?.download_url
    }

}
