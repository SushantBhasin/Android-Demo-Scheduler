package com.infinitylabs.whatsappscheduler.domain;

import java.util.Calendar;

/**
 * Created by Sushant Bhasin on 4/16/2016.
 */
public class AlarmDetails {

    private WhatsappContacts contact;
    private long time;
    private boolean enabled;
    private String message;
    private String type;
    private String days;
    private Calendar startDate;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }



    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




    public WhatsappContacts getContact() {
        return contact;
    }

    public void setContact(WhatsappContacts contactName) {
        this.contact = contactName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
