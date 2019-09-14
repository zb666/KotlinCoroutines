package com.example.bod.kotlincoroutines.activity.ktlist

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

/**
 *
 * @ClassName: moudlekt
 * @Description:
 * @CreateDate: 2019/9/14
 */
val globalMoudle = module {

    factory<IWanService> {
       ReterofitService.retrofit.create() //scopename T.Class
    }

    single {
        UserRepository(get()) //Single of HelloRepository
    }

    viewModel {
        KtViewModel(get())
    }
}
