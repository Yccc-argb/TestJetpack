package me.code.testjetpack

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.jzx91.lib_core.base.BaseActivity
import cn.jzx91.lib_core.base.BaseFragment
import me.code.testjetpack.databinding.ActivityMainBinding
import me.code.testjetpack.modle.UserBean
import me.code.testjetpack.modle.UserViewModel
import me.code.testjetpack.ui.TestActivity
import me.code.testjetpack.ui.TestOneFragment
import me.code.testjetpack.ui.TestTwoFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var showF : BaseFragment<ViewBinding>
    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initView() {

        val userModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userModel.setUser(UserBean("xxxxx",14,System.currentTimeMillis()))
            initFragment(TestOneFragment.getTag())

            binding.button1.setOnClickListener {
                val value = userModel.getUsers().value
                value?.time = System.currentTimeMillis()
                userModel.setUser(value!!)
//                userModel?.setUser(UserBean("xxxxx",14,System.currentTimeMillis()))


//
            }

        binding.button2.setOnClickListener {
           replaceF()
//            val intent = Intent(this,TestActivity::class.java)
//            startActivity(intent)
        }

    }


    private fun  showF(){
        val bt = supportFragmentManager.beginTransaction()
        if (showF.tag == TestOneFragment.getTag()){
            var f =
                supportFragmentManager.findFragmentByTag(TestTwoFragment.getTag())
            if (f == null){
                f = TestTwoFragment() as BaseFragment<ViewBinding>
                bt.add(binding.flContainer.id,f)
            }
            bt.hide(showF).show(f).commit()
            showF = f as BaseFragment<ViewBinding>
        }else {
            var f =
                supportFragmentManager.findFragmentByTag(TestOneFragment.getTag())
            if (f == null){
                f = TestOneFragment() as BaseFragment<ViewBinding>
                bt.add(binding.flContainer.id,f)
            }
            bt.hide(showF).show(f).commit()
            showF = f as BaseFragment<ViewBinding>
        }
    }

    private fun replaceF(){
        val bt = supportFragmentManager.beginTransaction()
        if (showF.tag == TestOneFragment.getTag()){
            var f =
                supportFragmentManager.findFragmentByTag(TestTwoFragment.getTag())
            if (f == null){
                f = TestTwoFragment() as BaseFragment<ViewBinding>

            }
            bt.replace(binding.flContainer.id,f,TestTwoFragment.getTag()).commit()
            showF = f as BaseFragment<ViewBinding>
        }else {
            var f =
                supportFragmentManager.findFragmentByTag(TestOneFragment.getTag())
            if (f == null){
                f = TestOneFragment() as BaseFragment<ViewBinding>
            }
            bt.replace(binding.flContainer.id,f,TestOneFragment.getTag()).commit()
            showF = f as BaseFragment<ViewBinding>
        }
    }

    private fun initFragment(tag:String){
        showF = TestOneFragment() as BaseFragment<ViewBinding>
        supportFragmentManager.beginTransaction()
            .add(binding.flContainer.id,showF,tag)
            .commit()


    }


    private fun judgePointZoom(view:View, x:Int,y:Int):Boolean{
        val rect = Rect()
        //获取View的绘制范围，即左、上、右、下边界相对于此View的左顶点的距离（偏移量），即0、0、View的宽、View的高
        view.getDrawingRect(rect)
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        rect.left = location[0]
        rect.top = location[1]
        rect.right = location[0] + rect.right
        rect.bottom = location[1] + rect.bottom
        Toast.makeText(this,"${rect.contains(x,y)}",Toast.LENGTH_LONG).show()
        return rect.contains(x,y)

    }

    override fun onPause() {
        super.onPause()
//        if (this::popupWindow.isInitialized){
//            if (popupWindow.isShowing) popupWindow.dismiss()
//        }
    }

}