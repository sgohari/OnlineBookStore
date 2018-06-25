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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookReview extends AppCompatActivity {
    BottomNavigationView navView;
    List<Books> booksList;
    private ListView list;
    // private Button viewMoreBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_review);

        booksList = new ArrayList<>(); //referencing to arrayList

        list = (ListView) findViewById(R.id.listView);
        booksList.add(new Books(R.drawable.alias, "Alias Grace", "Authored by: Margaret Atwood", "Giller prize winning, historical novel", "Price: $13.95"));
        booksList.add(new Books(R.drawable.braving, "Braving the Wilderness", "Authored by: Brene Brown", "The quest of true belonging and the courage to stand alone", "Price: $22.20"));
        booksList.add(new Books(R.drawable.camino_island, "Camino Island", "Authored by: John Grisham", "A crime fiction thriller novel", "Price: $7.20"));
        booksList.add(new Books(R.drawable.canada, "Canada", "Authored by: Robert Bateman", "New York Times best seller, a jurney across seven regions of canada", "Price: $45.05"));
        booksList.add(new Books(R.drawable.darker, "Darker", "Authored by: E L James", "A deeper a                nd darker take on love story", "Price: $17.59"));
        booksList.add(new Books(R.drawable.glass_castle, "Glass Castle", "Authored by: Jeannette Castle", "Ever best selling listed in New York times- a true family story", "Price: $17.24"));

        CustomListAdaptor adaptor = new CustomListAdaptor(this, R.layout.my_list_items, booksList);
        list.setAdapter(adaptor);

        navView = (BottomNavigationView) findViewById(R.id.bookNavView);

        navView.setSelectedItemId(R.id.navProduct);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navHome:
                        startActivity(new Intent(BookReview.this, MainActivity.class));
                        break;
                    case R.id.navCart:
                        startActivity(new Intent(BookReview.this, CartActivity.class));
                        break;
                    case R.id.navDictionary:
                        startActivity(new Intent(BookReview.this, DictionaryActivity.class));
                        break;
                }
                return true;
            }
        });
    }
    public void onClick(View view)
    {
        startActivity(new Intent(BookReview.this, ViewWebview.class));
    }

}
