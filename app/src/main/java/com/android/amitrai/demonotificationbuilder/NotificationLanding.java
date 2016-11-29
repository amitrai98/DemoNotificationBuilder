package com.android.amitrai.demonotificationbuilder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class NotificationLanding extends AppCompatActivity {

    public final static String NOTIFICATION_ID = "notification_id" ;

    public final static String NOTIFICATION_CANCEL = "101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    /**
     * finish activity.
     */
    private void cancelNotification(){
        finish();
    }

}
