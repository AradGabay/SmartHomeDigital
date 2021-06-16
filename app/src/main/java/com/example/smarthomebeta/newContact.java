package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.smarthomebeta.Constants.REF_CONTACTS;



public class newContact extends AppCompatActivity {
    TextView recordcount;
    //Contact contact = new Contact();
    EditText contactFamilyEdt,contactNameEdt,contactCityEdt,contactStreetEdt,contactStreetNumEdt,contactApartmentNumberEdt,contactFloorEdt,contactEmailEdt,contactCellularNumEdt,contactPhoneNumEdt;
    String UidContact;
    Boolean connectToHouse = false;
    Intent t2;
    DataSnapshot contactsDs;
    String keyvalue;
    ImageButton savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        recordcount = (TextView)findViewById(R.id.recordCountTxt);
        contactNameEdt = (EditText)findViewById(R.id.contactNameEdt);
        contactFamilyEdt = (EditText)findViewById(R.id.contactFamilyEdt);
        contactCityEdt = (EditText)findViewById(R.id.contactCityEdt);
        contactStreetEdt = (EditText)findViewById(R.id.contactStreetEdt);
        contactStreetNumEdt = (EditText)findViewById(R.id.contactStreetNumEdt);
        contactApartmentNumberEdt = (EditText)findViewById(R.id.contactApartmentNumberEdt);
        contactFloorEdt = (EditText)findViewById(R.id.contactFloorEdt);
        contactEmailEdt = (EditText)findViewById(R.id.contactEmailEdt);
        contactCellularNumEdt = (EditText)findViewById(R.id.contactCellularNumEdt);
        contactPhoneNumEdt = (EditText)findViewById(R.id.contactPhoneNumEdt);
        t2 = getIntent();
        keyvalue = t2.getStringExtra("key");
        //Log.d("keyvalue:",keyvalue);
        enterContactInfo(keyvalue);

    }



    public void addContact(View view) {
        REF_CONTACTS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String count = ""+snapshot.getChildrenCount();
                pushContact(count);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        startActivity(new Intent(this,Contactmenu.class));
    }
    public void pushContact(String count){
        if(keyvalue != null) {
            UidContact=keyvalue;
        } else {
            int childCount = Constants.CONTACT_FIRST_REC_ID + Integer.parseInt(count);
            UidContact = "" + childCount;
        }
        String contactFamily = contactFamilyEdt.getText().toString();
        String contactName=contactNameEdt.getText().toString();
        String contactCity= contactCityEdt.getText().toString();
        String contactStreet = contactStreetEdt.getText().toString();
        String contactStreetNum = contactStreetNumEdt.getText().toString();
        String contactApartmentNum = contactApartmentNumberEdt.getText().toString();
        String contactFloor = contactFloorEdt.getText().toString();
        String contactEmail = contactEmailEdt.getText().toString();
        String contactCellularNum = contactCellularNumEdt.getText().toString();
        String contactPhoneNum = contactPhoneNumEdt.getText().toString();
        Contact contact = new Contact(contactFamily, contactName, contactEmail, contactCellularNum,contactCity,contactStreet,contactStreetNum,contactApartmentNum,contactFloor,contactPhoneNum);
        REF_CONTACTS.child(UidContact).setValue(contact);
        if (connectToHouse) {
            Intent intent = new Intent(this,newHouse.class);
            intent.putExtra("contact",UidContact);
            startActivity(intent);
        }
    }

    public void addContactAndHouse(View view) {
        connectToHouse = true;
        addContact(view);
    }
    private void enterContactInfo(String keyvalue) {

        REF_CONTACTS.child(keyvalue).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactsDs = snapshot;
                //Log.d("snapshot2:", contactsDs.child("contactCity").toString());
                String city, street, streetNum, floor, apartmentNum, phoneNum;
                if(contactsDs.child("addressCity").getValue()!=null) city = contactsDs.child("addressCity").getValue().toString();
                else city = contactsDs.child("contactCity").getValue().toString();
                contactCityEdt.setText(city);

                if(contactsDs.child("contactStreet").getValue()!=null) street = contactsDs.child("contactStreet").getValue().toString();
                else street = contactsDs.child("addressStreetName").getValue().toString();
                contactStreetEdt.setText(street);

                if(contactsDs.child("contactStreetNum").getValue()!=null) streetNum = contactsDs.child("contactStreetNum").getValue().toString();
                else streetNum = contactsDs.child("addressStreetNumber").getValue().toString();
                contactStreetNumEdt.setText(streetNum);

                if(contactsDs.child("contactFloor").getValue()!=null) floor = contactsDs.child("contactFloor").getValue().toString();
                else floor = contactsDs.child("addressFloor").getValue().toString();
                contactFloorEdt.setText(floor);

                if(contactsDs.child("contactApartmentNumber").getValue()!=null) apartmentNum = contactsDs.child("contactApartmentNumber").getValue().toString();
                else apartmentNum= contactsDs.child("addressApartmentNumber").getValue().toString();
                contactApartmentNumberEdt.setText(apartmentNum);

                if(contactsDs.child("contactApartmentNumber").getValue()!=null) apartmentNum = contactsDs.child("contactApartmentNumber").getValue().toString();
                else apartmentNum= contactsDs.child("addressApartmentNumber").getValue().toString();
                contactApartmentNumberEdt.setText(apartmentNum);

                if(contactsDs.child("addressPhoneNumber").getValue()!=null) phoneNum = contactsDs.child("addressPhoneNumber").getValue().toString();
                else phoneNum= contactsDs.child("contactPhoneNum").getValue().toString();
                contactPhoneNumEdt.setText(phoneNum);

                contactNameEdt.setText(contactsDs.child("contactName").getValue().toString());
                contactEmailEdt.setText(contactsDs.child("contactEmail").getValue().toString());
                contactCellularNumEdt.setText(contactsDs.child("contactCellularNum").getValue().toString());
                contactFamilyEdt.setText(contactsDs.child("contactFamily").getValue().toString());

               /* contactStreetEdt.setText(contactsDs.child("addressStreetName").getValue().toString());
                contactStreetNumEdt.setText(contactsDs.child("addressStreetNumber").getValue().toString());
                contactApartmentNumberEdt.setText(contactsDs.child("addressApartmentNumber").getValue().toString());
                String addressPhoneNum = contactsDs.child("addressPhoneNumber").getValue().toString();
                contactFloorEdt.setText(contactsDs.child("addressFloor").getValue().toString());*/
                String isActive = contactsDs.child("contactIsActive").getValue().toString();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        
        //savebutton = (ImageButton) menu.findItem(R.id.saveedit).getActionView();

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.editmode) {
            contactNameEdt.setEnabled(true);
            contactFamilyEdt.setEnabled(true);
            contactCityEdt .setEnabled(true);
            contactStreetEdt .setEnabled(true);
            contactStreetNumEdt .setEnabled(true);
            contactApartmentNumberEdt .setEnabled(true);
            contactFloorEdt .setEnabled(true);
            contactEmailEdt .setEnabled(true);
            contactCellularNumEdt .setEnabled(true);
            contactPhoneNumEdt .setEnabled(true);

            item.setVisible(false);



        }


        return true;
    }
}