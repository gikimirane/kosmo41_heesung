package com.study.andriod.service2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "lecture";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"지정한 시간입니다.",Toast.LENGTH_LONG).show();
    }
}
