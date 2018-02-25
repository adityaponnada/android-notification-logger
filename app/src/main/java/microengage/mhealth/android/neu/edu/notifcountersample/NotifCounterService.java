package microengage.mhealth.android.neu.edu.notifcountersample;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.Date;

/**
 * Created by adityaponnada on 25/08/17.
 */

public class NotifCounterService extends NotificationListenerService {

    public static String TAG = NotifCounterService.class.getSimpleName();

    Date dateStart;

    private Logger logger;

    private Context mContext;


    @Override
    public void onCreate() {
        Log.d(TAG, "Created");

        logger = new Logger(TAG);
        mContext = getApplicationContext();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Log.d(TAG, "Notification has arrived");

        Log.d(TAG, "ID: " + sbn.getId() + " Posted by: " + sbn.getPackageName() + " at: " + sbn.getPostTime() + " ");

        logger.i(sbn.getId() + "," + sbn.getPackageName() + "," + sbn.getPostTime(), mContext);
        logger.close();

        /*Log.i(TAG, "ID:" + sbn.getId());
        Log.i(TAG, "Posted by:" + sbn.getPackageName());
        Log.i(TAG, "tickerText:" + sbn.getNotification().tickerText);*/

       /* for (String key : sbn.getNotification().extras.keySet()) {
            Log.i(TAG, key + "=" + sbn.getNotification().extras.get(key).toString());
        }*/

    }
}

