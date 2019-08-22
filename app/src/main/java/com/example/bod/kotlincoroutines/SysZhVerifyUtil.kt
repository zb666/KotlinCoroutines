package com.example.bod.kotlincoroutines

import java.util.*

/**
 *
 * @ClassName: SysZhVerifyUtil
 * @Description:系统简体|中文校验
 * @CreateDate: 2019/8/12
 */
object SysZhVerifyUtil {

    fun isZh() = "zh".equals(Locale.getDefault().language)

}