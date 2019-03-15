package com.ashishsonani.pr21;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.ashishsonani.pr21.DBHelper.DBHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ashishsonani.pr21.Adpater.passwordValutAdpater;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_item;
    SwipeRefreshLayout refreshData;

    LottieAnimationView empty_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_item = findViewById(R.id.add_item);
        refreshData = findViewById(R.id.refeshData);
        empty_view=findViewById(R.id.empty_view_animation);

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addItemActivity.class);
                startActivity(intent);
            }
        });

        updateData();
        refreshData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData.setRefreshing(false);
                try {

                    RecyclerView recyclerView = findViewById(R.id.list_PassowrdVault);

                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    ArrayList<ArrayList<String>> list = dbHelper.getALLPassword();
                    ArrayList<String> uname = list.get(0);
                    ArrayList<String> pass = list.get(1);
                    ArrayList<String> ids = list.get(2);
                    passwordValutAdpater adapter = new passwordValutAdpater(getApplicationContext(), uname, pass, ids);
                    recyclerView.setAdapter(adapter);
                    // recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    Toast.makeText(MainActivity.this, "Refresh Data", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    String error = e.toString();
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateData() {

        try {

            RecyclerView recyclerView = findViewById(R.id.list_PassowrdVault);

            DBHelper dbHelper = new DBHelper(getApplicationContext());
            int numRows=dbHelper.numberOfRows();
            if (numRows>0){
                empty_view.setVisibility(View.GONE);
            }else {
                empty_view.setVisibility(View.VISIBLE);
            }
            ArrayList<ArrayList<String>> list = dbHelper.getALLPassword();
            ArrayList<String> uname = list.get(0);
            ArrayList<String> pass = list.get(1);
            ArrayList<String> ids = list.get(2);
            passwordValutAdpater adapter = new passwordValutAdpater(getApplicationContext(), uname, pass, ids);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            String error = e.toString();
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
        }

    }
}

