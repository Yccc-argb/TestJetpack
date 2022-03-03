package me.code.testjetpack.ui

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import cn.jzx91.lib_core.base.BaseActivity
import me.code.testjetpack.databinding.ActivityTestBinding
import me.code.testjetpack.modle.UserViewModel
import java.text.SimpleDateFormat

class TestActivity : BaseActivity<ActivityTestBinding>() {
    override fun initView() {
        val userModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userModel.getUsers().observe(this,{users ->
            binding.tvName.text = users.name
            binding.tvAge.text = "${users.age}"
            binding.tvTime.text = "${formatTime(users.time)}"
            Log.i(TAG, "数据变更通知[$TAG]-->${binding.tvTime.text}")
        })

        binding.button1.setOnClickListener {
            val value = userModel.getUsers().value
            value?.time = System.currentTimeMillis()
            userModel.setUser(value!!)
        }
    }

    private fun formatTime(time:Long):String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return  simpleDateFormat.format(time)
    }
}