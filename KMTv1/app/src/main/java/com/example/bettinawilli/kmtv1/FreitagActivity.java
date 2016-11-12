package com.example.bettinawilli.kmtv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;
import com.example.bettinawilli.kmtv1.models.nosql.EventlistDO;

public class FreitagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freitag);

        //owa_das sollte in einer initialize application klasse generalistisch gemacht werden und nicht für jede activit
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());
        AWSMobileClient client = AWSMobileClient.defaultMobileClient();

        Log.i("com.example", ""+client.getDynamoDBClient().listTables());

        /*owa_Error weil DAten nicht in Mainthread von Activity geholt werden dürfen,
         müssen einen zweiten Thread dafür haben - Stichwort IntentService der das handelt

          */
    }




}
