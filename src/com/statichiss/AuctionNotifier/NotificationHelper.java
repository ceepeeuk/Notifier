package com.statichiss.AuctionNotifier;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationHelper {
    public static void showNotification(Context context, int searchId, int numberOfItems, String searchTerm) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String message = "Found " + numberOfItems + " new listings for " + searchTerm;

        long when = System.currentTimeMillis();
        Notification notification = new Notification(R.drawable.ic_hammer, message, when);

        CharSequence contentTitle = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("searchId", searchId);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        notification.setLatestEventInfo(context, contentTitle, message, contentIntent);
        notificationManager.notify(searchId, notification);
    }

    public static void cancelNotification(Context context, int searchId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(searchId);
    }
}
