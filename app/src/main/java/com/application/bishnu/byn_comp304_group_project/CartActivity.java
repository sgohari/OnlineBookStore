package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Yayun Yang
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ListView listView;
    BottomNavigationView navView;
    Button checkout;
    public static List<Books> cartList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = (ListView)findViewById(R.id.lvCart);
        CartItemListAdapter adapter = new CartItemListAdapter(this ,R.layout.cart_list_items, cartList);
        listView.setAdapter(adapter);

        navView = (BottomNavigationView)findViewById(R.id.cartNavView);
        navView.setSelectedItemId(R.id.navCart);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navProduct:
                        startActivity(new Intent(CartActivity.this, BookReview.class));
                        break;
                    case R.id.navHome:
                        startActivity(new Intent(CartActivity.this, MainActivity.class));
                        break;
                    case R.id.navDictionary:
                        startActivity(new Intent(CartActivity.this, DictionaryActivity.class));
                        break;
                }
                return true;
            }
        });
//cartList != null || !cartList.isEmpty()
        checkout = (Button) findViewById(R.id.btnCheckout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnCheckout){
                    if(cartList.size() > 0){
                        startActivity(new Intent(CartActivity.this, ConfirmActivity.class));
                    }else{
                        Toast.makeText(CartActivity.this,"Please select a book first.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
