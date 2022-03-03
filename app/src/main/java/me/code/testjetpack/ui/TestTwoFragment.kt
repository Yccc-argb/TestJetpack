package me.code.testjetpack.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import cn.jzx91.lib_core.base.BaseFragment
import me.code.testjetpack.databinding.FragmentTestTwoBinding
import me.code.testjetpack.modle.UserViewModel
import java.text.SimpleDateFormat

class TestTwoFragment : BaseFragment<FragmentTestTwoBinding>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)
        userModel.getUsers().observe(this, { users ->

            binding.tvName.text = users.name
            binding.tvAge.text = "${users.age}"
            binding.tvTime.text = "${formatTime(users.time)}"
            Log.i(TAG, "数据变更通知[$TAG]-->${binding.tvTime.text}")
        })
    }


    private fun formatTime(time:Long):String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return  simpleDateFormat.format(time)
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"$TAG->onPause()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG,"$TAG->onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"$TAG->onDestroy()")

    }



    companion object{
        fun getTag():String = "TestTwoFragment"
    }
}