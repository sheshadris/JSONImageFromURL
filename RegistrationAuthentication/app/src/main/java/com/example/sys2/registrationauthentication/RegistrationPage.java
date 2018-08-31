package com.example.sys2.registrationauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener{
    String username,pass,mail,phonenumber;
    TextView txtname,txtpass,txtemail,txtnumber;
    EditText edtname,edtpass,edtmail,edtnumber;
    Button btnsignup;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        txtname = findViewById(R.id.txt_username);
        txtpass = findViewById(R.id.txt_password);
        txtemail = findViewById(R.id.txt_email);
        txtnumber = findViewById(R.id.txt_mobilenumber);
        edtname = findViewById(R.id.edt_username);
        edtpass = findViewById(R.id.edt_password);
        edtmail = findViewById(R.id.edt_email);
        edtnumber = findViewById(R.id.edt_mobile);
        btnsignup = findViewById(R.id.btn_reg);
        btnsignup.setOnClickListener(this);
        databaseHandler = new DatabaseHandler(this);
    }
       @Override
    public void onClick(View view) {
        if (validate()) {
            username = edtname.getText().toString();
            pass = edtpass.getText().toString();
            mail = edtmail.getText().toString();
            phonenumber = edtnumber.getText().toString();

            databaseHandler.insert(username, pass, mail, phonenumber);
            Toast.makeText(getApplicationContext(), "Done Registration", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Unauthenticated",Toast.LENGTH_SHORT).show();
        }
       }
   public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String username = edtname.getText().toString();
        String password = (edtpass.getText().toString());
        String mail = edtmail.getText().toString();
        String mobilenumber = edtnumber.getText().toString();
        //Handling validation for UserName field
        if (username.isEmpty()) {
            valid = false;
            edtname.setError("Please enter valid username!");
        } else {
            if (username.length() > 5) {
                valid = true;
                edtname.setError(null);
            } else {
                valid = false;
                edtname.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            valid = false;
            edtmail.setError("Please enter valid email!");
        } else {
            valid = true;
            edtmail.setError(null);
        }

        //Handling validation for Password field
        if (password.isEmpty()) {
            valid = false;
            edtpass.setError("Please enter valid password!");
        } else {
            if (password.length() > 5) {
                valid = true;
                edtpass.setError(null);
            } else {
                valid = false;
                edtpass.setError("Password is to short!");
            }
        }
        if (mobilenumber.isEmpty())
        {
            valid=false;
            edtnumber.setError("Mobile Number field is empty");
        }
        else    {
            if (mobilenumber.length()>10)
            {
                valid = false;
                edtnumber.setError("Please enter 10 digit mobile number");
            }
            else {
                valid = true;
                edtnumber.setError(null);
            }
        }


        return valid;
    }
}
