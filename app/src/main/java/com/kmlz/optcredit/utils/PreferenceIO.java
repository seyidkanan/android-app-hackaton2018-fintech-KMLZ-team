package com.kmlz.optcredit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



import java.util.Map;
public class PreferenceIO {
    private SharedPreferences sharedPreferences;

    private static PreferenceIO preferenceIO;

    private PreferenceIO(Context context){
        sharedPreferences=context.getSharedPreferences("PTSPrefrences", 0);
    }

    public static PreferenceIO getInstance(Context context){
        if(preferenceIO ==null) preferenceIO =new PreferenceIO(context);

        return preferenceIO;
    }

    public void writeParam(String key, String value){
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }




    public void writeParams(Map<String,String> map){
        Editor editor = sharedPreferences.edit();

        for (String key:map.keySet()) {
            editor.putString(key, map.get(key));
        }

        editor.apply();
    }

    public String readParam(String key){
        return sharedPreferences.getString(key, null);
    }


    public void resetAll(Context context) {
        context.getSharedPreferences("OptCreditPrefs", 0).edit().clear().apply();
    }
}

