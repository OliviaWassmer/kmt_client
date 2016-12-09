package com.example.bettinawilli.kmtv1.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bettinawilli.kmtv1.R;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;
import com.example.bettinawilli.kmtv1.models.nosql.WettbewerbDO;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.example.bettinawilli.kmtv1.activities.Stimme;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    ListView listViewInfo;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //******************GOTO WEBSEITE*****************************************************

        Button webseiteBtn = (Button) findViewById(R.id.webseiteBtn);

        webseiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://bmhasliberg.ch/kreismusiktag2017/"));
                startActivity(intent);
            }
        });


        //**********RATING**************************************************************

        //tabelle mit Wettbewerebsanwärtern anzeigen
        listViewInfo = (ListView) findViewById(R.id.listViewInfo);

        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        final ArrayAdapter<String> adapterInfo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());

        listViewInfo.setAdapter(adapterInfo);

        //Separater Thread, weil nicht in MainThread sein darf der DB verbindung aufbaut und Daten holt
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AWSMobileClient client = AWSMobileClient.defaultMobileClient();

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedList<WettbewerbDO> wettbewerbList = client.getDynamoDBMapper().scan(WettbewerbDO.class, scanExpression);

                //lokale liste weil durch innere klasse klassenvariablen neu  instanziert = 0 wird
                final ArrayList<String> infoList = new ArrayList<String>();
                //Alle Events aus der DB in eine Liste abfüllen.
                if (wettbewerbList.size() > 0) {
                    for (int i = 0; i < wettbewerbList.size(); i++) {
                        setEventDataOnList(infoList, wettbewerbList.get(i));
                    }
                } else {
                    infoList.add("Keine Musikgruppen gefunden");
                }

                //"DB Thread" darf nicht im UI Zeug machen, darum wieder neuer Thread um DAten von
                // DatenThread in UI Thread zu bringen der sie dann ins UI laden kann
                InfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("InfoActivity", "Updating Info data...");
                        adapterInfo.clear();
                        adapterInfo.addAll(infoList);
                        adapterInfo.notifyDataSetChanged();
                    }
                });

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setEventDataOnList(ArrayList<String> infoList, WettbewerbDO wettbewerb) {

        infoList.add(wettbewerb.getMg());
    }


    //**********CLICK RATING ******************************************************************

    WettbewerbDO meinGewinner = new WettbewerbDO();
    Stimme stimme = new Stimme();

    //welche zeile resp. objekt wurde geklickt?

    //wettbewerbsDO objekt abspeichern
    meinGewinner = //gewaehlterGewinner welchen wir aus Liste geholt haben;

    //auf wettbewerbsDO objekt setzteMeineStimme aufrufen
    stimme.setzeMeineStimme(meinGewinner);


    //disable klick auf zeile wenn setzeMeineStimme returns true


    //***************************************************************************************

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Info Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    ;


}


