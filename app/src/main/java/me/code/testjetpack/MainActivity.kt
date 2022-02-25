package me.code.testjetpack

import android.annotation.SuppressLint
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
import cn.jzx91.lib_core.base.BaseActivity
import me.code.testjetpack.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var popupWindow : PopupWindow

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initView() {


        binding.button1.setOnClickListener{
            val contentView = layoutInflater.inflate(R.layout.layout_pop, null)
            popupWindow = PopupWindow(
                contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popupWindow.isOutsideTouchable = true
            popupWindow.isFocusable = false
//            popupWindow.showAsDropDown(binding.button1,-binding.button1.width/2,0)
            popupWindow.showAtLocation(it,Gravity.CENTER_HORIZONTAL,0,0)
            val tvTest = contentView.findViewById<TextView>(R.id.tvTest)
            tvTest.setOnClickListener {
                popupWindow.dismiss()
            }

//            popupWindow.setTouchInterceptor { v, event ->
//                if (event.action == MotionEvent.ACTION_OUTSIDE && !popupWindow.isFocusable){
//                    val judgePointZoom =
//                        judgePointZoom(binding.button1, event.rawX.toInt(), event.rawY.toInt())
//                    if (judgePointZoom) popupWindow.dismiss()
//                    return@setTouchInterceptor judgePointZoom
//                }else {
//                    return@setTouchInterceptor  false
//                }
//            }

            contentView.setOnKeyListener(object: View.OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    Log.i("KEYCODE_BACK","-------$keyCode-------")
                    when(keyCode){
                        KeyEvent.KEYCODE_BACK->{

                            Toast.makeText(this@MainActivity,"KEYCODE_BACK~",Toast.LENGTH_LONG).show()
                            popupWindow.dismiss()
                            return true

                        }

                    }
                    return true
                }
            })

        }
        binding.button2.setOnClickListener {
            Toast.makeText(this,"button被点击了~",Toast.LENGTH_LONG).show()
        }



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