package com.example.bod.kotlincoroutines.anim

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ViewAnimator
import com.example.bod.kotlincoroutines.Iaidl
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.activity.BaseActivity
import com.example.bod.kotlincoroutines.activity.ButterKnifeActivity
import com.example.bod.kotlincoroutines.activity.IocActivity
import com.example.bod.kotlincoroutines.activity.MMKVActivity
import com.example.bod.kotlincoroutines.aidlfile.ServerService
import kotlinx.android.synthetic.main.activity_anim.*
import timber.log.Timber

/**
 *
 * @ClassName: AnimActivityt
 * @Description:
 * @CreateDate: 2020/1/28
 */
class AnimActivity : BaseActivity() {

    var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        tv_ioc.setOnClickListener {
            startActivity(Intent(this, IocActivity::class.java))
        }

        tv_remote_service.setOnClickListener {
            bindService(
                    Intent(this, ServerService::class.java),
                    BobServiceCon().also {
                        serviceConnection = it
                    },
                    Context.BIND_AUTO_CREATE
            )
        }

        tv_unbind_service.setOnClickListener {
            serviceConnection?.run {
                unbindService(this)
            }
        }
        tv_context.setOnClickListener {
            startActivity(Intent(this, MMKVActivity::class.java))
            it.animate().alpha(0.5f)
                    .apply {
                        duration = 2500
                    }.start()
        }
    }

    class BobServiceCon : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            Timber.d("BobName","服务断开:$name")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val iBookAidl = Iaidl.Stub.asInterface(service)
            //aidl->remoteService->getData(获取名称)

            Timber.d("BobName", "具体的业务远程对象$iBookAidl 获取到数据${iBookAidl.data}")
        }

    }

}