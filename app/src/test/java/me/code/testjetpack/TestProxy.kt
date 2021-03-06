package me.code.testjetpack

import cn.jzx91.lib_core.net.IHttpCallBack
import cn.jzx91.lib_core.net.OkHelper
import me.code.testjetpack.proxy.Cat
import me.code.testjetpack.proxy.Dog
import me.code.testjetpack.proxy.ProxyCat
import okhttp3.*
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class TestProxy {


    private val token =
        "eyJpc3MiOiJKWlgiLCJhbGciOiJFUzI1NiJ9.eyJhcHBVc2VySWQiOiIwMDBiZmQ4NS1lNDFlLTRkNGEtYjUxOC02MDA2YTFhYmE5ZTgiLCJ1c2VySWQiOiIwMDBiZmQ4NS1lNDFlLTRkNGEtYjUxOC02MDA2YTFhYmE5ZTgiLCJkZXZpY2VJZCI6IjMwMDAyMDIxMTAwMDQ2NCIsImNyZWF0ZVRpbWUiOiIxNjQ0OTk5OTYyMTY4IiwiYXBwSWQiOiJjbi5qeng5MS5jb3JlLnNlcnZlIn0.2grglVs-fT5oFa2ltC5V8E8a534G46PCjDkkiHK4Fy-OfyiTrB-r2Zf4R9TAnr5SJoggorm-D-nHtEBv8wpl4A"
    private val tokenP =
        "eyJpc3MiOiJKWlgiLCJhbGciOiJFUzI1NiJ9.eyJhcHBVc2VySWQiOiI4N2I0NTM1NS1hYWM2LTRlOWItYTMxNi00NjFiZDNkMTM1NWQiLCJ1c2VySWQiOiI4MTY3MWZmYi0wNjAzLTRmODgtYTY2NS1jNTc4ZTliMWMxNmUiLCJkZXZpY2VJZCI6IjMwMDAyMDIxMTAwMDQ2NCIsImNyZWF0ZVRpbWUiOiIxNjQ1MDY2OTQ4NDE0IiwiYXBwSWQiOiJjbi5qeng5MS5haS5ndWlkZSJ9.nYHQ6PljPVjx2LHbSTf6QWVfrEg5Nb7knT18eFtahUE2Mt4_QeAaX2POPXXZBskumWUCLjDt49bYv9HJR-N5QQ"
    private val base_url = "https://test-device.91jzx.cn"

    private val client = OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.SECONDS)//设置超时时间
        .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
        .writeTimeout(5, TimeUnit.SECONDS)//设置写入超时时间
        .build()

    @Test
    fun testStaticProxy() {
        val cat = Cat()
        val proxyCat = ProxyCat(cat)

        proxyCat.animalVoice()
        proxyCat.animalEat()


        val proxyDog = ProxyCat(Dog())
        proxyDog.animalName()
    }


    @Test
    fun testDynamicProxy() {


    }

    @Test
    fun testOkhttpRequest() {
        val okHelper = OkHelper.instance()
//        okHelper.getAsync(mapOf("deviceId" to "300020211000464"),"",object : IHttpCallBack{
//            override fun onSuccess(data: Any) {
//                val call = data as Call
////                println("request onSuccess->${call.request().url()}")
//            }
//
//            override fun onFailure(error: Any) {
//                println("request error->")
//            }
//        })

        okHelper.postAsync(mapOf("sex" to 1),"",object : IHttpCallBack{
            override fun onSuccess(data: Any) {
                println("post->success")
            }

            override fun onFailure(error: Any) {
                println("post->failure")
            }
        })

        //防止异步请求程序执行完了无返回
        Thread.sleep(1000)

    }







    private fun testGetAsync(params: Map<String, String>) {

        val builder = Request.Builder()
            .addHeader("ACCESS-TOKEN", token)
            .addHeader("DEVICE-ID", "300020211000464")
            .addHeader("JZX-AppID", "cn.jzx91.core.serve")
            .get()

        var p = 0
        val sb = StringBuilder()
        for (param in params) {
            if (p > 0) {
                sb.append("&")
            }
            sb.append(String.format("%s=%s", param.key, param.value))
            p++
        }

        val format =
            String.format("%s/%s?%s", base_url, "kunlunmirror/rest/device/app/list", sb.toString())
        val request = builder.url(format).build()
        val newCall = client.newCall(request)
        val measureTimeMillis = measureTimeMillis {
            val execute = newCall.execute()
            println("request success->${execute.request().url()}")
            println("response->${execute.body()?.string()}")
        }
        println("耗时:$measureTimeMillis")

//        newCall.enqueue(object : Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                println("request error->${call.request().url()}")
//                println("error msg->${e.message}")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                println("request success->${call.request().url()}")
//                println("response->${response.body()?.string()}")
//            }
//        })
        //防止异步请求程序执行完了无返回
//        Thread.sleep(5000)


    }


    private fun testPost(){
        RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),"")
    }

}