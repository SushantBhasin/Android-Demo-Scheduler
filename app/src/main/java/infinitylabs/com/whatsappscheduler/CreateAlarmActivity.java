package infinitylabs.com.whatsappscheduler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.infinitylabs.whatsappscheduler.domain.AlarmDetails;
import com.infinitylabs.whatsappscheduler.domain.WhatsappContacts;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateAlarmActivity extends AppCompatActivity  {
    private String TAG="ServiceWhatsappController";
    AlarmDetails newItem;
    static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_alarm);
        newItem=new AlarmDetails();
        Spinner spinner= (Spinner) findViewById(R.id.frequency);
        ArrayAdapter freq=ArrayAdapter.createFromResource(this,R.array.frequency,R.layout.support_simple_spinner_dropdown_item);
        freq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(freq);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newItem.setType((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMM,yyyy");
        TextView date= (TextView) findViewById(R.id.startDay);
        date.setText(dateFormat.format(cal.getTime()));


        SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm");
        TextView time= (TextView) findViewById(R.id.startTime);
        time.setText(timeFormat.format(cal.getTime()));
        newItem.setStartDate(cal);

    }

    public void SelectContacts(View view) {
    Intent intent=new Intent(this,ContactChooser.class);
        startActivityForResult(intent,PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      Log.d(TAG,"HMM");
        if(requestCode==PICK_CONTACT_REQUEST)
        {
                String id=data.getStringExtra("ContactId");
                WhatsappContacts contact=WhatsappContacts.getContactById(getApplicationContext(),id);
                EditText contactName= (EditText) findViewById(R.id.contact);
                contactName.setText(contact.getContactName()+"("+contact.getNumber()+")");
                Log.d(TAG,"HMM"+contact.getContactName());
        }



    }

    public void DatePicker(View view) {
        final Calendar day = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day.set(Calendar.MONTH,monthOfYear);
                day.set(Calendar.YEAR,year);
                day.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                newItem.setStartDate(day);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMM,yyyy");
                TextView date= (TextView) findViewById(R.id.startDay);
                date.setText(dateFormat.format(day.getTime()));
            }
        },day.get(Calendar.YEAR),day.get(Calendar.MONTH),day.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void TimePicker(View view) {
        Calendar  c = Calendar.getInstance();


        new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener(){


            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar time=newItem.getStartDate();
                time.set(Calendar.HOUR_OF_DAY,hourOfDay);
                time.set(Calendar.MINUTE,minute);
                newItem.setStartDate(time);
                newItem.setTime(time.getTimeInMillis());
                SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm");
                TextView message = (TextView) findViewById(R.id.startTime);
                message.setText(timeFormat.format(time.getTime()));

            }
        },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();

    }



       /* Alarm alarm = new Alarm();
        EditText message = (EditText) findViewById(R.id.messageText);

        alarm.setMessage(message.getText().toString());
        Log.d("Text", message.getText().toString());
        if(selectedContact!=null) {
            alarm.setContact(selectedContact);
            Log.d("Text", selectedContact.getNumber().toString());
            Calendar calendar=day;
            if(calendar.get(Calendar.HOUR_OF_DAY)>hour)
                calendar.add(Calendar.DAY_OF_MONTH,1);
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,minutes);
            long time=calendar.getTimeInMillis();
//                long time=(long) (hour * 60 * 60 * 1000 + minutes * 60 * 1000);
            Log.d("Text",Long.toString(time));
            alarm.setTime(time);
            CheckBox repeat = (CheckBox) findViewById(R.id.repeat);
            CheckBox yearlyChk = (CheckBox) findViewById(R.id.yearlyChk);
            CheckBox hourlyChk = (CheckBox) findViewById(R.id.hourlyChk);
            CheckBox dailyChk = (CheckBox) findViewById(R.id.dailyChk);
            String text="";
            if(!repeat.isChecked() )
            {
                new ScheduleAlarm().scheduleAlarm(alarm, getApplicationContext());
            }
            else if(repeat.isChecked() && dailyChk.isChecked())
            {
                CheckBox monday= (CheckBox) findViewById(R.id.monCheck);
                if(monday.isChecked())
                    text=text.concat("Monday,");
                CheckBox tuesday= (CheckBox) findViewById(R.id.tueCheck);
                if(tuesday.isChecked())
                    text=text.concat("Tuesday,");
                CheckBox wednesday= (CheckBox) findViewById(R.id.wedCheck);
                if(wednesday.isChecked())
                    text=text.concat("Wednesday,");
                CheckBox thursday= (CheckBox) findViewById(R.id.thuCheck);
                if(thursday.isChecked())
                    text=text.concat("Thursday,");
                CheckBox friday= (CheckBox) findViewById(R.id.friCheck);
                if(friday.isChecked())
                    text=text.concat("Friday,");
                CheckBox saturday= (CheckBox) findViewById(R.id.satCheck);
                if(saturday.isChecked())
                    text=text.concat("Saturday,");
                CheckBox sunday= (CheckBox) findViewById(R.id.sunCheck);
                if(sunday.isChecked())
                    text=text.concat("Sunday");
                alarm.setDays(text);
                new ScheduleAlarm().scheduleDailyAlarm(alarm, getApplicationContext());
            }


                  *//*  Log.d("Text", hour + ":" + minutes);*//*

            else if(repeat.isChecked() && yearlyChk.isChecked())
            {
                new ScheduleAlarm().scheduleYearlyAlarm(alarm, getApplicationContext());
            }


            dbHelper.insertContact(selectedContact.getContactName(),selectedContact.getNumber(),Long.toString(time),text,"true",message.getText().toString());
            NavUtils.navigateUpTo(this, getParentActivityIntent());

        }*/



    public void CreateAlarm(View view) {


    }
}
