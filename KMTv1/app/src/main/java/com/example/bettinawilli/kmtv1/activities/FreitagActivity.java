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

import java.util.ArrayList;
import java.util.List;

public class FreitagActivity extends AppCompatActivity {

    ListView listViewFreitag;
    /*
    List muss in der Inneren Klasse gemacht werden, da sie sonst auf 0 gesetzt wird beim Instanzieren
    der Inneren Klassen... ist jetzt auskommentiert und angepasst.
    List<String> eventListFreitag = new ArrayList<String>();
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freitag);
        listViewFreitag = (ListView) findViewById(R.id.listViewFreitag);

        //Holt Applikationscontext mit dem er nachher weiss wo und wie weitermachen
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());


        //ArrayAdapter für ListView erstellen
        final ArrayAdapter<String> adapterFreitag = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());

        //Array in ListView abfüllen
        listViewFreitag.setAdapter(adapterFreitag);


        //Separater Thread, weil nicht in MainThread sein darf der DB verbindung aufbaut und Daten holt
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AWSMobileClient client = AWSMobileClient.defaultMobileClient();

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedList<EventsFreitagDO> eventList = client.getDynamoDBMapper().scan(EventsFreitagDO.class, scanExpression);

                //lokale liste weil durch innere klasse klassenvariablen neu  instanziert = 0 wird
                final ArrayList<String> freitagList = new ArrayList<String>();
                //Alle Events aus der DB in eine Liste abfüllen.
                if(eventList.size() > 0) {
                    for(int i=0; i<eventList.size();i++){
                        setEventDataOnList(freitagList, eventList.get(i));
                    }
                } else {
                    freitagList.add("Keine Events gefunden");
                }

                //"DB Thread" darf nicht im UI Zeug machen, darum wieder neuer Thread um DAten von
                // DatenThread in UI Thread zu bringen der sie dann ins UI laden kann
                FreitagActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("bla", "Updating data...");
                        adapterFreitag.clear();
                        adapterFreitag.addAll(freitagList);
                        adapterFreitag.notifyDataSetChanged();
                    }
                });

            }
        };




        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void setEventDataOnList(ArrayList<String> freitagList, EventsFreitagDO event){

        freitagList.add(event.getZeit() + " Uhr: "+ "\t" + "\t" + event.getAct());
    }


}
