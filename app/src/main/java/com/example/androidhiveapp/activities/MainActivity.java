package com.example.androidhiveapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidhiveapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnViewMovies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        btnViewMovies = (Button) findViewById(R.id.btnViewMovies);

        // view products click event
        btnViewMovies.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Top rated Movies  Activity
                Intent i = new Intent(getApplicationContext(), TopRatedMoviesActivity.class);
                startActivity(i);

            }
        });

    }
}
