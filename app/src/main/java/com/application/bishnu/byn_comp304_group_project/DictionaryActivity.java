package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Bishnu Khanal
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DictionaryActivity extends AppCompatActivity implements View.OnClickListener{

    final private int REQUEST_INTERNET = 123;

    EditText myText;
    Button search;
    TextView text;

    BottomNavigationView navView;
    private InputStream OpenHttpConnection(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    private String WordDefinition(String word) {
        InputStream in = null;
        String strDefinition = " ";
        try {
            in = OpenHttpConnection("http://services.aonaware.com" +
                    "/DictService/DictService.asmx/Define?word=" + word);
            Document doc = null;
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            try {
                db = dbf.newDocumentBuilder();
                doc = db.parse(in);
            } catch (ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            doc.getDocumentElement().normalize();

            //---retrieve all the <Definition> elements---
            NodeList definitionElements =
                    doc.getElementsByTagName("Definition");

            //---iterate through each <Definition> elements---
            for (int i = 0; i < definitionElements.getLength(); i++) {
                Node itemNode = definitionElements.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    //---convert the Definition node into an Element---
                    Element definitionElement = (Element) itemNode;
                    //---get all the <WordDefinition> elements under
                    // the <Definition> element---
                    NodeList wordDefinitionElements =
                            (definitionElement).getElementsByTagName(
                                    "WordDefinition");
                    strDefinition = "";
                    //---iterate through each <WordDefinition> elements---
                    for (int j = 0; j < wordDefinitionElements.getLength(); j++) {
                        //---convert a <WordDefinition> node into an Element---
                        Element wordDefinitionElement =
                                (Element) wordDefinitionElements.item(j);
                        //---get all the child nodes under the
                        // <WordDefinition> element---
                        NodeList textNodes =
                                ((Node) wordDefinitionElement).getChildNodes();
                        strDefinition +=
                                ((Node) textNodes.item(0)).getNodeValue() + ". \n";
                    }
                }
            }
        } catch (IOException e) {
            Log.d("NetworkingActivity", e.getLocalizedMessage());
        }
        //---return the definitions of the word---
        return strDefinition;
    }


    private class AccessWebServiceTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return WordDefinition(urls[0]);
        }
        protected void onPostExecute(String result) {
            // Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();

            text = (TextView) findViewById(R.id.myTextView); //referencing the textView to display result
            text.setText(result);//setting result into textView to display

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        search =(Button)findViewById(R.id.btnSearch);
        search.setOnClickListener(this);

        //navigating beetween pages
        navView = (BottomNavigationView) findViewById(R.id.bookNavView);
        navView.setSelectedItemId(R.id.navDictionary);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navHome:
                        startActivity(new Intent(DictionaryActivity.this, MainActivity.class));
                        break;
                    case R.id.navCart:
                        startActivity(new Intent(DictionaryActivity.this, CartActivity.class));
                        break;
                    case R.id.navProduct:
                        startActivity(new Intent(DictionaryActivity.this, BookReview.class));
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onClick(View v)
    {
        myText = (EditText) findViewById(R.id.myEditText);
        String searchText = myText.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET);
        }
        else
            new AccessWebServiceTask().execute(searchText);
    }

}
