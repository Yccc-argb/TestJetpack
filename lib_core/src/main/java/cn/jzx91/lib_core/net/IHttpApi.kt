package cn.jzx91.lib_core.net


interface IHttpApi {

    fun getAsync(params:Map<String,Any>,path: String,callBack: IHttpCallBack)

    fun postAsync(params:Map<String,Any>,path: String,callBack: IHttpCallBack)
}