package com.cvaccari.commons.extensions

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.cvaccari.commons.R

private const val CHANNEL_ID = "Notifications"
private const val CHANNEL_NAME = "Notifications"
private const val SPLASH_ACTIVITY = "com.cvaccari.features.splash.SplashActivity"


fun NotificationManager.createNotificationChannelIfNeeded(
    channelID: String = CHANNEL_ID,
    channelName: String = CHANNEL_NAME
) {
    this.createNotificationChannel(
        NotificationChannel(
            channelID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
    )
}

fun NotificationManager.sendNotification(
    applicationContext: Context,
    title: String?,
    body: String?,
    notificationID: Int = 1
) {
    val notificationBuilder =
        NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setContentIntent(pendingIntent(applicationContext))
            .build()

    notify(notificationID, notificationBuilder)
}


private fun pendingIntent(
    applicationContext: Context
): PendingIntent? {

    return PendingIntent.getActivity(
        applicationContext,
        1,
        Intent(applicationContext, Class.forName(SPLASH_ACTIVITY)),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
}
