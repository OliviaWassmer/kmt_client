package com.example.bettinawilli.kmtv1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.bettinawilli.kmtv1.R;
import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;
import com.example.bettinawilli.kmtv1.models.nosql.EventsSamstagDO;

import java.util.ArrayList;
import java.util.List;

public class SamstagActivity extends AppCompatActivity {

    ListView listViewSamstag;
    List<String> eventListSamstag = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samstag);
        listViewSamstag = (ListView) findViewById(R.id.listViewSamstag);

        //owa_initialisiert
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AWSMobileClient client = AWSMobileClient.defaultMobileClient();

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedList<EventsSamstagDO> eventList = client.getDynamoDBMapper().scan(EventsSamstagDO.class, scanExpression);

                //Alle Events aus der DB in eine Liste abfüllen.
                if(eventList.size() > 0) {
                    for(int i=0; i<eventList.size();i++){
                        setEventDataOnList(eventList.get(i));
                    }
                } else {
                    eventListSamstag.add("Keine Events gefunden");
                }
            }
        };

        //ArrayAdapter für ListView erstellen
        ArrayAdapter<String> adapterFreitag = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventListSamstag);

        //Array in ListView abfüllen
        listViewSamstag.setAdapter(adapterFreitag);

        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void setEventDataOnList(EventsSamstagDO event){

        eventListSamstag.add(event.getZeit() + " Uhr: "+ "\t" + "\t" + event.getAct());
    }


}
