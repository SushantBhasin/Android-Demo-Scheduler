package infinitylabs.com.whatsappscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.infinitylabs.whatsappscheduler.domain.WhatsappContacts;
import com.infinitylabs.whatsappscheduler.helper.ContactArrayAdapter;

import java.util.ArrayList;

public class ContactChooser extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_chooser);

        final ArrayList<WhatsappContacts> contacts = WhatsappContacts.readContacts(getApplicationContext());

        ContactArrayAdapter items=new ContactArrayAdapter(this,0,contacts);
        contact= (ListView) findViewById(R.id.contact_list);
        contact.setAdapter(items);
        contact.setTextFilterEnabled(true);
        SearchView searchView= (SearchView) findViewById(R.id.search_contact);

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint("Search Here");
        contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent result=new Intent();
                WhatsappContacts contacts= (WhatsappContacts) parent.getItemAtPosition(position);

                result.putExtra("ContactId",contacts.getId());
                setResult(CreateAlarmActivity.PICK_CONTACT_REQUEST,result);
                finish();
            }
        });


    }




    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {

            contact.clearTextFilter();
        } else {

            contact.setFilterText(newText.toString());
        }
        return true;
    }
}
