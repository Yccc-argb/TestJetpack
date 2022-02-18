package me.code.testjetpack

import me.code.testjetpack.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initView() {

        binding.tvName.text = getString(R.string.app_name)
    }

}