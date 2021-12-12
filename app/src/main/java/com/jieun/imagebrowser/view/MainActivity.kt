package com.jieun.imagebrowser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jieun.imagebrowser.BaseActivity
import com.jieun.imagebrowser.R
import com.jieun.imagebrowser.databinding.ActivityMainBinding
import com.jieun.imagebrowser.view.searchImage.SearchImageResultFragment

/**
 * date 2021-12-12
 * create by jieun
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(R.id.frame_lt, SearchImageResultFragment(), false)
    }
}