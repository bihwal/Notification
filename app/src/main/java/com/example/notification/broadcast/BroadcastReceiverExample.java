package com.example.notification.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notification.R;
import com.example.notification.createchannel.CreateChannel;

public class BroadcastReceiverExample extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiverExample(Context context){
        this.context=context;
    }

    @Override
    public void onReceive(Context context, Intent intent){
        boolean noConnectivity;

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noConnectivity=intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);

            if (noConnectivity){
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                DisplayNotification1();
             } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                DisplayNotification2();
            }
        }
    }
    private void DisplayNotification1(){
        Notification notification=new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.notification1)
                .setContentTitle("No Connection")
                .setContentText("No Connectivity, Please Connect")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }

    private void DisplayNotification2(){
        Notification notification=new NotificationCompat.Builder(context,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.notification2)
                .setContentTitle("Connected")
                .setContentText("Connected to the network")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(2,notification);
    }
}