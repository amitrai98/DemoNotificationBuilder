package com.android.amitrai.demonotificationbuilder;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.RemoteInput;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int NOTIFICATION_ID = 1;
    // Key for the string that's delivered in the action's intent.
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    private NotificationManager mNotificationManager;
    private RemoteInput remoteInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mNotificationManager = (NotificationManager) getActivity().getSystemService(Context
                .NOTIFICATION_SERVICE);
        remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("reply")
                .build();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void showNotification(View view){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mNotificationManager.notify(NOTIFICATION_ID, createNotification(true));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    //@VisibleForTesting
    Notification createNotification(boolean makeHeadsUpNotification) {
        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(getActivity(), NotificationLanding.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(getActivity(), 0,
                push, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(getActivity());
        builder.setSmallIcon(R.drawable.ic_menu_gallery)
                .setPriority(Notification.PRIORITY_HIGH)
                .setPriority(Notification.PRIORITY_MAX)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .setContentText("User is calling");

        Intent notificationIntent = new Intent(getActivity(), NotificationLanding.class);

        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);

        builder.setLights(Color.BLUE, 500, 500);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        builder.setVibrate(pattern);
//        builder.setStyle(new NotificationCompat.InboxStyle());
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if(alarmSound == null){
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if(alarmSound == null){
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }

//        // Add as notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setSound(alarmSound);
//        manager.notify(1, builder.build());

        //Yes intent
        Intent yesReceive = new Intent();
        yesReceive.putExtra("id",NOTIFICATION_ID);
        yesReceive.setAction("com.logan.yesclicked");

        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(getActivity(), 123, yesReceive,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.addAction(R.drawable.accept, "Yes", pendingIntentYes);
        //No intent
        Intent noReceive = new Intent();
        noReceive.putExtra("id",NOTIFICATION_ID);
        noReceive.setAction("com.logan.noclicked");
        noReceive.putExtra(NotificationLanding.NOTIFICATION_ID, NotificationLanding.NOTIFICATION_CANCEL);
        PendingIntent pendingIntentNo = PendingIntent.getBroadcast(getActivity(), 1234, noReceive, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.addAction(R.drawable.cancel, "no", pendingIntentNo);

        Intent replyIntent = new Intent();
        yesReceive.putExtra("id",NOTIFICATION_ID);
        yesReceive.setAction("com.logan.yesclicked");

       /* PendingIntent replyPendingIntent = PendingIntent.getBroadcast(getActivity(), 123, replyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the reply action and add the remote input.
        Notification.Action action =
                new Notification.Action.Builder(R.drawable.ic_menu_send,
                        getString(R.string.app_name)
                        .addRemoteInput(remoteInput)
                        .build();*/

        /*RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification_layout);
        view.setOnClickPendingIntent(R.id.btn_yes, pendingIntentNo);
        builder.setContent(view);*/
        return builder.build();
    }

    private Activity getActivity(){
        return this;
    }

}
