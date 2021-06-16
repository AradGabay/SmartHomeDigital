package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;



public class newHouse extends AppCompatActivity {
    String UidHouse, UidContact;

    EditText houseCityEdt,houseStreetEdt,houseStreetNumEdt,houseApartmentNumberEdt,houseFloorEdt,housePhoneNumEdt,
            houseDnsEdt,housePasswordEdt;
    String uidContact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house);
        houseCityEdt = (EditText)findViewById(R.id.houseCityEdt);
        houseStreetEdt = (EditText)findViewById(R.id.houseStreetEdt);
        houseStreetNumEdt = (EditText)findViewById(R.id.houseStreetNumEdt);
        houseApartmentNumberEdt = (EditText)findViewById(R.id.houseApartmentNumberEdt);
        houseFloorEdt = (EditText)findViewById(R.id.houseFloorEdt);
        housePhoneNumEdt = (EditText)findViewById(R.id.housePhoneNumEdt);
        houseDnsEdt = (EditText)findViewById(R.id.houseDnsEdt);
        housePasswordEdt = (EditText)findViewById(R.id.housePasswordEdt);
    }

    public void addHouse(View view) {
        if(houseCityEdt.getText().toString().equals("")) Toast.makeText(this, "שדה עיר חובה", Toast.LENGTH_SHORT).show();
        else if(houseStreetEdt.getText().toString().equals("")) Toast.makeText(this, "שדה רחוב חובה", Toast.LENGTH_SHORT).show();
        else if(houseStreetNumEdt.getText().toString().equals("")) Toast.makeText(this, "שדה מספר בית חובה", Toast.LENGTH_SHORT).show();
        else if(houseApartmentNumberEdt.getText().toString().equals("")) Toast.makeText(this, "שדה מספר דירה חובה", Toast.LENGTH_SHORT).show();
        else if(houseDnsEdt.getText().toString().equals("")) Toast.makeText(this, "שדה DNS חובה", Toast.LENGTH_SHORT).show();
        else if(housePhoneNumEdt.getText().toString().equals("")) Toast.makeText(this, "שדה טלפון חובה", Toast.LENGTH_SHORT).show();
        else if(housePasswordEdt.getText().toString().equals("")) Toast.makeText(this, "שדה סיסמה חובה", Toast.LENGTH_SHORT).show();
        else{
            String uidContact;
            if(getIntent().getSerializableExtra("contact")!=null) uidContact = "" + getIntent().getSerializableExtra("contact").toString();
        Constants.REF_PRIVATE_CURRENT_YEAR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String count = ""+snapshot.getChildrenCount();
                Log.d("TAG", "childeNum:"+count.toString());
                pushHouse(count);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        
    }}
    public void pushHouse(String count){
        int childCount = Constants.PRIVATE_HOUSE_REC_ID+Integer.parseInt(count);
        UidHouse = ""+childCount;
        String houseCity= houseCityEdt.getText().toString();
        String houseStreet = houseStreetEdt.getText().toString();
        String houseStreetNum = houseStreetNumEdt.getText().toString();
        String houseApartmentNum = houseApartmentNumberEdt.getText().toString();
        String houseFloor = houseFloorEdt.getText().toString();
        String housePhoneNum = housePhoneNumEdt.getText().toString();
        String houseDnd = houseDnsEdt.getText().toString();
        String housePassword = housePasswordEdt.getText().toString();
        if (getIntent().getSerializableExtra("contact")!=null) UidContact = "" + getIntent().getSerializableExtra("contact").toString();
        Log.d("TAG", "Uidcontact: "+UidContact);

        Address houseAddress = new Address( houseCity, houseStreet, houseStreetNum, houseApartmentNum, houseFloor, housePhoneNum);
        House house = new House(houseDnd, housePassword, houseAddress, UidContact,null,null,null,null);
        Constants.REF_PRIVATE_CURRENT_YEAR.child(UidHouse).setValue(house);
        Toast.makeText(this, "בית נוסף בהצלחה", Toast.LENGTH_SHORT).show();
        if (UidContact != null) {
            Constants.REF_PRIVATE_CURRENT_YEAR.child(UidHouse).child("Contacts").child("1").setValue(UidContact);
            // Connection connection = new Connection("owner",uidContact);
            // uidContact = connection.toString();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        menu.add(0,0,250,"הוסף הזמנה");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==0){
            Intent t1 = new Intent(this, newOrder.class);
            t1.putExtra("uidhouse",UidHouse);
            t1.putExtra("uidContact",UidContact);
            if(UidHouse== null) Toast.makeText(this, "אין בית להוספת הזמנה חדשה.", Toast.LENGTH_SHORT).show();
                else startActivity(t1);
        }

        return true;
    }

}