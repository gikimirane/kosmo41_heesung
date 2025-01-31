package com.study.andriod.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class MySMSReceiver extends BroadcastReceiver {
    private static final String TAG = "lecture";

    @Override
    public void onReceive(Context context, Intent intent){
        Log.d(TAG,"onReceive() called...");

        //---------------------------------------------
        Bundle bundle = intent.getExtras();
        Object[] objs = (Object[]) bundle.get("pdus");

        SmsMessage[] managers = new SmsMessage[objs.length];

        for(int i = 0; i<objs.length; i++){
            managers[i] = SmsMessage.createFromPdu((byte[])objs[i]);
        }
        //--------------------------------------------------------
        String sender = managers[0].getOriginatingAddress();
        String contents = managers[0].getMessageBody();

        Log.d(TAG,"발신번호: "+sender);
        Log.d(TAG,"내용: "+contents);

        //브로드캐스트 메시지 전달 중지
        abortBroadcast();

        loadDisplayActivity(context,sender,contents);
    }

    private void loadDisplayActivity(Context context, String sender, String contents){
        Intent intent = new Intent(context,SMSDisplay2Activity.class);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        context.startActivity(intent);
    }
}
