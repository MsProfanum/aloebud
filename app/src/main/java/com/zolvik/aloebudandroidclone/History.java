package com.zolvik.aloebudandroidclone;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mListView = (ListView) findViewById(R.id.list_view);
        mDatabaseHelper = new DatabaseHelper(this);
        
        addToListView();
    }

    private void addToListView() {
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = mDatabaseHelper.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }

                if(backPressedTime + 2000 > System.currentTimeMillis()){
                    mDatabaseHelper.deleteCard(itemID, name);
                    Toast.makeText(getBaseContext(), "Activity deleted successfully", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(getBaseContext(), "Press back again to delete", Toast.LENGTH_SHORT).show();
                }

                backPressedTime = System.currentTimeMillis();

            }
        });
    }

    public void backToMain(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }
}
