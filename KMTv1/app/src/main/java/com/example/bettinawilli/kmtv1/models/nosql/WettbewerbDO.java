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

@DynamoDBTable(tableName = "kmt-mobilehub-125735138-wettbewerb")

public class WettbewerbDO {
    private String _userId;
    private String _mg;
    private Double _stimmen;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "mg")
    public String getMg() {
        return _mg;
    }

    public void setMg(final String _mg) {
        this._mg = _mg;
    }
    @DynamoDBAttribute(attributeName = "stimmen")
    public Double getStimmen() {
        return _stimmen;
    }

    public void setStimmen(final Double _stimmen) {
        this._stimmen = _stimmen;
    }

}
