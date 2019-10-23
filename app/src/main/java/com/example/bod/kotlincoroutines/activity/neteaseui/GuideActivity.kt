package com.example.bod.kotlincoroutines.activity.neteaseui

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.*
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bod.kotlincoroutines.BuildConfig
import com.example.bod.kotlincoroutines.GlideImageLoader
import com.example.bod.kotlincoroutines.MyItemDecor
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.adapter.ItemAdapter
import com.example.bod.kotlincoroutines.create.NewObserverOnSubscribe
import com.example.bod.kotlincoroutines.create.Observable
import com.example.bod.kotlincoroutines.create.Observer
import com.example.bod.kotlincoroutines.sound.TestSoundPool
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.layout_train_plan.*
import timber.log.Timber
import kotlin.math.min

class GuideActivity : BaseUiActivity(), View.OnClickListener {

    private val mPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        strokeWidth = 10f
    }

    private val testSoundPool by lazy { TestSoundPool() }

    private val soundList = listOf(TestSoundPool.FOCUSPLANET_NEW_APPEAR, TestSoundPool.FOCUSPLANET_COMMON_TREE)

    private fun generateCanvasBitmap(): Bitmap {
        val originBitmap = BitmapFactory.decodeResource(resources, R.drawable.asia)
                .copy(Bitmap.Config.RGB_565, true)
        //或者 val bitmap = Bitmap.createBitmap(originBitmap)
        val canvas = Canvas(originBitmap)

        val circleRadius = min(originBitmap.width, originBitmap.height) * 0.5f

        val circlePath = Path().apply {
            addCircle(originBitmap.width * 0.5f, originBitmap.height * 0.5f, circleRadius, Path.Direction.CW)
        }
        canvas.drawCircle(originBitmap.width * 0.5f, originBitmap.height * 0.5f, circleRadius, mPaint)
        canvas.clipPath(circlePath)

//        mPaint.reset()
//        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
//
//        canvas.drawBitmap(originBitmap, 0f, 0f, mPaint)

        return originBitmap
    }

    override fun initView() {
        //new A().print()
        //new AA(A()) print()-> a.print()
        Observable.create(object : NewObserverOnSubscribe<String> {
            override fun subscribe(observer: Observer<String>) {
                observer.onNext("123456")  //就是这种方式骚一点而已
            }
        }).transObs(object : Observer<String> {
            override fun onNext(t: String) {
                Timber.d("OnNext:$t")
            }

            override fun onError() {

            }

            override fun onCompleted() {

            }
        })

        val spBuilder = SpannableStringBuilder("那你可真是棒棒哒").apply {
            setSpan(ForegroundColorSpan(Color.BLUE), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.BOLD), 1, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setSpan(AbsoluteSizeSpan(50), 2, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setSpan(ImageSpan(this@GuideActivity, R.drawable.ic_big_arrow, DynamicDrawableSpan.ALIGN_BASELINE), 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }

        spBuilder.clear()
        spBuilder.append("你可真是66哒").apply {
            setSpan(AbsoluteSizeSpan(50), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }.run {
            tvSpan.text = spBuilder
        }

        ivBg.setImageBitmap(generateCanvasBitmap())

        ivBg.setOnClickListener {
            soundList.random().let {
                testSoundPool.play(it)
            }
        }
        guideList.forEach {
            it.setOnClickListener(this)
        }

        for (index in 0 until 4) {
            Timber.d("MyIndex: $index")
        }
//        start<ReStartActivity>()
        ll_jump.setOnClickListener {
            start<ReStartActivity>()
        }

        rv_divider.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter()

        rv_divider.adapter = itemAdapter
//        rv_divider.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL).apply {
//            ContextCompat.getDrawable(this@GuideActivity,R.drawable.ic_focus_logo)?.let { setDrawable(it) }
//        })
        rv_divider.addItemDecoration(MyItemDecor(this))
        for (index in 0..30) {
            itemAdapter.addData(index.toString())
        }

//        val viewGroup = window.decorView as ViewGroup
//        viewGroup.addView(TextView(this).apply {
//            textSize = 20f
//            setTextColor(Color.RED)
//            text = "Hello I'am Bob"
//        }, RelativeLayout.LayoutParams(dp2px(Paint()
//                .apply {
//                    textSize = 20f
//                }.measureText("Hello I'am Bob")), dp2px(50f)).apply {
//            addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txtView)
//            setMargins(100, 200, 0, 0)
//        }.apply {
//            topMargin = dp2px(200f)
//        })

        if (BuildConfig.isRelease) {

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvPaint -> {
                start<ReStartActivity>()
            }
            else -> {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        ValueAnimator.ofInt(0, 18).apply {
            interpolator = LinearInterpolator()
            duration = 2000
            start()
            addUpdateListener {
                Timber.d("ProgressDay: ${it.animatedValue}")
                trainCircle.setProgress(it.animatedValue as Int)
            }
        }

        ValueAnimator.ofInt(0, 18).apply {
            interpolator = LinearInterpolator()
            duration = 2000
            start()
            addUpdateListener {
                focusCircle.progress = (it.animatedValue as Int) / 21.toFloat()
                tvFinishedDay.text = (it.animatedValue as Int).toString()
            }
        }

        cornerBanner.apply {
            setImages(listOf(R.drawable.asia, R.drawable.asia))
            setImageLoader(GlideImageLoader())
            start()
        }

    }

    private val guideList by lazy {
        listOf(tvPaint)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    private inline fun <reified T> start() {
        startActivity(Intent(this@GuideActivity, T::class.java))
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("OnResStart invoked")
    }

}
