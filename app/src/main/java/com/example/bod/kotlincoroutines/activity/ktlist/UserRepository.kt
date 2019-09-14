package com.example.bod.kotlincoroutines.activity.ktlist

import androidx.lifecycle.MutableLiveData
import java.lang.Exception

/**
 *
 * @ClassName: UserRepository
 * @Description:
 * @CreateDate: 2019/9/14
 */
class UserRepository(val iWanService: IWanService) : IUserRepository {

    suspend fun getBanner(): Banner {
        return iWanService.getBannerJson()
    }

    override fun getUser(): User {
        return User("Zb666", 18)
    }
}