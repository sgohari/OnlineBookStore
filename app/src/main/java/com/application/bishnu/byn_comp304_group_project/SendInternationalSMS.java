package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Syed Nasir Gohary
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 * Designed and styled the XML activities
 */
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendInternationalSMS extends AppCompatActivity {

    EditText api,_sender,_message,phone;
    Button btnSendInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_international_sms);

        api=(EditText)findViewById(R.id.txtAPI);
        _sender=(EditText)findViewById(R.id.txtSender);
        _message=(EditText)findViewById(R.id.txtMessage);
        phone=(EditText)findViewById(R.id.txtPhone);
        api.setText("7EUkgx57ivo-BBfQ0GJ1Ytobzmqjr3pdjQb1IxFcuh");

        btnSendInt=(Button)findViewById(R.id.btnSendIntMSG);
        btnSendInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // Construct data
                    String apiKey = "apikey=" +api.getText().toString();

                    String message = "&message=" + _message.getText().toString();
                    String sender = "&sender=" + _sender.getText().toString();
                    String numbers = "&numbers=" + phone.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        //stringBuffer.append(line);

                        Toast.makeText(getApplicationContext(),"Message has been sent "+line,Toast.LENGTH_LONG).show();
                    }
                    rd.close();

                    //return stringBuffer.toString();
                } catch (Exception e) {
                    //System.out.println("Error SMS "+e);
                    // return "Error "+e;
                    Toast.makeText(getApplicationContext(),"Error message "+e,Toast.LENGTH_LONG).show();
                }
            }
        });
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
