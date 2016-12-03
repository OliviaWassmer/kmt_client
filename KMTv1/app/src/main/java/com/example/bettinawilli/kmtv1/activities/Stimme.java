package com.example.bettinawilli.kmtv1.activities;

import com.example.bettinawilli.kmtv1.models.nosql.WettbewerbDO;

/**
 * Created by olivi on 03.12.2016.
 */

public class Stimme {
    WettbewerbDO wettbewerbGewinner = new WettbewerbDO();
    boolean geklappt;

    public boolean setzeMeineStimme(WettbewerbDO meinGewinner){
        this.wettbewerbGewinner = meinGewinner;

        //hole Stimme vorher
        double stimmenVorher = wettbewerbGewinner.getStimmen();
        //setze Stimme vorher plus eins, dh. ich habe abgestimmt
        double stimmeNachAbstimmung = stimmenVorher++;
        //setze neue Stimmenanzahl in DB
        wettbewerbGewinner.setStimmen(stimmeNachAbstimmung);

        if(wettbewerbGewinner.getStimmen()==stimmeNachAbstimmung
                && wettbewerbGewinner.getStimmen()==stimmenVorher+1){
            geklappt = true;
            return geklappt;
        } else {
            geklappt = false;
            return geklappt;
        }


    }

}
