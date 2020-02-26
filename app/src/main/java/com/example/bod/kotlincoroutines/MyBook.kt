package com.example.bod.kotlincoroutines

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @ClassName: MyBook
 * @Description:
 * @CreateDate: 2020/2/22
 */
class MyBook() :Parcelable {
     var bookName:String?=null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<MyBook> {
        override fun createFromParcel(parcel: Parcel): MyBook {
            return MyBook(parcel)
        }

        override fun newArray(size: Int): Array<MyBook?> {
            return arrayOfNulls(size)
        }
    }
}