package me.code.testjetpack.modle

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel(){



    val  users  = MutableLiveData<UserBean>()


    fun  getUsers():LiveData<UserBean>{
        return users
    }

    fun  setUser(userBean: UserBean){
        users.value = userBean
    }





    private  fun loadUsers(){

        Handler().post {
            val data = mutableListOf<UserBean>()
            val currentTimeMillis = System.currentTimeMillis()
            repeat(1){
                data.add(UserBean("小明",50,currentTimeMillis))
            }
//            users.setValue(data)
        }


    }

    override fun onCleared() {
        super.onCleared()
    }
}