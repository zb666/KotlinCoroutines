package com.example.bod.kotlincoroutines.activity.ktlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 *
 * @ClassName: KtViewModel
 * @Description:
 * @CreateDate: 2019/9/14
 */
class KtViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userLiveData = MutableLiveData<Banner>()

    //扩展属性 在clear()方法里面会被自动取消
    fun getBanner(){
        //1.clear方法遍历所有BagOfTags的value()
        //2.实现Closeable接口的类
        //3.
        /**
         * synchronized (mBagOfTags) {
        for (Object value : mBagOfTags.values()) {
        // see comment for the similar call in setTagIfAbsent
        closeWithRuntimeException(value);
        }
        }
         */
        viewModelScope.launch {
            Timber.d("userRepository $userRepository")
           val banner = userRepository.getBanner()
            userLiveData.value = banner
        }
    }



}