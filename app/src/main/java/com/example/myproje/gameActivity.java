package com.example.myproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Button p6 = findViewById(R.id.button_game1);
        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(gameActivity.this, game1activity.class);
                startActivity(intent);
            }
        });

        Button p7 = findViewById(R.id.button_game2);
        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(gameActivity.this, game2activity.class);
                startActivity(intent);
            }
        });

        Button p8 = findViewById(R.id.button_game3);
        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(gameActivity.this, game3activity.class);
                startActivity(intent);
            }
        });


    }




}