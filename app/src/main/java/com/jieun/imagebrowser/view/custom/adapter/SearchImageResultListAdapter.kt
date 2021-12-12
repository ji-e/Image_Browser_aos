package com.jieun.imagebrowser.view.custom.adapter

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.jieun.imagebrowser.R
import com.jieun.imagebrowser.databinding.ItemSearchImageLoadingBinding
import com.jieun.imagebrowser.databinding.ItemSearchImageResultBinding
import com.jieun.imagebrowser.model.data.SearchImageData
import com.jieun.imagebrowser.utils.SearchImageResultViewType

/**
 * date 2021-12-12
 * create by jieun
 *
 * 이미지 검색 ListAdapter
 */
class SearchImageResultListAdapter :
    BaseListAdapter.Adapter<SearchImageData?, ItemSearchImageResultBinding>(
        layoutResId = R.layout.item_search_image_result,
        bindingVariableId = BR.searchImageItem,
        diff = object : DiffUtil.ItemCallback<SearchImageData?>() {
            override fun areItemsTheSame(
                oldItem: SearchImageData,
                newItem: SearchImageData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SearchImageData,
                newItem: SearchImageData
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {
    override fun getItemViewType(position: Int) = when (list[position]) {
        is SearchImageData -> SearchImageResultViewType.CONTENT
        null -> SearchImageResultViewType.LOADING
        else -> throw IllegalArgumentException("Invalid view type")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseListAdapter.ViewHolder<*> {
        return when (viewType) {
            SearchImageResultViewType.LOADING -> {
                BaseListAdapter.ViewHolder<ItemSearchImageLoadingBinding>(
                    R.layout.item_search_image_loading,
                    parent,
                    BR.searchImageLoadingItem
                )
            }
            else -> super.onCreateViewHolder(parent, viewType)
        }
    }


    /** 아이템 클릭 리스너 -------------------*/
    interface OnItemClickListener {
        fun onItemClick(id: String?)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    /** -------------------아이템 클릭 리스너 */


    override fun onBindViewHolder(
        holder: BaseListAdapter.ViewHolder<*>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(getItem(holder.bindingAdapterPosition)?.id)
        }
    }
}