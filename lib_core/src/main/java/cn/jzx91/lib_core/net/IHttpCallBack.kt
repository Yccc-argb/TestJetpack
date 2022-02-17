package cn.jzx91.lib_core.net

interface IHttpCallBack {
    fun onSuccess(data : Any)
    fun onFailure(error: Any)
}