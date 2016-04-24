package com.infinitylabs.whatsappscheduler.service;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by Sushant Bhasin on 4/16/2016.
 */
public class CustomAppControlService extends AccessibilityService {
    private boolean EnableService=false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("Test", "Sad Hello");
        if (EnableService) {

            Log.d("Test", "Hello");
            Log.d("Hmm",event.getPackageName().toString());
            AccessibilityNodeInfo source = event.getSource();
            if(source==null)
                Log.d("SHIT","SHIT");
            Log.d("Test1", source.getClassName() + " : " + source.toString());
            Log.d("Test2", source.getClassName() + " : " + Integer.toString(source.getChildCount()));
            if (source.getParent() != null)
                Log.d("Test3", source.getClassName() + " : " + source.getParent().getClassName());
            if (source.getClassName().equals("android.widget.FrameLayout")) {
                Log.d("Test1", source.toString());
                Log.d("Test1", "Getting all children");
                for (int i = 0; i < source.getChildCount(); i++) {
                    AccessibilityNodeInfo child = source.getChild(i);
                    Log.d("Test1", "(" + i + ") " + child.getClassName() + " " + child.getText() + " " + child.getContentDescription());
                    if (child.getClassName().equals("android.widget.ImageButton") && child.getContentDescription().equals("Send")) {

                        Log.d("Test2", child.toString());
                        child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        EnableService=false;
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    }

                    if (child.getClassName().equals("android.widget.EditText")) {


                        child.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    }


                }
            }

            if (source.getClassName().equals("android.widget.ImageButton") && source.getContentDescription().equals("Send")) {

                Log.d("Test2", source.toString());
                source.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                EnableService=false;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }


      /*  int EVENT_TYPE=event.getEventType();
        Toast.makeText(ACService.this, AccessibilityEvent.eventTypeToString(EVENT_TYPE), Toast.LENGTH_SHORT).show();*/

        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){


        String text = intent.getStringExtra("AlarmMessage");
        ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label","");
        clipboard.setPrimaryClip(clip);
        clip=ClipData.newPlainText("label",text);
        EnableService=true;
        return START_STICKY;

    }
}
