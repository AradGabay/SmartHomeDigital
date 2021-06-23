package com.example.smarthomebeta;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Constants {
    public static final Integer CURRENT_YEAR = 21;
    public static final Integer CONTACT_FIRST_REC_ID = 10000;
    public static final Integer PRIVATE_HOUSE_REC_ID = 100;
    public static final String PRIVATE_PROJECT_NAME = "prv";
    public static final String[] orderStatus = new String[]{"התקנה מושלמת","התקנה ללא ראוטר","התקנה ללא ראוטר וללא משתמש","התקנת מפסקים בלבד","התקנה חלקית","אחר"};

    //firebase references
    public static FirebaseAuth M_Auth = FirebaseAuth.getInstance();
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference REF_USERS=FBDB.getReference("Users");
    public static DatabaseReference REF_CONTACTS = FBDB.getReference("contacts");
    public static DatabaseReference REF_ITEMS = FBDB.getReference("items");
    public static DatabaseReference REF_ORDERS = FBDB.getReference("orders");

    public static DatabaseReference REF_PRIVATE = FBDB.getReference("projects/"+PRIVATE_PROJECT_NAME);
    public static DatabaseReference REF_PRIVATE_CURRENT_YEAR = REF_PRIVATE.child("Buildings").child(PRIVATE_PROJECT_NAME+CURRENT_YEAR.toString()).
                                    child("Apartments");


    // Storage reference
    public static StorageReference STORAGE = FirebaseStorage.getInstance().getReference();
    public static StorageReference REF_ITEM_IMAGES_SMALL = STORAGE.child("Images/itemImages/itemImagesSmall/");

}
