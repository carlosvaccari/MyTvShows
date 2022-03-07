package com.cvaccari.commons.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        context ?: return

        if (intent.action != null) {
            Log.d(TAG, "BootCompleted")
            if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED, ignoreCase = true)) {
                AlarmUtils.setupReminder(context)
                return
            }
        }
        //Trigger the notification
        AlarmUtils.sendNotification(context)
    }

    companion object {
        const val TAG = "AlarmReceiver"
    }
}