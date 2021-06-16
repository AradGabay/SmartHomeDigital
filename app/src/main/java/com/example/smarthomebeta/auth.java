package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import static com.example.smarthomebeta.Constants.M_Auth;
import static com.example.smarthomebeta.Constants.REF_USERS;
import static com.example.smarthomebeta.Methods.isEmailValid;
import static com.example.smarthomebeta.Methods.isValidPassword;


public class auth extends AppCompatActivity {
    Boolean registered, stayConnected;
    TextView regorlogtxt, reglogoption;
    Button regorlogbtn;
    EditText usernameedt, fullnameedt,phonenumedt, emailedt, passwordedt;
    User dbuser;
    String uid;
    CheckBox cbStayConnected;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        regorlogtxt = (TextView)findViewById(R.id.regorlogtxt);
        reglogoption = (TextView)findViewById(R.id.reglogoption);
        passwordedt = (EditText)findViewById(R.id.passwordedt);
        emailedt = (EditText)findViewById(R.id.emailedt);
        fullnameedt = (EditText)findViewById(R.id.fullnameedt);
        phonenumedt = (EditText)findViewById(R.id.phonenumedt);
        regorlogbtn = (Button)findViewById(R.id.regorlogbtn);
        cbStayConnected = (CheckBox)findViewById(R.id.cbStayConnected);

        stayConnected = false;
        registered = true;
        regoption();

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        Boolean isChecked=settings.getBoolean("stayConnect",false);
        Intent si = new Intent(auth.this,loginok.class);
        if (M_Auth.getCurrentUser()!=null && isChecked) {
            stayConnected=true;
            si.putExtra("newuser",false);
            startActivity(si);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (stayConnected) finish();
    }

    private void regoption() {
        SpannableString ss = new SpannableString("אינך רשום עוד במערכת? הירשם כאן!");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                regorlogtxt.setText("הרשמה");
                fullnameedt.setVisibility(View.VISIBLE);
                phonenumedt.setVisibility(View.VISIBLE);
                regorlogbtn.setText("הירשם");
                registered=false;
                logoption();
            }
        };
        ss.setSpan(span, 22, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        reglogoption.setText(ss);
        reglogoption.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void logoption() {
        SpannableString ss = new SpannableString("כבר רשום במערכת? היכנס כאן!");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                regorlogtxt.setText("התחברות");
                fullnameedt.setVisibility(View.GONE);
                phonenumedt.setVisibility(View.GONE);
                regorlogbtn.setText("התחבר");
                registered=true;
                regoption();
            }
        };
        ss.setSpan(span, 17, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        reglogoption.setText(ss);
        reglogoption.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void logorreg(View view) {
        String email = emailedt.getText().toString();
        String password = passwordedt.getText().toString();


        if (registered) {

                final ProgressDialog pd = ProgressDialog.show(this, "Login", "Connecting...", true);
                M_Auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pd.dismiss();
                                if (task.isSuccessful()) {
                                    SharedPreferences settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = settings.edit();
                                    editor.putBoolean("stayConnect", cbStayConnected.isChecked());
                                    editor.commit();
                                    Log.d("MainActivity", "signinUserWithEmail:success");
                                    Toast.makeText(auth.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent si = new Intent(auth.this, loginok.class);
                                    startActivity(si);
                                } else {
                                    Log.d("MainActivity", "signinUserWithEmail:fail");
                                    Toast.makeText(auth.this, "e-mail or password are wrong!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
        }
             else{
            if (!isEmailValid(email))
                Toast.makeText(this, "אימייל לא חוקי", Toast.LENGTH_SHORT).show();
            else if (!isValidPassword(password))Toast.makeText(this, "סיסמה חייבת לכלול 8 תווים לפחות בינהן לפחות אות אחת באנגלית, ספרה אחת, וסימן מיוחד אחד.", Toast.LENGTH_LONG).show();
            else {
                String fullname = fullnameedt.getText().toString();
                String phone = phonenumedt.getText().toString();

                final ProgressDialog pd = ProgressDialog.show(this, "Register", "Registering...", true);
                M_Auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pd.dismiss();
                                if (task.isSuccessful()) {
                                    SharedPreferences settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = settings.edit();
                                    editor.putBoolean("stayConnect", cbStayConnected.isChecked());
                                    editor.commit();
                                    Log.d("MainActivity", "createUserWithEmail:success");
                                    FirebaseUser user = M_Auth.getCurrentUser();
                                    uid = user.getUid();
                                    dbuser = new User(uid, fullname, email, password, phone);
                                    REF_USERS.child(uid).setValue(dbuser);
                                    Toast.makeText(auth.this, "Successful registration", Toast.LENGTH_SHORT).show();
                                    Intent si = new Intent(auth.this, loginok.class);
                                    startActivity(si);
                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                        Toast.makeText(auth.this, "User with e-mail already exist!", Toast.LENGTH_SHORT).show();
                                    else {
                                        Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(auth.this, "User create failed.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {


        menu.add(0,1,240,"קרדיטים");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==1){
            startActivity(new Intent(this,credits.class));
        }

        return true;
    }
}