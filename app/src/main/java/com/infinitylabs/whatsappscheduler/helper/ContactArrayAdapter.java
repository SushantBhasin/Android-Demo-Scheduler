package com.infinitylabs.whatsappscheduler.helper;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infinitylabs.whatsappscheduler.domain.WhatsappContacts;

import java.util.List;

import infinitylabs.com.whatsappscheduler.R;

/**
 * Created by Sushant Bhasin on 4/21/2016.
 */
public class ContactArrayAdapter extends ArrayAdapter<WhatsappContacts> {


    public ContactArrayAdapter(Context context, int resource, List<WhatsappContacts> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WhatsappContacts contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.contact_list_name);
        TextView number = (TextView) convertView.findViewById(R.id.contact_list_number);
        ImageView image= (ImageView) convertView.findViewById(R.id.imageView);
        if(contact.getImageUrl()!=null) {
            Uri imageuri = Uri.parse(contact.getImageUrl());
            image.setImageURI(imageuri);
        }
        else
        image.setImageDrawable(null);
        name.setText(contact.getContactName());
        number.setText(contact.getNumber());

        return convertView;
    }


}
