package com.zolvik.aloebudandroidclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TITLE = "title";
    public static final String IMAGE = "image";
    private RecyclerView mRecyclerView;
    private CardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<CardItem> mCardItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCardList();
        buildRecyclerView();
    }

    public void createCardList(){
        mCardItemList = new ArrayList<>();
        mCardItemList.add(new CardItem(R.drawable.ic_school_black, "Hydrate", "never"));
        mCardItemList.add(new CardItem(R.drawable.ic_call_black, "People", "last week"));
        mCardItemList.add(new CardItem(R.drawable.ic_brightness_black, "Line 5", "last month"));
        mCardItemList.add(new CardItem(R.drawable.ic_school_black, "Rest", "today"));
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(this, 3);
        mAdapter = new CardAdapter(mCardItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getBaseContext(), CardDetails.class);
                intent.putExtra(TITLE, mCardItemList.get(position).getmLine1());
                intent.putExtra(IMAGE, mCardItemList.get(position).getmImage());
                startActivity(intent);
            }
        });
    }

    public void startSettings(View view) {
        Intent intent = new Intent(view.getContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void showHistory(View view) {
        Intent intent = new Intent(view.getContext(), History.class);
        startActivity(intent);
    }

    public void chooseCards(View view) {
        Intent intent = new Intent(view.getContext(), AvailableCardsActivity.class);
        startActivity(intent);
    }
}
