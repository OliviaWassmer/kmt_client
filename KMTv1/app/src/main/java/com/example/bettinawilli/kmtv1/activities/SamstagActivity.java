package com.example.bettinawilli.kmtv1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.bettinawilli.kmtv1.R;
import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;
import com.example.bettinawilli.kmtv1.models.nosql.EventsFreitagDO;
import com.example.bettinawilli.kmtv1.models.nosql.EventsSamstagDO;

import java.util.ArrayList;
import java.util.List;

public class SamstagActivity extends AppCompatActivity {

    ListView listViewSamstag;
    //List<String> eventListSamstag = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samstag);
        listViewSamstag = (ListView) findViewById(R.id.listViewSamstag);

        //initialisiert
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        //ArrayAdapter für ListView erstellen
        final ArrayAdapter<String> adapterSamstag = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());

        //Array in ListView abfüllen
        listViewSamstag.setAdapter(adapterSamstag);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AWSMobileClient client = AWSMobileClient.defaultMobileClient();

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedList<EventsSamstagDO> eventList = client.getDynamoDBMapper().scan(EventsSamstagDO.class, scanExpression);

                //Alle Events aus der DB in eine Liste abfüllen.
                final ArrayList<String> samstagList = new ArrayList<String>();
                //Alle Events aus der DB in eine Liste abfüllen.
                if(eventList.size() > 0) {
                    for(int i=0; i<eventList.size();i++){
                        setEventDataOnList(samstagList, eventList.get(i));
                    }
                } else {
                    samstagList.add("Keine Events gefunden");
                }

                SamstagActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("bla", "Updating data...");
                        adapterSamstag.clear();
                        adapterSamstag.addAll(samstagList);
                        adapterSamstag.notifyDataSetChanged();
                    }
                });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void setEventDataOnList(ArrayList<String> samstagList, EventsSamstagDO event){

        samstagList.add(event.getZeit() + " Uhr: "+ "\t" + "\t" + event.getAct());
    }


}
