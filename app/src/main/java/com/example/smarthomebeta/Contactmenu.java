package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.smarthomebeta.Constants.REF_CONTACTS;
import static com.example.smarthomebeta.Methods.dateAndTimeTostring;


public class Contactmenu extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener {
    ListView contactslist;
    LinkedList<String> contacts;
    private ArrayAdapter<String> adp;
    DataSnapshot contactsDs;
    EditText searchfilter;
    String keyvalue;
    int pos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactmenu);



        contactslist = (ListView) findViewById(R.id.contactslist);
        contacts = new LinkedList<String>();
        adp = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, contacts);

        contactslist.setOnItemLongClickListener(this);
        contactslist.setOnCreateContextMenuListener(this);
        contactslist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        REF_CONTACTS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactsDs = snapshot;
                for (DataSnapshot ds : snapshot.getChildren()) {

                    if(ds.child("contactIsActive").getValue().equals(true)||ds.child("contactIsActive").getValue().toString().equals("1"))
                    contacts.add(ds.child("contactFamily").getValue().toString() + " " + ds.child("contactName").getValue().toString() + " - " + ds.getKey().toString());

                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        contactslist.setAdapter(adp);

        EditText serachfilter = (EditText)findViewById(R.id.searchfilter);
        serachfilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Contactmenu.this).adp.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contactmenuitems, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.newcontact) {
            Intent t1 = new Intent(this, newContact.class);
            startActivityForResult(t1, 1);
        }

        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("צפה בפרטי איש הקשר");
        menu.add("מחק איש קשר");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
            String oper = item.getTitle().toString();
            if(oper.equals("צפה בפרטי איש הקשר")){
                Intent t1 = new Intent(this, newContact.class);
                t1.putExtra("key", keyvalue);
                startActivity(t1);


            }else{
                contacts.remove(pos);
                adp.notifyDataSetChanged();
            }
            return true;
    }





    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        String selectedFromList = (contactslist.getItemAtPosition(position)).toString();
        keyvalue = selectedFromList.substring(selectedFromList.length() - 5, selectedFromList.length());
        Log.d("position text:", selectedFromList + "");
        Log.d("position text:", keyvalue + "");
        Log.d("position text:", position + "");

        return false;
    }
}
