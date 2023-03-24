package com.roboraider75.frcscouter2019;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.roboraider75.frcscouter2019.model.TransferCode;
import com.roboraider75.frcscouter2019.util.Defaults;
import com.roboraider75.frcscouter2019.util.PreferenceUtility;
import com.roboraider75.frcscouter2019.util.Utility;

import java.util.Map;

/**
 * Created by Amaury Valdes on 1/30/2019.
 */


public class EndGameActivity extends AppCompatActivity {

  private static final String TAG = "EndGameActivity";
  private static final float ENABLED = 1f;
  private static final float DISABLED = 0.15f;

  private TextView endGameTeamNumber;
  private TextView endGameMatchNumber;
  private ImageView endGameAllianceFlag;
  private TextView endGameAllianceColor;
  private ImageView gameBG;

  private GridLayout gridLayout0;
  private GridLayout gridLayout2;
  private GridLayout gridLayout3;
  private GridLayout gridLayout4;

  private RadioGroup endGameRGHang;
  private RadioGroup endGameRGHangPos;
  private RadioGroup endGameRGPark;
  private RadioGroup endGameRGLevel;
  private RadioGroup endGameRGAttemptMade;

  private TransferCode tcode;
  private Map<String, TransferCode> allMatches;
  private String INSTANCE_STATE = "INSTANCE_STATE";

  private Button endGameSaveBtn;
  private Button endGameQRCodeBtn;
  private Button endGameClearBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_end_game);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    allMatches = PreferenceUtility.getAllMatches(getApplicationContext());
    endGameTeamNumber = findViewById(R.id.endGameTeamNumberID);
    endGameMatchNumber = findViewById(R.id.endGameMatchNumberID);
    endGameAllianceColor = findViewById(R.id.endGameAllianceColorID);
    endGameAllianceFlag = findViewById(R.id.endGameAllianceFlagID);

    endGameSaveBtn = findViewById(R.id.endGameSaveBtnID);
    endGameQRCodeBtn = findViewById(R.id.endgameCodeButtonID);
    endGameClearBtn = findViewById(R.id.endGame_clearBtn);

    gridLayout0 = findViewById(R.id.endGameGridLayout0ID);
    gridLayout2 = findViewById(R.id.endGameGridLayout2ID);
    gridLayout3 = findViewById(R.id.endGameGridLayout3ID);
    gridLayout4 = findViewById(R.id.endGameGridLayout4ID);

    endGameRGAttemptMade = findViewById(R.id.endGameRGAttemptMadeID);
    endGameRGHang = findViewById(R.id.endGameRGHangID);
    endGameRGHangPos = findViewById(R.id.endGameRGHangPosID);
    endGameRGPark = findViewById(R.id.endGameRGParkID);
    endGameRGLevel = findViewById(R.id.endGameRGLevelID);
    gameBG = findViewById(R.id.endgameImageID);
    tcode = new TransferCode();

    Intent intent = getIntent();
    if (intent != null) {
      String json = intent.getStringExtra("code");
      Log.i(TAG, "onCreate: intent JSON ==> " + json);

      Gson gson = new Gson();
      tcode = gson.fromJson(json, TransferCode.class);
      Log.i(TAG, "Existing TCODE from Intent..." + tcode.toString());

    }
      showAllValues();
    //------
    endGameTeamNumber.setText("Team Number: " + tcode.getTeamNumber());
    endGameMatchNumber.setText("Match Number: " + tcode.getMatchNumber());
    setComponentBackground(tcode.getIsRed());

    if (tcode.getIsRed() == 1) {
      endGameAllianceFlag.setImageDrawable(getResources().getDrawable(R.drawable.flag_red));
      endGameAllianceColor.setText(Defaults.RED_ALLIANCE);
    } else {
      endGameAllianceFlag.setImageDrawable(getResources().getDrawable(R.drawable.flag_blue));
      endGameAllianceColor.setText(Defaults.BLUE_ALLIANCE);
    }
    int orientation = getResources().getConfiguration().orientation;
    if(orientation == Configuration.ORIENTATION_PORTRAIT){
      gameBG.setBackgroundResource(R.drawable.game_bg_p);
    }
    else{
      gameBG.setBackgroundResource(R.drawable.game_bg_l);
    }



    endGameSaveBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        saveMatch();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
      }
    });  // End of endGameBtn

    endGameQRCodeBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Utility.showCodeDialog(EndGameActivity.this, tcode);
      }
    });  // End of endGameQRCodeBtn
    endGameClearBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        tcode.setEndgame_did_not_attempt(1);
        tcode.setEndgame_hang_s(0);
        tcode.setEndgame_hang_f(0);
        tcode.setEndgame_hangPos(0);
        tcode.setEndgame_park_s(0);
        tcode.setEndgame_park_f(0);
        tcode.setEndgame_level(0);

        endGameRGAttemptMade.clearCheck();
        endGameRGHang.clearCheck();
        endGameRGHangPos.clearCheck();
        endGameRGPark.clearCheck();
        endGameRGLevel.clearCheck();
        gridLayout0.setAlpha(ENABLED);
        gridLayout2.setAlpha(ENABLED);
        gridLayout3.setAlpha(ENABLED);
        gridLayout4.setAlpha(ENABLED);
        enableRadioButtons(endGameRGAttemptMade);
        enableRadioButtons(endGameRGHang);
        enableRadioButtons(endGameRGHangPos);
        enableRadioButtons(endGameRGPark);
        enableRadioButtons(endGameRGLevel);
        endGameRGAttemptMade.clearCheck();
        saveMatch();
      }
    });  // End of endGameClearBtn

    endGameRGAttemptMade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.endGameRGAttemptMadeYesID) {
          Log.i(TAG, "endGameRGHubPlatform: YES");
          tcode.setEndgame_did_not_attempt(0);
          gridLayout2.setAlpha(ENABLED);
          gridLayout3.setAlpha(ENABLED);
          gridLayout4.setAlpha(ENABLED);
          enableRadioButtons(endGameRGHang);
          enableRadioButtons(endGameRGHangPos);
          enableRadioButtons(endGameRGPark);
          enableRadioButtons(endGameRGLevel);
          saveMatch();
        } else if (checkedId == R.id.endGameRGAttemptMadeNoID) {
          Log.i(TAG, "endGameRGHubPlatform: Failed");
          tcode.setEndgame_did_not_attempt(1);
          tcode.setEndgame_hang_s(0);
          tcode.setEndgame_park_s(0);
          tcode.setEndgame_hang_f(0);
          tcode.setEndgame_park_f(0);
          tcode.setEndgame_level(0);
          endGameRGHang.clearCheck();
          endGameRGHangPos.clearCheck();
          endGameRGPark.clearCheck();
          endGameRGLevel.clearCheck();
          gridLayout2.setAlpha(DISABLED);
          gridLayout3.setAlpha(DISABLED);
          gridLayout4.setAlpha(DISABLED);
          disableRadioButtons(endGameRGHang);
          disableRadioButtons(endGameRGHangPos);
          disableRadioButtons(endGameRGPark);
          disableRadioButtons(endGameRGLevel);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeNoID);
          saveMatch();
        }
      }
    });

      endGameRGHang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.endGameHangSuccessID) {
          Log.i(TAG, "endGameRGHubPlatform1: Success");
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_hang_s(1);
          tcode.setEndgame_hang_f(0);
          tcode.setEndgame_park_s(0);
          tcode.setEndgame_park_f(0);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
          endGameRGPark.clearCheck();
          gridLayout2.setAlpha(DISABLED);
          gridLayout4.setAlpha(ENABLED);
          disableRadioButtons(endGameRGPark);
          enableRadioButtons(endGameRGLevel);
          saveMatch();
        } else if (checkedId == R.id.endGameHangFailedID) {
          Log.i(TAG, "endGameRGHubPlatform1: Failed");
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_hang_f(1);
          tcode.setEndgame_hang_s(0);
          tcode.setEndgame_park_s(0);
          tcode.setEndgame_park_f(0);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
//          endGameRGLevel.clearCheck();
          endGameRGPark.clearCheck();
          gridLayout2.setAlpha(DISABLED);
//          gridLayout4.setAlpha(DISABLED);
          disableRadioButtons(endGameRGPark);
//          disableRadioButtons(endGameRGLevel);
          saveMatch();
        }
      }
    });
    endGameRGHangPos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.endGameHangPosMidID) {
          tcode.setEndgame_hangPos(1);
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_park_s(0);
          tcode.setEndgame_park_f(0);
          disableRadioButtons(endGameRGPark);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
          gridLayout2.setAlpha(DISABLED);
          saveMatch();
        } else if (checkedId == R.id.endGameHangPosSideID) {
          tcode.setEndgame_hangPos(0);
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_park_s(0);
          tcode.setEndgame_park_f(0);
          disableRadioButtons(endGameRGPark);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
          gridLayout2.setAlpha(DISABLED);
          saveMatch();
        }
      }
    });

      endGameRGPark.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.endGameParkSuccessID) {
          tcode.setEndgame_did_not_attempt(0);
//          tcode.setEndgame_hang(0);
          tcode.setEndgame_park_s(1);
          tcode.setEndgame_park_f(0);
          tcode.setEndgame_hang_s(0);
          tcode.setEndgame_hang_f(0);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
          endGameRGHang.clearCheck();
          endGameRGHangPos.clearCheck();
          gridLayout3.setAlpha(DISABLED);
//          gridLayout4.setAlpha(DISABLED);
          disableRadioButtons(endGameRGHang);
          disableRadioButtons(endGameRGHangPos);
//          disableRadioButtons(endGameRGLevel);
          saveMatch();
        } else if (checkedId == R.id.endGameParkFailedID) {
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_park_f(1);
          tcode.setEndgame_park_s(0);
          tcode.setEndgame_hang_s(0);
          tcode.setEndgame_hang_f(0);
          endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
          endGameRGHang.clearCheck();
          endGameRGHangPos.clearCheck();
          gridLayout3.setAlpha(DISABLED);
//          gridLayout4.setAlpha(DISABLED);
          disableRadioButtons(endGameRGHang);
          disableRadioButtons(endGameRGHangPos);
//          disableRadioButtons(endGameRGLevel);
          saveMatch();
        }
      }
    });

    endGameRGLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.endGameLevelSuccessID) {
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_level(1);
        } else if (checkedId == R.id.endGameLevelFailedID) {
          tcode.setEndgame_did_not_attempt(0);
          tcode.setEndgame_level(0);
        }
      }
    });
  }
  private void disableRadioButtons(RadioGroup rg) {
    for (int i = 0; i < rg.getChildCount(); i++) {
      rg.getChildAt(i).setEnabled(false);
    }
  }
  private void enableRadioButtons(RadioGroup rg) {
    for (int i = 0; i < rg.getChildCount(); i++) {
      rg.getChildAt(i).setEnabled(true);
    }
  }
  private void saveMatch() {
    String key = tcode.getTeamNumber() + "/" + tcode.getMatchNumber();
    Log.i(TAG, "saveMatch, KEY: " + key + ", TCODE..: " + tcode.toString());
    allMatches.put(key, tcode);
    PreferenceUtility.saveAllMatches(getApplicationContext(), allMatches);
  }
  private void showAllValues() {

    if (tcode.getEndgame_did_not_attempt() == 1) {
      endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeNoID);
      gridLayout2.setAlpha(DISABLED);//Park
      gridLayout3.setAlpha(DISABLED);//hang
      gridLayout4.setAlpha(DISABLED);//level
      disableRadioButtons(endGameRGHang);
      disableRadioButtons(endGameRGPark);
      disableRadioButtons(endGameRGLevel);
    } else {
      endGameRGAttemptMade.check(R.id.endGameRGAttemptMadeYesID);
      if (tcode.getEndgame_hang_s() == 1){
        endGameRGHang.check(R.id.endGameHangSuccessID);
        if (tcode.getEndgame_level() == 1) {
          endGameRGLevel.check(R.id.endGameLevelSuccessID);
        } else if (tcode.getEndgame_level() == 0){
          endGameRGLevel.check(R.id.endGameLevelFailedID);
        }
        if (tcode.getEndgame_hangPos() == 1) {
          endGameRGHangPos.check(R.id.endGameHangPosMidID);
        } else if (tcode.getEndgame_hangPos() == 0){
          endGameRGHangPos.check(R.id.endGameHangPosSideID);
        }
        disableRadioButtons(endGameRGPark);
        gridLayout2.setAlpha(DISABLED);
      }
      else if (tcode.getEndgame_hang_f() == 1){
        endGameRGHang.check(R.id.endGameHangFailedID);
        if (tcode.getEndgame_level() == 1) {
          endGameRGLevel.check(R.id.endGameLevelSuccessID);
        } else if (tcode.getEndgame_level() == 0){
          endGameRGLevel.check(R.id.endGameLevelFailedID);
        }
        if (tcode.getEndgame_hangPos() == 1) {
          endGameRGHangPos.check(R.id.endGameHangPosMidID);
        } else if (tcode.getEndgame_hangPos() == 0){
          endGameRGHangPos.check(R.id.endGameHangPosSideID);
        }
        disableRadioButtons(endGameRGPark);
        gridLayout2.setAlpha(DISABLED);
      }
      if (tcode.getEndgame_park_s() == 1){
        endGameRGPark.check(R.id.endGameParkSuccessID);
        disableRadioButtons(endGameRGHang);
        disableRadioButtons(endGameRGHangPos);
        disableRadioButtons(endGameRGLevel);
        gridLayout3.setAlpha(DISABLED);
        gridLayout4.setAlpha(DISABLED);
      }
      else if (tcode.getEndgame_park_f() == 1){
        endGameRGPark.check(R.id.endGameParkFailedID);
        disableRadioButtons(endGameRGHang);
        disableRadioButtons(endGameRGHangPos);
        disableRadioButtons(endGameRGLevel);
        gridLayout3.setAlpha(DISABLED);
        gridLayout4.setAlpha(DISABLED);
      }
//
//      if (tcode.getEndgame_hang() == 1) {
//        endGameRGHang.check(R.id.endGameHangSuccessID);
//        endGameRGPark.check(R.id.endGameParkFailedID);
//        gridLayout2.setAlpha(DISABLED);
//        if (tcode.getEndgame_hangPos() == 1) {
//          endGameRGHangPos.check(R.id.endGameHangPosMidID);
//        } else if (tcode.getEndgame_hangPos() == 0){
//          endGameRGHangPos.check(R.id.endGameHangPosSideID);
//        }
//        if (tcode.getEndgame_level() == 1) {
//          endGameRGLevel.check(R.id.endGameLevelSuccessID);
//        } else if (tcode.getEndgame_level() == 0){
//          endGameRGLevel.check(R.id.endGameLevelFailedID);
//        }
//      }
//      else if (tcode.getEndgame_park() == 1) {
//        endGameRGPark.check(R.id.endGameParkSuccessID);
//        gridLayout3.setAlpha(DISABLED);
//        gridLayout4.setAlpha(DISABLED);
//      }
    }
  }
  private void setComponentBackground(int isRed) {

    if (isRed == 1) {
      gridLayout0.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout2.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout3.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout4.setBackgroundResource(R.drawable.round_grid_red);
    } else {
      gridLayout0.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout2.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout3.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout4.setBackgroundResource(R.drawable.round_grid_blue);
    }
  }
  @Override
  protected void onStart() {
    Log.i(TAG, "onStart: ");
    super.onStart();
  }
  @Override
  protected void onRestart() {
    Log.i(TAG, "onRestart: ");
    super.onRestart();
  }
  @Override
  protected void onDestroy() {
    Log.i(TAG, "onDestroy: ");
    super.onDestroy();
  }
  @Override
  protected void onPause() {
    Log.i(TAG, "onPause: ");
    super.onPause();
  }
  @Override
  public void onResume() {
    Log.i(TAG, "onResume: Inside of EndGameActivity...");
    allMatches = PreferenceUtility.getAllMatches(getApplicationContext());
    String key = tcode.getTeamNumber() + "/" + tcode.getMatchNumber();
    tcode = allMatches.get(key);

    super.onResume();
  }
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    Log.i(TAG, "onSaveInstanceState: ");
    Gson gson = new Gson();
    String json = gson.toJson(tcode);
    outState.putString(INSTANCE_STATE, json);
    saveMatch();
    super.onSaveInstanceState(outState);
  }
  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    Log.i(TAG, "onRestoreInstanceState: ");
    super.onRestoreInstanceState(savedInstanceState);
    String json = savedInstanceState.getString(INSTANCE_STATE);
    Gson gson = new Gson();
    tcode = gson.fromJson(json, TransferCode.class);
    showAllValues();
  }
  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    saveMatch();
    return true;
  }
}
