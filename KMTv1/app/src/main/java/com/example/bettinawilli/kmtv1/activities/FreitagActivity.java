package com.example.bettinawilli.kmtv1.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.bettinawilli.kmtv1.R;
import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;
import com.example.bettinawilli.kmtv1.models.nosql.EventlistDO;

public class FreitagActivity extends AppCompatActivity {

    private static final String LOG_TAG = "kmtv1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freitag);


        //owa_initialisiert
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AWSMobileClient client = AWSMobileClient.defaultMobileClient();

                //Should be a Query call with the date as query parameter instead of a full scan.
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedList<EventlistDO> eventList = client.getDynamoDBMapper().scan(EventlistDO.class, scanExpression);

                if(eventList.size() > 0) {
                    setEventDataOnView(eventList.get(0));
                } else {
                    Log.w(LOG_TAG, "No events found.");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void setEventDataOnView(EventlistDO event){
        //Should now set the values on the Viewfields(Tabellenanzeige in GUI).
        //for now just print it to log
        Log.i(LOG_TAG, "Would now set values for Event [name="+event.getEvent()+", date="+event.getDatum()+", ort="+event.getSpielort()+"]");
    }
}
