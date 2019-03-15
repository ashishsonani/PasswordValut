package com.ashishsonani.pr21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ashishsonani.pr21.Adpater.passwordValutAdpater;
import com.ashishsonani.pr21.DBHelper.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;

public class editItemActivity extends AppCompatActivity {

    FloatingActionButton updatebtn, deletelbtn;
    TextInputEditText uname, pass;
    String username, password;
    int ids;
    AlertDialog.Builder builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        updatebtn = findViewById(R.id.updateSave);
        deletelbtn = findViewById(R.id.deleteItem);
        uname = findViewById(R.id.edit_username);
        pass = findViewById(R.id.edit_password);

        builder1 = new AlertDialog.Builder(editItemActivity.this);
        ids = Integer.parseInt(getIntent().getStringExtra("id"));
        Log.d("ids_error", Integer.toString(ids));
        try {
            DBHelper helper = new DBHelper(getApplicationContext());
            Cursor res = helper.getData(ids);
            res.moveToFirst();
            username = res.getString(res.getColumnIndex("uname"));
            password = res.getString(res.getColumnIndex("pass"));

            uname.setText(username);
            pass.setText(password);
            //alertDailog

            ids = Integer.parseInt(getIntent().getStringExtra("id"));
            Toast.makeText(this, Integer.toString(ids), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("ids", e.toString());
        }
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper = new DBHelper(getApplicationContext());
                String u1 = uname.getText().toString();
                String p1 = pass.getText().toString();
                // Toast.makeText(getApplicationContext(), ids+"+"+u1+"+"+p1, Toast.LENGTH_SHORT).show();
                helper.updatePasswordVault(ids, u1, p1);
                Intent intent = new Intent(editItemActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(editItemActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();

            }
        });

        deletelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder1.setMessage("Do you want to Delete ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                DBHelper helper = new DBHelper(getApplicationContext());
                                helper.deletePassworsVault(ids);
                                Intent intent = new Intent(editItemActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(editItemActivity.this, "Data Deleted ", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    }

}
