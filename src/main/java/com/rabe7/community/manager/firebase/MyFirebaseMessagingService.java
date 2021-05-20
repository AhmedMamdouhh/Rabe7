//package com.rabe7.community.manager.firebase;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//import com.rabe7.community.R;
//import com.rabe7.community.ui.main.MainActivity;
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//
//
//        }
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//
//        }
//
//    }
//
//    @Override
//    public void onNewToken(String token) {
//
//        sendRegistrationToServer(token);
//    }
//
//
//    private void sendRegistrationToServer(String token) {
//        // TODO: Implement this method to send token to your app server.
//    }
//
//
//    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
//                        .setContentTitle(getString(R.string.fcm_message))
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }
//}