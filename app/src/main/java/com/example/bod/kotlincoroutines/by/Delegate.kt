package com.example.bod.kotlincoroutines.by

import kotlin.reflect.KProperty

/**
 *
 * @ClassName: Delegate
 * @Description:
 * @CreateDate: 2019/7/31
 */
 class Delegate {

//    operator fun getValue()

    operator fun getValue(thisRef:Any?,property:KProperty<*>):String{
        return "${thisRef?.javaClass} operator getValue ${property.name}"
    }

    operator fun setValue(thisRef: Any?,property: KProperty<*>,value:String){
        print("value has been assigned to ${property.name} in ${thisRef?.javaClass}")
    }

}