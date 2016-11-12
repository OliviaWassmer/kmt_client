//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.10
//
package com.example.bettinawilli.kmtv1.mobile;

import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {

    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
            "MobileHub 36c7af9d-f2c9-4e05-af3e-035f1ebce6ee aws-my-sample-app-android-v0.10";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
            Regions.fromName("eu-west-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
            "eu-west-1:6a9b92cb-8b1d-4713-8786-198caa362db0";
    public static final Regions AMAZON_DYNAMODB_REGION =
            Regions.fromName("eu-central-1");
}