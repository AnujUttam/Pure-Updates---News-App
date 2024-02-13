package com.example.pureupdate_newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView text_title, text_source;
    ImageView img_Headline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title = itemView.findViewById(R.id.txt_title);
        text_source = itemView.findViewById(R.id.txt_source);
        img_Headline = itemView.findViewById(R.id.news_img);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
