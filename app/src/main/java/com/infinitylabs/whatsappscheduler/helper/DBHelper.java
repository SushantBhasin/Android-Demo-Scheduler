package com.infinitylabs.whatsappscheduler.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.infinitylabs.whatsappscheduler.domain.AlarmDetails;
import com.infinitylabs.whatsappscheduler.domain.WhatsappContacts;

import java.util.ArrayList;

/**
 * Created by Sushant Bhasin on 4/10/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AlarmsWhatsapp.db";
    public static final String ALARM_TABLE_NAME = "whatsappAlarms";
    public static final String ALARM_COLUMN_NAME = "name";
    public static final String ALARM_COLUMN_ID = "id";
    public static final String ALARM_COLUMN_NUMBER="number";
    public static final String ALARM_COLUMN_ALARMTIME = "alarmtime";
    public static final String ALARM_COLUMN_ALARMDAYS = "alarmdays";
    public static final String ALARM_COLUMN_ENABLED = "enabled";
    public static final String ALARM_COLUMN_MESSAGE = "message";
    public static final String ALARM_START_DAY = "startday";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table " + ALARM_TABLE_NAME +
                        "("+ALARM_COLUMN_ID+" integer primary key, " + ALARM_COLUMN_NAME + " text," + ALARM_COLUMN_NUMBER + " text,"+ ALARM_COLUMN_ALARMTIME + " text, " + ALARM_COLUMN_ALARMDAYS + " text," + ALARM_COLUMN_MESSAGE + " text," + ALARM_START_DAY + " text," + ALARM_COLUMN_ENABLED + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact( String id,String name,String number, String alarmtime, String alarmdays, String enabled, String message, String startDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALARM_COLUMN_NAME, name);
        contentValues.put(ALARM_COLUMN_NUMBER, number);
        contentValues.put(ALARM_COLUMN_ID, id);
        contentValues.put(ALARM_COLUMN_ALARMTIME, alarmtime);
        contentValues.put(ALARM_COLUMN_ALARMDAYS, alarmdays);
        contentValues.put(ALARM_COLUMN_ENABLED, enabled);
        contentValues.put(ALARM_COLUMN_MESSAGE, message);
        contentValues.put(ALARM_START_DAY, startDay);
        db.insert(ALARM_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + ALARM_TABLE_NAME + " where " + ALARM_COLUMN_ID + "=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ALARM_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(String id,String name,String number,  String alarmtime, String alarmdays, String enabled, String message, String startDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALARM_COLUMN_NAME, name);
        contentValues.put(ALARM_COLUMN_NUMBER, number);
        contentValues.put(ALARM_COLUMN_ID, id);
        contentValues.put(ALARM_COLUMN_ALARMTIME, alarmtime);
        contentValues.put(ALARM_COLUMN_ALARMDAYS, alarmdays);
        contentValues.put(ALARM_COLUMN_ENABLED, enabled);
        contentValues.put(ALARM_COLUMN_MESSAGE, message);
        contentValues.put(ALARM_START_DAY, startDay);
        db.update(ALARM_TABLE_NAME, contentValues, "id = ? ", new String[]{id});
        return true;
    }

    public Integer deleteContact (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ALARM_TABLE_NAME,
                "id = ? ",
                new String[] { id });
    }

    public ArrayList<AlarmDetails> getAllCotacts() {
        ArrayList<AlarmDetails> array_list = new ArrayList<AlarmDetails>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + ALARM_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            AlarmDetails alarm = new AlarmDetails();
            WhatsappContacts whatsappContact = new WhatsappContacts();

            whatsappContact.setId(res.getString(res.getColumnIndex(ALARM_COLUMN_ID)));
            whatsappContact.setContactName(res.getString(res.getColumnIndex(ALARM_COLUMN_NAME)));
            whatsappContact.setNumber(res.getString(res.getColumnIndex(ALARM_COLUMN_NUMBER)));
            alarm.setContact(whatsappContact);
            alarm.setDays(res.getString(res.getColumnIndex(ALARM_COLUMN_ALARMDAYS)));

            String time = res.getString(res.getColumnIndex(ALARM_COLUMN_ALARMTIME));
            alarm.setTime(Long.parseLong(time));

           /* String day = res.getString(res.getColumnIndex(ALARM_START_DAY));
            alarm.setStartDate(day);*/

            alarm.setMessage(res.getString(res.getColumnIndex(ALARM_COLUMN_MESSAGE)));

            String enabled = res.getString(res.getColumnIndex(ALARM_COLUMN_ENABLED));

            if (enabled.equals("true"))
                alarm.setEnabled(true);
            else
                alarm.setEnabled(false);

            array_list.add(alarm);
            res.moveToNext();
        }
        return array_list;
    }


}