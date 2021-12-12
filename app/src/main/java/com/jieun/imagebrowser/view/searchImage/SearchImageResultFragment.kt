package com.jieun.imagebrowser.view.searchImage

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.jieun.imagebrowser.BaseFragment
import com.jieun.imagebrowser.R
import com.jieun.imagebrowser.databinding.FragmentSearchImageResultBinding
import com.jieun.imagebrowser.utils.SearchImageResultViewType
import com.jieun.imagebrowser.view.MainActivity
import com.jieun.imagebrowser.view.custom.adapter.SearchImageResultListAdapter
import com.jieun.imagebrowser.viewmodel.SearchImageViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색 목록
 */
class SearchImageResultFragment : BaseFragment<FragmentSearchImageResultBinding>() {
    override fun getLayoutId() = R.layout.fragment_search_image_result

    private val viewModel: SearchImageViewModel by sharedViewModel()

    private val mainActivity: MainActivity? by lazy { activity as? MainActivity }
    private val mContext: Context by lazy { this.requireContext() }

    override fun initStartView() {
        binding.run {
            searchImageResultVM = viewModel
        }


        binding.searchImageResultRv.apply {
            val searchImageResultListAdapter = SearchImageResultListAdapter().apply {
                setOnItemClickListener(object : SearchImageResultListAdapter.OnItemClickListener {
                    override fun onItemClick(id: String?) {
                        id?.let{
                            viewModel.setSearchImageDetail(it)
                        }
                    }
                })
            }
            adapter = searchImageResultListAdapter

            setOnScrollChangeListener { _, _, _, _, _ ->
                val lastVisibleItemPosition = (this.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = searchImageResultListAdapter.itemCount - 1

                // 최하단 확인
                if (lastVisibleItemPosition == itemTotalCount) {
                    viewModel.getSearchImage(false)
                }
            }

            // 하단 로딩 span
            val manager = GridLayoutManager(mContext, 2)
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (searchImageResultListAdapter.getItemViewType(position) == SearchImageResultViewType.LOADING) 2 else 1
                }
            }
            layoutManager = manager
        }

        binding.searchImageResultSwipeRefresh.setOnRefreshListener {
            viewModel.getSearchImage(true)
        }
    }

    override fun initDataBinding() {
        viewModel.getSearchImage(true)
    }

    override fun initAfterBinding() {
        viewModel.liveSearchImageResultDetail.observe(viewLifecycleOwner, {
            mainActivity?.replaceFragment(R.id.frame_lt, SearchImageResultDetailFragment(), true)
        })

        viewModel.liveSearchImageResult.observe(viewLifecycleOwner, {
            binding.searchImageResultSwipeRefresh.isRefreshing = false
        })
    }

}