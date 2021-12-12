package com.jieun.imagebrowser.view.searchImage

import android.content.Context
import android.util.Log
import android.view.View
import com.jieun.imagebrowser.BaseFragment
import com.jieun.imagebrowser.R
import com.jieun.imagebrowser.databinding.FragmentSearchImageResultDetailBinding
import com.jieun.imagebrowser.view.MainActivity
import com.jieun.imagebrowser.view.custom.dialog.LoadingDialog
import com.jieun.imagebrowser.viewmodel.SearchImageViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색 상세
 */
class SearchImageResultDetailFragment : BaseFragment<FragmentSearchImageResultDetailBinding>() {
    override fun getLayoutId() = R.layout.fragment_search_image_result_detail

    private val viewModel: SearchImageViewModel by sharedViewModel()

    private val mainActivity: MainActivity? by lazy { activity as? MainActivity }
    private val mContext: Context by lazy { this.requireContext() }

    private val loadingDialog:LoadingDialog by lazy { LoadingDialog(mContext) }

    override fun initStartView() {
        binding.run {
            searchImageResultDetailVM = viewModel
            rootView = this@SearchImageResultDetailFragment
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        viewModel.liveLoading.observe(viewLifecycleOwner, {
            Log.e("FDF", it.toString())
            if (it) loadingDialog.show()
            else loadingDialog.dismiss()
        })

    }

    fun onClickSearchImageResultDetailFragment(view: View?) {
        when (view) {
            binding.searchImageResultDetailRbOrigin -> viewModel.setImageChange("origin")
            binding.searchImageResultDetailRbBlur -> viewModel.setImageChange("blur")
            binding.searchImageResultDetailRbGrayscale -> viewModel.setImageChange("grayscale")
            binding.searchImageResultDetailImgBack -> mainActivity?.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog.dismiss()
    }


}