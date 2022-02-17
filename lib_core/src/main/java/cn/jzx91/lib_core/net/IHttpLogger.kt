package cn.jzx91.lib_core.net

import okhttp3.logging.HttpLoggingInterceptor

class IHttpLogger : HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        println(message)
    }
}