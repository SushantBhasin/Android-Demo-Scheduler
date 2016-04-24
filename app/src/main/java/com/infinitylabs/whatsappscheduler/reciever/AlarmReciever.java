package com.infinitylabs.whatsappscheduler.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.infinitylabs.whatsappscheduler.service.CustomAppControlService;

import java.util.Calendar;

/**
 * Created by Sushant Bhasin on 4/16/2016.
 */
public class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Uri uri = Uri.parse("sms://"+intent.getStringExtra("AlarmContact"));
     /*   Uri uri = Uri.parse("sms://9999330469");
        Log.d("Test", "Starting Service");
        Intent messageIntent=new Intent(context,ACService.class);
        messageIntent.putExtra("Message", intent.getStringExtra("AlarmMessage")+" "+intent.getStringExtra("AlarmContact"));
        context.startService(messageIntent);
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO,uri);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sendIntent);*/

        Context appContext=context.getApplicationContext();
        Uri uri = Uri.parse("sms://"+intent.getStringExtra("AlarmContact"));
        Log.d("Test", "Starting Service");
        String message=intent.getStringExtra("AlarmMessage");
        Intent intent1=new Intent(context,CustomAppControlService.class);
        intent1.putExtra("AlarmMessage", message);
        appContext.startService(intent1);

        String days=intent.getStringExtra("Days");
        if(days.length()>0 && days!=null)
        {   boolean enable=false;
            Calendar c=Calendar.getInstance();
            int day_of_week=c.get(Calendar.DAY_OF_WEEK);
            switch(day_of_week){
                case Calendar.SATURDAY:
                    if(days.contains("Saturday"))
                        enable=true;
                    break;
                case Calendar.SUNDAY:
                    if(days.contains("Sunday"))
                        enable=true;
                    break;
                case Calendar.MONDAY:
                    if(days.contains("Monday"))
                        enable=true;
                    break;
                case Calendar.TUESDAY:
                    if(days.contains("Tuesday"))
                        enable=true;
                    break;
                case Calendar.WEDNESDAY:
                    if(days.contains("Wednesday"))
                        enable=true;
                    break;
                case Calendar.THURSDAY:
                    if(days.contains("Thursday"))
                        enable=true;
                    break;
                case Calendar.FRIDAY:
                    if(days.contains("Friday"))
                        enable=true;
                    break;
            }
            if(enable)
            {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO,uri);
                sendIntent.setPackage("com.whatsapp");
                Intent chooser=Intent.createChooser(sendIntent,"asdasd");
                chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(chooser);

            }
        }
        else{
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO,uri);
            sendIntent.setPackage("com.whatsapp");
            Intent chooser=Intent.createChooser(sendIntent,"asdasd");
            chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(chooser);
        }



    }


}
