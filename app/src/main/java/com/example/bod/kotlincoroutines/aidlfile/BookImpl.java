package com.example.bod.kotlincoroutines.aidlfile;

import android.os.RemoteException;

import com.example.bod.kotlincoroutines.Iaidl;

/**
 * @ClassName: BookImpl
 * @Description:
 * @CreateDate: 2020/2/22
 */
public class BookImpl extends Iaidl.Stub {
    @Override
    public String getData() throws RemoteException {
        return "BobImpl";
    }
}
