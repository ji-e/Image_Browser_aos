package com.jieun.imagebrowser.module



import com.jieun.imagebrowser.model.datasource.SearchImageDatasource
import com.jieun.imagebrowser.model.datasource.SearchImageDatasourceImpl
import org.koin.dsl.module

/**
 * date 2021-12-12
 * create by jieun
 */
val dataSourceModule = module {
    single<SearchImageDatasource> { SearchImageDatasourceImpl(get()) }
}