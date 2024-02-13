package com.example.pureupdate_newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pureupdate_newsapp.Models.NewsApiResponse;
import com.example.pureupdate_newsapp.Models.NewsHeadline;
import com.example.pureupdate_newsapp.Models.OnFetchDataListener;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
RecyclerView recyclerView;
CustomAdapter customAdapter;
ShimmerFrameLayout shimmerFrameLayout;
Button business, entertainment, general, health, science, sports, technology;
ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmerAnimation();

        business = findViewById(R.id.btn_1);
        entertainment = findViewById(R.id.btn_2);
        general = findViewById(R.id.btn_3);
        health = findViewById(R.id.btn_4);
        science = findViewById(R.id.btn_5);
        sports = findViewById(R.id.btn_6);
        technology = findViewById(R.id.btn_7);

        business.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        general.setOnClickListener(this);
        health.setOnClickListener(this);
        science.setOnClickListener(this);
        sports.setOnClickListener(this);
        technology.setOnClickListener(this);

        dialog = new ProgressDialog(this);


        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener,"general",null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadline> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "Nothing Found", Toast.LENGTH_SHORT).show();
            }
            else {
                showNews(list);
                dialog.dismiss();
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "ERROR OCCURS", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadline> list) {
        recyclerView = findViewById(R.id.mainRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        customAdapter = new CustomAdapter(this,list,this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadline headline) {
        startActivity(new Intent(MainActivity.this,NewsDetailsActivity.class)
                .putExtra("data",headline));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();

        dialog.show();
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener,category,null);

    }
}