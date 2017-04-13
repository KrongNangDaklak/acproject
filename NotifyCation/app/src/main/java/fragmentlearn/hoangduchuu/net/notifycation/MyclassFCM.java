package fragmentlearn.hoangduchuu.net.notifycation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URI;

/**
 * Created by hoang on 26/03/2017.
 */

public class MyclassFCM extends FirebaseMessagingService {
    String TAG = "huuhoang";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, remoteMessage.getMessageId()
                    + remoteMessage.getFrom()
                    + remoteMessage.getMessageType()
                    + remoteMessage.getCollapseKey());
            sendNotifycations(remoteMessage.getNotification().getBody() );

        }

        if (remoteMessage.getData() != null) {
            Log.d(TAG, remoteMessage.getData() + "");
            Log.d(TAG, remoteMessage.getData() + remoteMessage.getData().get("k1"));
        }
    }

    private void sendNotifycations(String msgBody) {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultUrlSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifycation = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Huu ContentTitle")
                .setContentText(msgBody)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setSound(defaultUrlSound);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notifycation.build());
    }
}
