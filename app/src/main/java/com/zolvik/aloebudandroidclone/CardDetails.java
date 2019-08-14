package com.zolvik.aloebudandroidclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardDetails extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        mDatabaseHelper = new DatabaseHelper(this);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        String title = getIntent().getStringExtra(MainActivity.TITLE);
        String description;
        int image = getIntent().getIntExtra(MainActivity.IMAGE, 0);

        TextView mTitle = (TextView) findViewById(R.id.text_view_title);
        ImageView mImage = (ImageView) findViewById(R.id.image_view_cardrev);
        TextView mDescription = (TextView) findViewById(R.id.text_view_description);

        mImage.setImageResource(image);
        mTitle.setText(title);

        description = setDescription(title);
        mDescription.setText(description);
    }

    private String setDescription(String title) {
        switch (title){
            case("Hydrate"):
                return "Water helps us grow, remember to water plants and yourself.";
            case("Rest"):
                return "The best things we can do to ourselves is a good night's sleep.";
            case("People"):
                return "Call someone who makes you smile.";
            case("Stimulate"):
                return "What have you been reading lately?";
            case("Health"):
                return "Taking medication is one of the best self-love acts.";
            default:
                return "Do what you love";
        }
    }

    public void saveToDb(View view) {
        Toast.makeText(view.getContext(), "Proud of you! Keep going :)", Toast.LENGTH_SHORT).show();
        mDatabaseHelper.addData(getIntent().getStringExtra(MainActivity.TITLE));
    }
}
