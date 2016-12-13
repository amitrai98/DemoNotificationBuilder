package com.android.amitrai.demonotificationbuilder;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by devesh on 2/12/16.
 */

public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {


        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(intent.getIntExtra("id", -1));
        Handler mhandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(intent.getAction().equals("com.logan.yesclicked")){
                    Log.e("yes","clicked");
                    if (MainActivity.txt_status != null)
                    MainActivity.txt_status.setText("Your Action Was Yes");
                }else if(intent.getAction().equals("com.logan.noclicked")){
                    Log.e("reply","clicked");
                    if (MainActivity.txt_status != null)
                    MainActivity.txt_status.setText("Your Action Was No");
                }
            }
        };

        mhandler.sendEmptyMessage(0);
    }
}
