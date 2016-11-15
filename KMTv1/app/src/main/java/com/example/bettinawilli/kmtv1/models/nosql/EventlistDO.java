package com.example.bettinawilli.kmtv1.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "kmt-mobilehub-125735138-eventlist")
/*
Ist eigentlich wie Modelklasse, würde dann eigentlich Event heissen da es keine Liste ist.
 */
public class EventlistDO {
    private String _userId;
    private String _datum;
    private String _event;
    private String _spielort;
    private String _zeit;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "datum")
    public String getDatum() {
        return _datum;
    }

    public void setDatum(final String _datum) {
        this._datum = _datum;
    }
    @DynamoDBAttribute(attributeName = "event")
    public String getEvent() {
        return _event;
    }

    public void setEvent(final String _event) {
        this._event = _event;
    }
    @DynamoDBAttribute(attributeName = "spielort")
    public String getSpielort() {
        return _spielort;
    }

    public void setSpielort(final String _spielort) {
        this._spielort = _spielort;
    }
    @DynamoDBAttribute(attributeName = "zeit")
    public String getZeit() {
        return _zeit;
    }

    public void setZeit(final String _zeit) {
        this._zeit = _zeit;
    }

}
