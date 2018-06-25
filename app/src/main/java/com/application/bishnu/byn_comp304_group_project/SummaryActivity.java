package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Yayun Yang
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {
    TextView summary;
    SharedPreferences sharedPreferences;
    String name, total,payment,message;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        btnSend=(Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SummaryActivity.this,SendSMS.class));
            }
        });

        summary = (TextView) findViewById(R.id.tvSummary);
        btnSend = (Button) findViewById(R.id.btnSend);
        sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
        name = sharedPreferences.getString("name",null);
        total = sharedPreferences.getString("total",null);
        payment = sharedPreferences.getString("payMethod",null);

        //format order summary information
        message = String.format("Hello %s!%nThank you for placing order with us.Your order total is %s%nPayment method is %s%nWe will comfirm with you once your order is shipped.",
                name,total,payment);
        summary.setText(message);


    }

   // protected void sendSmsMessage() {
      //  if (ContextCompat.checkSelfPermission(this,
            //    Manifest.permission.SEND_SMS)
            //    != PackageManager.PERMISSION_GRANTED) {
           // if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                //    Manifest.permission.SEND_SMS)) {
           // } else {
            //    ActivityCompat.requestPermissions(this,
              //          new String[]{Manifest.permission.SEND_SMS},
              //          SEND_SMS_PERMISSION_CODE);
           // }
       // }
   // }

   // @Override
    //public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
       // switch (requestCode) {
            //case SEND_SMS_PERMISSION_CODE: {
              //  if (grantResults.length > 0
                     //   && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   // SmsManager smsManager = SmsManager.getDefault();
                   // smsManager.sendTextMessage("10000", null, message, null, null);
                   // Toast.makeText(getApplicationContext(), "SMS sent.",
                    //        Toast.LENGTH_LONG).show();
                //} else {
                  //  Toast.makeText(getApplicationContext(),
                       //     "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                   // return;
               // }
           // }
       // }

   // }
}
