package com.cvaccari.commons.extensions
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.content.ComponentName
import android.content.Context
import java.util.Calendar

private const val WEEKLY_REMINDER_REQUEST_CODE = 1010


fun AlarmManager.setReminder(context: Context, cls: Class<*>?, hour: Int, min: Int) {
    val calendar: Calendar = Calendar.getInstance()
    val setcalendar: Calendar = Calendar.getInstance()
    setcalendar.set(Calendar.HOUR_OF_DAY, hour)
    setcalendar.set(Calendar.MINUTE, min)
    setcalendar.set(Calendar.SECOND, 0)
    // cancel already scheduled reminders
    cancelReminder(context, cls)
    if (setcalendar.before(calendar)) setcalendar.add(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)

    // Enable a receiver
    val receiver = ComponentName(context, cls!!)
    val pm: PackageManager = context.packageManager
    pm.setComponentEnabledSetting(
        receiver,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )
    val intent1 = Intent(context, cls)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        WEEKLY_REMINDER_REQUEST_CODE, intent1,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
    )
    val am = context.getSystemService(ALARM_SERVICE) as AlarmManager
    am.setInexactRepeating(
        AlarmManager.RTC_WAKEUP, setcalendar.timeInMillis,
        AlarmManager.INTERVAL_DAY, pendingIntent
    )
}

private fun cancelReminder(context: Context, cls: Class<*>?) {
    // Disable a receiver
    val receiver = ComponentName(context, cls!!)
    val pm = context.packageManager
    pm.setComponentEnabledSetting(
        receiver,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )
    val intent1 = Intent(context, cls)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        WEEKLY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_MUTABLE
    )
    val am = context.getSystemService(ALARM_SERVICE) as AlarmManager
    am.cancel(pendingIntent)
    pendingIntent.cancel()
}
