package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Yayun Yang
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmActivity extends AppCompatActivity {
    BottomNavigationView navView;
    Button confirm;
    TextView subtotal, tax, total;
    EditText name, address,postalCode,phone;
    double amount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        name = (EditText)findViewById(R.id.etShipName);
        address= (EditText)findViewById(R.id.etShipAddress);
        postalCode = (EditText)findViewById(R.id.etShipPostCode);
        phone = (EditText)findViewById(R.id.etShipPhone);

        //set up BottomNavigationView
        navView = (BottomNavigationView) findViewById(R.id.paymentNavView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navHome:
                        startActivity(new Intent(ConfirmActivity.this, MainActivity.class));
                        break;
                    case R.id.navProduct:
                        startActivity(new Intent(ConfirmActivity.this, BookReview.class));
                        break;
                    case R.id.navCart:
                        startActivity(new Intent(ConfirmActivity.this, CartActivity.class));
                        break;
                    case R.id.navDictionary:
                        startActivity(new Intent(ConfirmActivity.this, DictionaryActivity.class));
                        break;
                }
                return true;
            }
        });

        subtotal = (TextView) findViewById(R.id.tvOrderSubtotal);
        tax = (TextView)findViewById(R.id.tvOrderTax);
        total = (TextView)findViewById(R.id.tvOrderTotal);
        for (Books b:CartActivity.cartList) {
            String s = b.getPrice().substring(8);//get book price
            amount += Double.parseDouble(s); //parse price from string to double and add the price to total amount
        }
        //calculate order price from static CartActivity.cartList
        subtotal.setText(String.format("$%.2f",amount));
        tax.setText(String.format("$%.2f",amount*0.13));
        total.setText(String.format("$%.2f",amount*1.13));

        confirm = (Button)findViewById(R.id.btnComfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnComfirm){
                    if(name.getText().toString().matches("")  || address.getText().toString().matches("")|| postalCode.getText().toString().matches("") || phone.getText().toString().matches("")){
                        Toast.makeText(ConfirmActivity.this, "Please fill out all the fileds above.",Toast.LENGTH_SHORT).show();
                    }else if(!postalCode.getText().toString().toUpperCase().matches("^[ABCEGHJ-NPRSTVXY][0-9][ABCEGHJ-NPRSTV-Z] [0-9][ABCEGHJ-NPRSTV-Z][0-9]$")){
                        Toast.makeText(ConfirmActivity.this, "Please enter valid postal code. \nFormat: A0A 1B1",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        SharedPreferences sharedPreferences = ConfirmActivity.this.getSharedPreferences("myPref",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",name.getText().toString());
                        editor.putString("total", total.getText().toString());
                        editor.putString("phone",phone.getText().toString());
                        editor.commit();
                        startActivity(new Intent(ConfirmActivity.this, PaymentActivity.class));
                    }
                }
            }
        });
    }
}
