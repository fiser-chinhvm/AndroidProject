package com.example.hoang.bookmovietickets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyListTicketsActivity extends AppCompatActivity {

    private RecyclerView myListTickets;
    private RVAdapter adapter;
    private List<MovieModel> arr;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_tickets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myListTickets = (RecyclerView) findViewById(R.id.myListTickets);
        dbHelper = new DBHelper(this);
        arr = dbHelper.getListBookedMovie(1);
        adapter = new RVAdapter(this, arr);
        myListTickets.setAdapter(adapter);
        myListTickets.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myListTickets.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        arr.clear();
        List<MovieModel> list = new ArrayList<MovieModel>();
        list = dbHelper.getListBookedMovie(1);
        arr.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
