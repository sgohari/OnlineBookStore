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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

public class PaymentActivity extends AppCompatActivity {
    EditText cardNumber, holderName, expiryDate, cvv;
    RadioGroup radioGroup;
    Button checkout;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        radioGroup = (RadioGroup) findViewById(R.id.rgPayment);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        checkout = (Button) findViewById(R.id.btnPaymentCheckout);
        cardNumber =(EditText) findViewById(R.id.etCardNumber);
        holderName = (EditText)findViewById(R.id.etCardHolderName);
        expiryDate = (EditText)findViewById(R.id.etEpiry);
        cvv = (EditText)findViewById(R.id.etCVV);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                SharedPreferences sharedPreferences = PaymentActivity.this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(checkedId == R.id.rbtnCredit){
                    tableLayout.setVisibility(View.VISIBLE);
                    checkout.setText("Check out");
                /*    payment.setCardHolderName(holderName.getText().toString());
                    payment.setCardNumber(cardNumber.getText().toString());
                    payment.setExpiryDate(expiryDate.getText().toString());
                    payment.setCvvNumber(cvv.getText().toString());*/
                    editor.putString("payMethod","Credit Card");

                }else if(checkedId == R.id.rbtnPaypal){
                    tableLayout.setVisibility(View.INVISIBLE);
                    checkout.setText("Pay on PayPal Website");
                    editor.putString("payMethod","PayPal");
                }
                editor.commit();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnPaymentCheckout){
                    startActivity(new Intent(PaymentActivity.this,SummaryActivity.class));
                }
            }
        });
    }
}
