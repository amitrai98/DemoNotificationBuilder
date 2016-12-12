package com.android.amitrai.demonotificationbuilder;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by devesh on 2/12/16.
 */

public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(intent.getIntExtra("id", -1));
        if(intent.getAction().equals("com.logan.yesclicked")){
            Log.e("yes","clicked");
        }else if(intent.getAction().equals("com.logan.noclicked")){
            Log.e("reply","clicked");
        }
    }
}
