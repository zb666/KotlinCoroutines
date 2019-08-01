package com.example.bod.kotlincoroutines.paging

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.jetpack.DateBean
import java.util.*

/**
 *
 * @ClassName: ConvertAdapter
 * @Description:
 * @CreateDate: 2019/8/1
 */
class ConvertAdapter : BaseQuickAdapter<DateBean, BaseViewHolder>(R.layout.item_convert) {
    override fun convert(helper: BaseViewHolder?, item: DateBean?) {
        helper?.setText(R.id.tvCenter, item.toString())
    }

     lateinit var dataCall: (Int) -> DateBean

    fun setTestData(dataCall: (Int) -> DateBean) {
        this.dataCall = dataCall
        addData(dataCall.invoke(hashCode()))
    }
}