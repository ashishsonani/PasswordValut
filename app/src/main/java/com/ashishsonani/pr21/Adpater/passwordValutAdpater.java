package com.ashishsonani.pr21.Adpater;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishsonani.pr21.R;
import com.ashishsonani.pr21.ViewHolder.passwordValutViewHolder;
import com.ashishsonani.pr21.editItemActivity;

import java.util.ArrayList;

public class passwordValutAdpater extends RecyclerView.Adapter<passwordValutViewHolder> {

    Context context;
    ArrayList<String> uname;
    ArrayList<String> pass;
    ArrayList<String> ids;

    public passwordValutAdpater(Context context, ArrayList<String> uname, ArrayList<String> pass, ArrayList<String> ids) {
        this.context = context;
        this.uname = uname;
        this.pass = pass;
        this.ids = ids;
    }

    @NonNull
    @Override
    public passwordValutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new passwordValutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull passwordValutViewHolder holder, final int position) {
        holder.username.setText(uname.get(position));
        holder.password.setText(pass.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, editItemActivity.class);
                intent.putExtra("id", ids.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return uname.size();

    }
}
