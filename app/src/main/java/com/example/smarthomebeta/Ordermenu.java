package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.smarthomebeta.Constants.CURRENT_YEAR;
import static com.example.smarthomebeta.Constants.FBDB;
import static com.example.smarthomebeta.Constants.PRIVATE_PROJECT_NAME;
import static com.example.smarthomebeta.Constants.REF_CONTACTS;
import static com.example.smarthomebeta.Constants.REF_ORDERS;
import static com.example.smarthomebeta.Constants.REF_PRIVATE;
import static com.example.smarthomebeta.Constants.REF_PRIVATE_CURRENT_YEAR;

public class Ordermenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView ordersLv;
    ArrayList<String> ordersTitle;
    HashMap<Integer,String> orders;
    ArrayAdapter<String> adp;
    String UidContact, UidHouse, contactName;
    Intent t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordermenu);

        ordersLv = (ListView) findViewById(R.id.ordersLv);
        ordersTitle = new ArrayList<String>();
        adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ordersTitle);
        orders = new HashMap<Integer, String>();
        ordersLv.setOnItemClickListener(this);

        REF_ORDERS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    orders.put(orders.size(),ds.getKey());
                    UidHouse = ds.child("uidHouse").getValue().toString();
                    REF_PRIVATE_CURRENT_YEAR.child(UidHouse).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //Log.d("houseContacts",snapshot.child("houseContacts").getValue().toString());
                            if(snapshot.child("houseContacts").getValue()!=null) UidContact = snapshot.child("houseContacts").getValue().toString();
                            REF_CONTACTS.child(UidContact).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Log.d("contactName",snapshot.child("contactName").getValue()+ " "+snapshot.child("contactFamily").getValue()+ UidHouse);
                                    contactName = snapshot.child("contactName").getValue()+ " "+snapshot.child("contactFamily").getValue();
                                    ordersTitle.add(contactName+" - "+ds.child("orderDate").getValue().toString());
                                    ordersTitle.toString();
                                    adp.notifyDataSetChanged();
                                    ordersLv.setAdapter(adp);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Log.d("orderrow",ds.child("uidHouse").getValue().toString()+" "+ds.child("orderDate").getValue().toString());



                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String orderTitle = ordersTitle.get(position);
        String contactName = orderTitle.substring(0,orderTitle.indexOf('-'));
        String UidOrder = orders.get(position);

        REF_ORDERS.child(UidOrder).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UidHouse = snapshot.child("uidHouse").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        REF_PRIVATE_CURRENT_YEAR.child(UidHouse).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("houseContacts").getValue()!= null)
                UidContact = snapshot.child("houseContacts").getValue().toString();
                Log.d("orderinfo",orderTitle +" "+UidHouse+" "+UidContact+" "+UidOrder);
                t1 = new Intent(getApplicationContext(),newInstallation.class);
                t1.putExtra("UidHouse", UidHouse);
                t1.putExtra("UidOrder", UidOrder);
                t1.putExtra("UidContact",UidContact);
                t1.putExtra("contactName",contactName);

                startActivity(t1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }

}