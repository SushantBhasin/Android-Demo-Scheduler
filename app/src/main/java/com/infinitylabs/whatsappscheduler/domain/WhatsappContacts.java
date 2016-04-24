package com.infinitylabs.whatsappscheduler.domain;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sushant Bhasin on 4/16/2016.
 */
public class WhatsappContacts {

    public String id;
    private String contactName;
    private String Number;
    public String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }



    public static WhatsappContacts getContactById(Context ctx,String id)
    {Cursor c = ctx.getApplicationContext().getContentResolver().query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID,ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI },
            ContactsContract.RawContacts.ACCOUNT_TYPE + "= ? and "+ContactsContract.CommonDataKinds.Phone._ID+"= ?",
            new String[] { "com.whatsapp",id },
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC ");
        WhatsappContacts contact=new WhatsappContacts();
        int contactName = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int contactNo = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int ptUrl=c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI);
        while (c.moveToNext())
        {
            contact.setId(id);
            Log.d("ID", id);

            contact.setImageUrl(c.getString(ptUrl));
            Log.d("Photo", c.getString(ptUrl));

            contact.setContactName(c.getString(contactName));
            Log.d("Name", c.getString(contactName));

            contact.setNumber(c.getString(contactNo));
            Log.d("Num", c.getString(contactNo));


        }
        return contact;

    }

    public static ArrayList<WhatsappContacts> readContacts(Context ctx)
    {Cursor c = ctx.getApplicationContext().getContentResolver().query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            new String[] { },//ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID,ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
            ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
            new String[] { "com.whatsapp" },
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC ");

        ArrayList<WhatsappContacts> myWhatsappContacts = new ArrayList<WhatsappContacts>();
       /* int contactNameColumn = c.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY);*/
        int contactName = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int contactNo = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int id=c.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
        int ptUrl=c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);
        int uri=c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);
        while (c.moveToNext())
        {  WhatsappContacts contacts=new WhatsappContacts();
            contacts.setId(c.getString(id));
            Log.d("ID", c.getString(id));

            if(c.getString(ptUrl)!=null) {
                contacts.setImageUrl(c.getString(ptUrl));

                Log.d("Photo", c.getString(ptUrl));
            }

            contacts.setContactName(c.getString(contactName));
            Log.d("Name", c.getString(contactName));

            contacts.setNumber(c.getString(contactNo));
            Log.d("Num", c.getString(contactNo));

            myWhatsappContacts.add(contacts);
        }
        return  myWhatsappContacts;
    }
}
