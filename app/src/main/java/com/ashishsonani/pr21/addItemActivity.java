package com.ashishsonani.pr21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ashishsonani.pr21.DBHelper.DBHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class addItemActivity extends AppCompatActivity {

    MaterialButton btn_submit;
    TextInputEditText username, passowrd;
    String uname, pass, toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        btn_submit = findViewById(R.id.btn_submit);
        username = findViewById(R.id.add_username);
        passowrd = findViewById(R.id.add_password);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("") || passowrd.getText().toString().equals("")) {
                    username.setError("Value can't be Empty");
                    passowrd.setError("Value can't be Empty");
                } else {
                    uname = username.getText().toString();
                    pass = passowrd.getText().toString();
                    DBHelper helper = new DBHelper(getApplicationContext());
                    helper.insertPassowrdVault(uname, pass);
                    Toast.makeText(addItemActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addItemActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}
