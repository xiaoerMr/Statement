package com.app.login

import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lib_comment.base.BaseActivity
import com.app.login.adapter.ListTextAdapter
import com.app.login.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val adapterList by lazy { ListTextAdapter() }
    override fun initView() {

        mViewBinding.vRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@LoginActivity)
            adapter = adapterList
        }
    }

    override fun initData() {
        val list = mutableListOf<String>()
        for (item in 0 until 10){
            list.add("---> 测试 $item")
        }
        adapterList.submitList(list)
    }

    override fun initListener() {
        mViewBinding.vAddList.setOnClickListener {
            adapterList.swap(1,2)
        }
    }

}