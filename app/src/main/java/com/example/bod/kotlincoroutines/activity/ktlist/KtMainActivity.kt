package com.example.bod.kotlincoroutines.activity.ktlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.bod.kotlincoroutines.R
import kotlinx.android.synthetic.main.ktmain_layout.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 *
 * @ClassName: KtMainActivity
 * @Description:
 * @CreateDate: 2019/9/14
 */
class KtMainActivity:AppCompatActivity() {

    private val ktViewModel:KtViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ktmain_layout)
        ktViewModel.userLiveData.observe(this, Observer {banner->
            Timber.d("Banner $banner")
        })

        textView.setOnClickListener {
            ktViewModel.getBanner()
        }

    }

}