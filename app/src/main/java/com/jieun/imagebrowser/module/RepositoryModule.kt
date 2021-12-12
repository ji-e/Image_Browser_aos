package com.jieun.imagebrowser.module


import com.jieun.imagebrowser.model.repository.SearchImageRepository
import com.jieun.imagebrowser.model.repository.SearchImageRepositoryImpl
import org.koin.dsl.module

/**
 * date 2021-12-12
 * create by jieun
 */
val repositoryModule = module {
    single<SearchImageRepository> { SearchImageRepositoryImpl(get()) }}

