package com.example.bettinawilli.kmtv1.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "kmt-mobilehub-125735138-eventsFreitag")

public class EventsFreitagDO {
    private String _userId;
    private String _act;
    private String _zeit;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "act")
    public String getAct() {
        return _act;
    }

    public void setAct(final String _act) {
        this._act = _act;
    }
    @DynamoDBAttribute(attributeName = "zeit")
    public String getZeit() {
        return _zeit;
    }

    public void setZeit(final String _zeit) {
        this._zeit = _zeit;
    }

}
