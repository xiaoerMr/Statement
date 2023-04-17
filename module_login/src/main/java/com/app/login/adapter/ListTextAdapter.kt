package com.app.login.adapter

import android.content.Context
import android.view.ViewGroup
import com.app.login.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder

class ListTextAdapter:BaseQuickAdapter<String,QuickViewHolder> (){

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: String?) {
        holder.setText(R.id.vItemTitle,item)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_list_text,parent)
    }
}