package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.smarthomebeta.Constants.FBDB;
import static com.example.smarthomebeta.Constants.M_Auth;
import static com.example.smarthomebeta.Constants.REF_CONTACTS;
import static com.example.smarthomebeta.Constants.REF_ITEMS;
import static com.example.smarthomebeta.Constants.REF_ORDERS;
import static com.example.smarthomebeta.Methods.dateAndTimeTostring;

public class newInstallation extends AppCompatActivity {
    private static InstallationCustomAdapter adapter;
    ArrayList<Integer> itemCatalogNum;
    ArrayList<InstallationDataModel> dataModels;
    HashMap<String, String> items=new HashMap<>();
    Intent t1;
    String UidHouse, UidContact, UidOrder, itemKey, clientName;
    Integer totalInOrder,totalInstalled, itemValue,itemValueInArray;
    TextView houseidTv, clientnameTv, tinTv, tioTv, tibTv;
    Map<String, String> itemsOrdered = new HashMap<String, String>();
    Map<String, String> itemsInstalled = new HashMap<String, String>();


    ListView lv;
    TextView orderTotalPrice;
    TextView orderTotalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation);

        lv = (ListView) findViewById(R.id.installationListview);
        orderTotalPrice = (TextView) findViewById(R.id.totalmoneytv);
        orderTotalItems = (TextView) findViewById(R.id.totalitemstv);
        tinTv = (TextView)findViewById(R.id.tinTv);
        tibTv = (TextView) findViewById(R.id.tibTv);
        tioTv = (TextView) findViewById(R.id.tioTv);
        houseidTv = (TextView) findViewById(R.id.houseIdTv);
        clientnameTv = (TextView) findViewById(R.id.clientNametv);
        dataModels = new ArrayList<>();
        itemCatalogNum = new ArrayList<Integer>();



        t1 = getIntent();
        UidOrder = t1.getStringExtra("UidOrder");
        Log.d("uidorder",UidOrder);
        UidHouse = t1.getStringExtra("UidHouse");
        houseidTv.setText(houseidTv.getText()+UidHouse);
        UidContact = t1.getStringExtra("UidContact");

        if(UidContact==null)clientnameTv.setText(clientnameTv.getText()+"אין לקוח מקושר");
        else{
            REF_CONTACTS.child(UidContact).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String contactName = snapshot.child("contactName").getValue().toString()+" "+snapshot.child("contactFamily").getValue().toString();
                    clientnameTv.setText(clientnameTv.getText()+UidContact+"-"+contactName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }










        REF_ORDERS.child(UidOrder).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("snapshot",snapshot.toString());
                for (DataSnapshot ds: snapshot.child("items").getChildren()){
                    itemsOrdered.put(ds.getKey(),ds.getValue().toString());

                }
                Log.d("itemsordered:",itemsOrdered.toString());

                for (DataSnapshot ds: snapshot.child("installs").getChildren()){
                    Log.d("items",ds.toString());
                    DataSnapshot items = ds.child("items");
                    Log.d("items2",items.toString());

                    for(DataSnapshot item: items.getChildren()){
                        Log.d("itemchildren",item.toString());
                        itemKey = item.getKey();
                        if(item.getValue()!=null)itemValue = Integer.parseInt(item.getValue().toString());
                        if(itemsInstalled.get(itemKey)!=null)itemValueInArray = Integer.parseInt(itemsInstalled.get(itemKey));
                        if (itemValueInArray != null) {
                            itemsInstalled.put(itemKey,(itemValueInArray+itemValue)+"");
                        } else itemsInstalled.put(itemKey,itemValue.toString());
                    }
                }
                Log.d("iteminstalled:",itemsInstalled.toString());


                REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("snapshot",snapshot.toString());
                        for(DataSnapshot data: snapshot.getChildren()){
                            itemCatalogNum.add(Integer.parseInt(data.getKey()));
                            String itemName = data.child("itemName").getValue().toString();
                            Integer itemPrice =  Long.valueOf((Long) data.child("itemPrice").getValue()).intValue();
                            Long itemDisplay =  Long.valueOf((Long) data.child("ItemDisplay").getValue());
                            Log.d("TAG", "itemDisplay: " + itemPrice);
                            if (itemDisplay == 1) {
                                totalInOrder=0;
                                totalInstalled=0;
                                if (itemsOrdered.get(data.getKey())!=null)totalInOrder = Integer.parseInt(itemsOrdered.get(data.getKey()));
                                if (itemsInstalled.get(data.getKey())!=null)totalInstalled = Integer.parseInt(itemsInstalled.get(data.getKey()));
                                Log.d("rrrr",totalInOrder+" "+totalInstalled+" "+data.getKey().toString());
                                dataModels.add(new InstallationDataModel(itemName+".png",itemName,totalInOrder, 0,totalInstalled ));
                            }
                        }

                        adapter= new InstallationCustomAdapter(dataModels,getApplicationContext());
                        lv.setAdapter(adapter);
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




    }

    public void calcTotalInstallation(View view) {
        Integer totalInstalledNow = 0;
        Integer totalInstalledBefore = 0;
        Integer totalInOrder = 0;
        String itemCatalog;
        Integer itemTotal;
        items.clear();
        for (int counter = 0; counter < dataModels.size(); counter++) {
            totalInstalledNow += dataModels.get(counter).getInstalledNow();
            totalInstalledBefore += dataModels.get(counter).getInstalledBefore();
            totalInOrder += dataModels.get(counter).getTotalInOrder();
            if (dataModels.get(counter).getInstalledNow()>0){
                itemCatalog=itemCatalogNum.get(counter).toString();
                items.put(itemCatalog,dataModels.get(counter).getInstalledNow().toString());


            }
        }
        Log.d("TAG", "itemsArray: " + items.toString());

        tioTv.setText(totalInOrder.toString());
        tibTv.setText(totalInstalledBefore.toString());
        tinTv.setText(totalInstalledNow.toString());

    }

    public void saveinstall(View view) {
        FirebaseUser user = M_Auth.getCurrentUser();
        String userid = user.getUid();
        calcTotalInstallation(view);
        String curDate = dateAndTimeTostring();

        Installation installData = new Installation(curDate,curDate,1,1,1,items,UidContact,UidContact,userid,userid);
        REF_ORDERS.child(UidOrder).child("installs").child(curDate).setValue(installData);
    }



}