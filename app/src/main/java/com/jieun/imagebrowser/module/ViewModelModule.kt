package com.jieun.imagebrowser.module


import com.jieun.imagebrowser.viewmodel.SearchImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * date 2021-12-12
 * create by jieun
 */
val viewModelModule = module {
    viewModel { SearchImageViewModel(get()) }
}