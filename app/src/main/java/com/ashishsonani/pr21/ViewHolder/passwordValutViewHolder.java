package com.ashishsonani.pr21.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ashishsonani.pr21.R;
import com.google.android.material.textfield.TextInputEditText;

public class passwordValutViewHolder extends RecyclerView.ViewHolder {
    public TextInputEditText password;
    public TextView username;

    public passwordValutViewHolder(View item_view){
        super(item_view);
        password=item_view.findViewById(R.id.view_password);
        username=item_view.findViewById(R.id.userName);
    }

}
