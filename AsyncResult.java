package com.example.ubundu.canteen;

import org.json.JSONObject;


interface AsyncResult
{
    void onResult(JSONObject object);
}