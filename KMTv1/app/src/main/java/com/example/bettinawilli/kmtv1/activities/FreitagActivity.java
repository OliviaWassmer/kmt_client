package com.example.bettinawilli.kmtv1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.bettinawilli.kmtv1.R;
import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;
import com.example.bettinawilli.kmtv1.models.nosql.EventsFreitagDO;

import java.util.ArrayList;
import java.util.List;

public class FreitagActivity extends AppCompatActivity {

    ListView listViewFreitag;
    List<String> eventListFreitag = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freitag);
        listViewFreitag = (ListView) findViewById(R.id.listViewFreitag);

        //owa_initialisiert
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AWSMobileClient client = AWSMobileClient.defaultMobileClient();

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedList<EventsFreitagDO> eventList = client.getDynamoDBMapper().scan(EventsFreitagDO.class, scanExpression);

                //Alle Events aus der DB in eine Liste abfüllen.
                if(eventList.size() > 0) {
                    for(int i=0; i<eventList.size();i++){
                        setEventDataOnList(eventList.get(i));
                    }
                } else {
                    eventListFreitag.add("Keine Events gefunden");
                }
            }
        };

        //ArrayAdapter für ListView erstellen
        ArrayAdapter<String> adapterFreitag = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventListFreitag);

        //Array in ListView abfüllen
        listViewFreitag.setAdapter(adapterFreitag);

        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void setEventDataOnList(EventsFreitagDO event){

        eventListFreitag.add(event.getZeit() + " Uhr: "+ "\t" + "\t" + event.getAct());
    }


}
