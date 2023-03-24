package com.roboraider75.frcscouter2019.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roboraider75.frcscouter2019.model.TransferCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amaury Valdes on 1/24/2019.
 */

public class PreferenceUtility {

  private static final String TAG = "PreferenceUtility";
  private final static String DEFAULTS = "com.roboraiders75.defaults";
  private final static String MATCHES = "com.roboraiders75.frc.matches";

  public static void saveAllMatches(Context context, Map<String, TransferCode> allMatches) {

    for (Map.Entry<String, TransferCode> entry: allMatches.entrySet()) {
      String key = entry.getKey();
      TransferCode value = entry.getValue();

      Log.i(TAG, "saveAllMatches: KEY ===> " + key + ", VALUE ===> " + value);
    }

    Gson gson = new Gson();
    String json = gson.toJson(allMatches);

    Log.i(TAG, "saveAllMatches: JSON ==> "+ json);
    SharedPreferences pref = context.getSharedPreferences(MATCHES, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    editor.putString("ALL_MATCHES", json);
    editor.apply();
  }

  public static Map<String, TransferCode> getAllMatches(Context context) {
    Map<String, TransferCode> allMatches = new HashMap<>();

    Gson gson = new Gson();
    SharedPreferences pref = context.getSharedPreferences(MATCHES, Context.MODE_PRIVATE);

    Log.i(TAG, "getAllMatches...");
    if (pref.contains("ALL_MATCHES")) {
      String json = pref.getString("ALL_MATCHES", null);
      allMatches = gson.fromJson(json, new TypeToken<Map<String, TransferCode>>() {}.getType());
      //allMatches = gson.fromJson(json, Map.class);

      for (Map.Entry<String, TransferCode> entry: allMatches.entrySet()) {
        String key = entry.getKey();
        TransferCode value = entry.getValue();

        //Log.i(TAG, "getAllMatches: KEY ===> " + key + ", VALUE ===> " + value);
      }
    }

    return allMatches;
  }
}
