package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Bishnu Khanal
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity{

    //private Button reviewBtn;
    private ImageButton play, stop;
    private BottomNavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startService(new Intent(this, MusicService.class)); //sets the background music at the run time

        play = (ImageButton) findViewById(R.id.ibtnPlay);
        stop = (ImageButton) findViewById(R.id.ibtnStop);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MusicService.class)); //play the background music at the run time
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), MusicService.class)); //stop the background music
            }
        });

        navView = (BottomNavigationView) findViewById(R.id.homeNavView);
        navView.setSelectedItemId(R.id.navHome);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navProduct:
                        startActivity(new Intent(MainActivity.this, BookReview.class));
                        break;
                    case R.id.navCart:
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                        break;
                    case R.id.navDictionary:
                        startActivity(new Intent(MainActivity.this, DictionaryActivity.class));
                        break;
                }
                return true;
            }
        });
    }
}
