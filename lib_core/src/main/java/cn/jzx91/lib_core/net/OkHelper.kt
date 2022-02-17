package cn.jzx91.lib_core.net

import com.google.gson.Gson
import okhttp3.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis


class OkHelper private constructor(): IHttpApi{


    private val token =
        "eyJpc3MiOiJKWlgiLCJhbGciOiJFUzI1NiJ9.eyJhcHBVc2VySWQiOiIwMDBiZmQ4NS1lNDFlLTRkNGEtYjUxOC02MDA2YTFhYmE5ZTgiLCJ1c2VySWQiOiIwMDBiZmQ4NS1lNDFlLTRkNGEtYjUxOC02MDA2YTFhYmE5ZTgiLCJkZXZpY2VJZCI6IjMwMDAyMDIxMTAwMDQ2NCIsImNyZWF0ZVRpbWUiOiIxNjQ0OTk5OTYyMTY4IiwiYXBwSWQiOiJjbi5qeng5MS5jb3JlLnNlcnZlIn0.2grglVs-fT5oFa2ltC5V8E8a534G46PCjDkkiHK4Fy-OfyiTrB-r2Zf4R9TAnr5SJoggorm-D-nHtEBv8wpl4A"

    private val tokenP =
        "eyJpc3MiOiJKWlgiLCJhbGciOiJFUzI1NiJ9.eyJhcHBVc2VySWQiOiI4N2I0NTM1NS1hYWM2LTRlOWItYTMxNi00NjFiZDNkMTM1NWQiLCJ1c2VySWQiOiI4MTY3MWZmYi0wNjAzLTRmODgtYTY2NS1jNTc4ZTliMWMxNmUiLCJkZXZpY2VJZCI6IjMwMDAyMDIxMTAwMDQ2NCIsImNyZWF0ZVRpbWUiOiIxNjQ1MDY2OTQ4NDE0IiwiYXBwSWQiOiJjbi5qeng5MS5haS5ndWlkZSJ9.nYHQ6PljPVjx2LHbSTf6QWVfrEg5Nb7knT18eFtahUE2Mt4_QeAaX2POPXXZBskumWUCLjDt49bYv9HJR-N5QQ"

//    private val base_url = "https://test-device.91jzx.cn"
    private val base_url = "https://test-app.91jzx.cn"


    private val logger = HttpLoggingInterceptor(IHttpLogger()).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

   private val client = OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.SECONDS)//设置超时时间
        .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
        .writeTimeout(5, TimeUnit.SECONDS)//设置写入超时时间
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(logger)
        .build()



    companion object{


        private var okHelper: OkHelper ? =null

        @Synchronized
        fun instance():OkHelper{

            if (okHelper == null){
                okHelper = OkHelper().also { okHelper = it }
            }
            return okHelper!!
        }
    }





    override fun getAsync(params: Map<String, Any>, path: String, callBack: IHttpCallBack) {


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
        val request = Request.Builder().get().url(format).build()
        val newCall = client.newCall(request)
        newCall.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailure(e)

            }

            override fun onResponse(call: Call, response: Response) {
                callBack.onSuccess(call)
            }
        })

    }

    override fun postAsync(params: Map<String, Any>, path: String, callBack: IHttpCallBack) {
        val format =
            String.format("%s/%s", base_url, "ares/rest/user/modify")
        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=UTF-8"),
            Gson().toJson(params)
        )
        val build = Request.Builder().post(requestBody).url(format).build()
        client.newCall(build).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailure(call)
            }

            override fun onResponse(call: Call, response: Response) {
                callBack.onSuccess(response)
            }
        })
    }


  inner  class HeaderInterceptor: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val addHeader = request.newBuilder()
                .addHeader("ACCESS-TOKEN", token)
                .addHeader("DEVICE-ID", "300020211000464")
//                .addHeader("JZX-AppID", "cn.jzx91.core.serve")
                .addHeader("JZX-AppID", "cn.jzx91.ai.guide")
                .build()

            return chain.proceed(addHeader)
        }
    }


}