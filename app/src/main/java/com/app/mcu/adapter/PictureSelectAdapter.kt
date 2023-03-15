package com.app.mcu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mcu.databinding.ItemAdapterPictureSelectBinding
import com.app.mcu.ext.d
import com.app.mcu.util.ImageLoaderUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.luck.picture.lib.entity.LocalMedia

class PictureSelectAdapter: BaseQuickAdapter<LocalMedia,PictureSelectAdapter.BindViewHolder>() {

    class BindViewHolder(
        parent:ViewGroup,
        val binding: ItemAdapterPictureSelectBinding = ItemAdapterPictureSelectBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): BindViewHolder  = BindViewHolder(parent)

    override fun onBindViewHolder(holder: BindViewHolder, position: Int, item: LocalMedia?) {
        item?.availablePath?.let {
            d("----",it)
            ImageLoaderUtils().load(holder.binding.itemPicture, it) }
    }

}