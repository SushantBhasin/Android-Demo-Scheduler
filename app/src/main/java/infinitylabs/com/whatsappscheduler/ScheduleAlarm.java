package infinitylabs.com.whatsappscheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.infinitylabs.whatsappscheduler.domain.AlarmDetails;
import com.infinitylabs.whatsappscheduler.reciever.AlarmReciever;

import java.util.Calendar;

/**
 * Created by Sushant Bhasin on 4/7/2016.
 */
public class ScheduleAlarm {

    public void scheduleAlarm(AlarmDetails alarm, Context ctx)
    {


        Long timer = alarm.getTime();
       // timer+=1*60*1000;
        Log.d("Hello","hello "+timer);
        Intent intentAlarm = new Intent(ctx,AlarmReciever.class);


        intentAlarm.putExtra("AlarmMessage",alarm.getMessage());
        intentAlarm.putExtra("AlarmContact",alarm.getContact().getNumber());
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timer, PendingIntent.getBroadcast(ctx, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    }

    public void scheduleDailyAlarm(AlarmDetails alarm,Context ctx)
    {

        Long timer = alarm.getTime();
        // timer+=1*60*1000;
        Log.d("Hello","hello "+timer);
        Intent intentAlarm = new Intent(ctx,AlarmReciever.class);
        intentAlarm.putExtra("AlarmMessage",alarm.getMessage());
        intentAlarm.putExtra("AlarmContact",alarm.getContact().getNumber());
        intentAlarm.putExtra("Days",alarm.getDays());
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timer,AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(ctx, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    }



    public void scheduleYearlyAlarm(AlarmDetails alarm,Context ctx)
    {


        Long timer = alarm.getTime();
        // timer+=1*60*1000;
        Log.d("Hello","hello "+timer);
        Intent intentAlarm = new Intent(ctx,AlarmReciever.class);
        Calendar today_plus_year = Calendar.getInstance();
        today_plus_year.add( Calendar.YEAR, 1 );

        intentAlarm.putExtra("AlarmMessage",alarm.getMessage());
        intentAlarm.putExtra("AlarmContact",alarm.getContact().getNumber());
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timer,today_plus_year.getTimeInMillis(), PendingIntent.getBroadcast(ctx, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    }
}

