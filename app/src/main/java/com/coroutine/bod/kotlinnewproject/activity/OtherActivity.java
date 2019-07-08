package com.coroutine.bod.kotlinnewproject.activity;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.coroutine.bod.kotlinnewproject.R;

import java.util.Timer;

import timber.log.Timber;

public class OtherActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Timber.d("hhhdhd");
    }
}
