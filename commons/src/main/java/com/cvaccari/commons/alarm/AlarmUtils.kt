package com.cvaccari.commons.alarm

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import com.cvaccari.commons.R
import com.cvaccari.commons.extensions.createNotificationChannelIfNeeded
import com.cvaccari.commons.extensions.sendNotification
import com.cvaccari.commons.extensions.setReminder

object AlarmUtils {

    private const val ALARM_HOUR = 18
    private const val ALARM_MINUTE = 0

    fun sendNotification(context: Context) {
        ContextCompat.getSystemService(context, NotificationManager::class.java)?.apply {
            val randomNumber = java.util.Random().nextInt(2)
            createNotificationChannelIfNeeded()
            sendNotification(
                context,
                context.resources.getStringArray(R.array.notification_titles)[randomNumber],
                context.resources.getStringArray(R.array.notification_messages)[randomNumber],
            )
        }
    }

    fun setupReminder(
        context: Context
    ): Boolean {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.setReminder(
            context,
            AlarmReceiver::class.java,
            ALARM_HOUR,
            ALARM_MINUTE
        )
        return false
    }
}