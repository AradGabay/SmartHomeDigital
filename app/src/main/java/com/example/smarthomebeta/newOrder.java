package com.example.smarthomebeta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.smarthomebeta.Constants.M_Auth;
import static com.example.smarthomebeta.Constants.REF_ORDERS;
import static com.example.smarthomebeta.Methods.dateAndTimeTostring;

public class newOrder extends AppCompatActivity {
    private static OrderCustomAdapter adapter;
    ArrayList<Integer> itemCatalogNum;
    ArrayList<OrderDataModel> dataModels;
    HashMap<String, String> items=new HashMap<>();
    Intent t1;
    String UidHouse, UidContact;
    TextView houseidTv, clientnameTv;

    ListView lv;
    TextView orderTotalPrice;
    TextView orderTotalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        lv = (ListView) findViewById(R.id.orderListview);
        orderTotalPrice = (TextView) findViewById(R.id.totalmoneytv);
        orderTotalItems = (TextView) findViewById(R.id.totalitemstv);
        houseidTv = (TextView) findViewById(R.id.houseidtv);
        clientnameTv = (TextView) findViewById(R.id.clientnametv);
        dataModels = new ArrayList<>();
        itemCatalogNum = new ArrayList<Integer>();

        t1 = getIntent();
        UidHouse = t1.getStringExtra("uidhouse");
        houseidTv.setText(houseidTv.getText()+UidHouse);
        UidContact = t1.getStringExtra("uidContact");
        if(UidContact!=null) clientnameTv.setText(clientnameTv.getText()+UidContact);
        else clientnameTv.setText(clientnameTv.getText()+" אין לקוח מקושר");





        Constants.REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    itemCatalogNum.add(Integer.parseInt(data.getKey()));
                    String itemName = data.child("itemName").getValue().toString();
                    Integer itemPrice =  Long.valueOf((Long) data.child("itemPrice").getValue()).intValue();
                    Long itemDisplay =  Long.valueOf((Long) data.child("ItemDisplay").getValue());
                    Log.d("TAG", "itemDisplay: " + itemPrice);
                    if (itemDisplay == 1) {
                       dataModels.add(new OrderDataModel(itemName,itemName+".png",0, itemPrice,0));
                    }
                }
                adapter= new OrderCustomAdapter(dataModels,getApplicationContext());
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void calcTotalOrder(View view) {
            Integer totalPrice = 0;
            Integer totalItems = 0;
            String itemCatalog;
            Integer itemTotal;
            items.clear();
            for (int counter = 0; counter < dataModels.size(); counter++) {
                itemTotal = dataModels.get(counter).getTotalItem();
                totalPrice = totalPrice + dataModels.get(counter).getItemPrice()*itemTotal;
                totalItems = totalItems+itemTotal;
                if (itemTotal>0){
                    itemCatalog=itemCatalogNum.get(counter).toString();
                    items.put(itemCatalog,itemTotal.toString());
                    Log.d("TAG", "catnum: " + itemCatalog.toString()+"total: " + itemTotal.toString());

                }
            }
        Log.d("TAG", "itemsArray: " + items.toString());

        orderTotalPrice.setText(totalPrice.toString());
        orderTotalItems.setText(totalItems.toString());

    };

    public void saveorder(View view) {

        FirebaseUser user = M_Auth.getCurrentUser();
        String userid = user.getUid();
        calcTotalOrder(view);
        String curDate = dateAndTimeTostring();

        Order orderdata = new Order(curDate,"1", userid, UidHouse, orderTotalItems.getText().toString(),orderTotalPrice.getText().toString(),
                "1","Null","Null",curDate,items);
        Log.d("OrderData:",orderdata.toString());
        String orderUid = REF_ORDERS.push().getKey();
        REF_ORDERS.child(orderUid).setValue(orderdata);

        Constants.REF_PRIVATE_CURRENT_YEAR.child(UidHouse).child("Orders").child(curDate).setValue(orderUid);
    }
}

