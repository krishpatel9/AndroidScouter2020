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
import android.widget.Toast;

import com.google.gson.Gson;
import com.roboraider75.frcscouter2019.model.TransferCode;
import com.roboraider75.frcscouter2019.util.Defaults;
import com.roboraider75.frcscouter2019.util.PreferenceUtility;

import java.util.Map;

/**
 * Created by Amaury Valdes on 1/18/2019.
 */

public class AutoActivity extends AppCompatActivity {

  private static final String TAG = "TeleActivity";

  private TextView autoTeamNumber;
  private TextView autoMatchNumber;
  private ImageView autoAllianceFlag;
  private TextView autoAllianceColor;


  private TextView powerCellInnerPortSuccessNum;
  private TextView powerCellInnerPortFailNum;
  private TextView powerCellOuterPortSuccessNum;
  private TextView powerCellOuterPortFailNum;
  private TextView powerCellBottomPortSuccessNum;
  private TextView powerCellBottomPortFailNum;
  private TextView auto_zone_1_text;
  private TextView auto_zone_2_text;
  private TextView auto_zone_3_text;

  private GridLayout gridLayout2;
  private GridLayout gridLayout7;
  private GridLayout gridLayout8;
  private GridLayout gridLayout11;
  private GridLayout gridLayout12;
  private GridLayout gridLayout15;
  private GridLayout gridLayout16;
  private GridLayout mapVals;


  private TransferCode tcode;
  private Map<String, TransferCode> allMatches;
  private String INSTANCE_STATE = "INSTANCE_STATE";

  private int powerCell_innerPort_success;
  private int powerCell_innerPort_failure;

  private int powerCell_outerPort_success;
  private int powerCell_outerPort_failure;

  private int powerCell_bottomPort_success;
  private int powerCell_bottomPort_failure;

  private int localZone1 = 0;
  private int localZone2 = 0;
  private int localZone3 = 0;
  private int localZone4 = 0;

  private RadioGroup autoRGCrossInitiation;

  private Button autoPowerCellInnerPortSuccessPlusBtn;
  private Button autoPowerCellInnerPortSuccessMinusBtn;
  private Button autoPowerCellInnerPortFailurePlusBtn;
  private Button autoPowerCellInnerPortFailureMinusBtn;


  private Button autoPowerCellOuterPortSuccessPlusBtn;
  private Button autoPowerCellOuterPortSuccessMinusBtn;
  private Button autoPowerCellOuterPortFailurePlusBtn;
  private Button autoPowerCellOuterPortFailureMinusBtn;

  private Button autoPowerCellBottomPortSuccessPlusBtn;
  private Button autoPowerCellBottomPortSuccessMinusBtn;
  private Button autoPowerCellBottomPortFailurePlusBtn;
  private Button autoPowerCellBottomPortFailureMinusBtn;
  private ImageView gameBG;
  private Button teleOpBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auto);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    allMatches = PreferenceUtility.getAllMatches(getApplicationContext());
    autoTeamNumber = findViewById(R.id.autoTeamNumberID);
    autoMatchNumber = findViewById(R.id.autoMatchNumberID);
    autoAllianceColor = findViewById(R.id.autoAllianceColorID);
    autoAllianceFlag = findViewById(R.id.autoAllianceFlagID);

    gridLayout2 = findViewById(R.id.gridLayout2ID);
    gridLayout7 = findViewById(R.id.gridLayout7ID);
    gridLayout8 = findViewById(R.id.gridLayout8ID);
    gridLayout11 = findViewById(R.id.gridLayout11ID);
    gridLayout12 = findViewById(R.id.gridLayout12ID);
    gridLayout15 = findViewById(R.id.gridLayout15ID);
    gridLayout16 = findViewById(R.id.gridLayout16ID);
    mapVals = findViewById(R.id.valGrid);
    autoRGCrossInitiation = findViewById(R.id.autoRGCrossInitiationID);

    auto_zone_1_text = findViewById(R.id.auto_zone_1_text);
    auto_zone_2_text = findViewById(R.id.auto_zone_2_text);
    auto_zone_3_text = findViewById(R.id.auto_zone_3_text);

    autoPowerCellInnerPortSuccessPlusBtn = findViewById(R.id.autoPowerCellInnerPortSuccessPlusBtnID);
    autoPowerCellInnerPortSuccessMinusBtn = findViewById(R.id.autoPowerCellInnerPortSuccessMinusBtnID);
    autoPowerCellInnerPortFailurePlusBtn = findViewById(R.id.autoPowerCellInnerPortFailurePlusBtnID);
    autoPowerCellInnerPortFailureMinusBtn = findViewById(R.id.autoPowerCellInnerPortFailureMinusBtnID);

    autoPowerCellOuterPortSuccessPlusBtn = findViewById(R.id.autoRocketMidCargoSuccessPlusBtnID);
    autoPowerCellOuterPortSuccessMinusBtn = findViewById(R.id.autoRocketMidCargoSuccessMinusBtnID);
    autoPowerCellOuterPortFailurePlusBtn = findViewById(R.id.autoRocketMidCargoFailurePlusBtnID);
    autoPowerCellOuterPortFailureMinusBtn = findViewById(R.id.autoRocketMidCargoFailureMinusBtnID);

    autoPowerCellBottomPortSuccessPlusBtn = findViewById(R.id.autoRocketBotCargoSuccessPlusBtnID);
    autoPowerCellBottomPortSuccessMinusBtn = findViewById(R.id.autoRocketBotCargoSuccessMinusBtnID);
    autoPowerCellBottomPortFailurePlusBtn = findViewById(R.id.autoRocketBotCargoFailurePlusBtnID);
    autoPowerCellBottomPortFailureMinusBtn = findViewById(R.id.autoRocketBotCargoFailureMinusBtnID);

    teleOpBtn = findViewById(R.id.teleopBtnID);

    powerCellInnerPortSuccessNum = findViewById(R.id.autoPowerCellInnerPortSuccessNum);
    powerCellInnerPortFailNum = findViewById(R.id.autoPowerCellInnerPortFailNum);

    powerCellOuterPortSuccessNum = findViewById(R.id.autoRocketMidCargoSuccessNum);
    powerCellOuterPortFailNum = findViewById(R.id.autoRocketMidCargoFailNum);
    powerCellBottomPortSuccessNum = findViewById(R.id.autoRocketBotCargoSuccessNum);
    powerCellBottomPortFailNum = findViewById(R.id.autoRocketBotCargoFailNum);
    gameBG = findViewById(R.id.autoImageID);
    tcode = new TransferCode();

    Intent intent = getIntent();
    if (intent != null) {
      String json = intent.getStringExtra("code");
      Log.i(TAG, "onCreate: intent JSON ==> " + json);

      Gson gson = new Gson();
      tcode = gson.fromJson(json, TransferCode.class);
      Log.i(TAG, "Existing TCODE from Intent..." + tcode.toString());


      setAllValuesFromObject();
      //showAllValues();
    }

    //------
    autoTeamNumber.setText("Team Number: " + tcode.getTeamNumber());
    autoMatchNumber.setText("Match Number: " + tcode.getMatchNumber());
    setComponentBackground(tcode.getIsRed());

    if (tcode.getIsRed() == 1) {
      autoAllianceFlag.setImageDrawable(getResources().getDrawable(R.drawable.flag_red));
      autoAllianceColor.setText(Defaults.RED_ALLIANCE);
    } else {
      autoAllianceFlag.setImageDrawable(getResources().getDrawable(R.drawable.flag_blue));
      autoAllianceColor.setText(Defaults.BLUE_ALLIANCE);
    }
    int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
      gameBG.setBackgroundResource(R.drawable.game_bg_p);
    } else {
      gameBG.setBackgroundResource(R.drawable.game_bg_l);
    }
    View.OnClickListener listener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Button btn = (Button) view;
        int id = btn.getId();

        switch (id) {

          // Rocket Top Cargo
          case R.id.autoPowerCellInnerPortSuccessPlusBtnID:
            Log.d(TAG, "Inside of Rocket Top Cargo success plus");
            if (powerCell_innerPort_success < Defaults.MAX_ROCKET_NUMBER)
              powerCell_innerPort_success++;
            tcode.setAuto_powerCell_innerPort_s(powerCell_innerPort_success);
            powerCellInnerPortSuccessNum.setText(String.valueOf(powerCell_innerPort_success));
            saveMatch();
            break;

          case R.id.autoPowerCellInnerPortSuccessMinusBtnID:
            Log.d(TAG, "Inside of Rocket Top Cargo success minus");
            if (powerCell_innerPort_success > 0) powerCell_innerPort_success--;
            tcode.setAuto_powerCell_innerPort_s(powerCell_innerPort_success);
            powerCellInnerPortSuccessNum.setText(String.valueOf(powerCell_innerPort_success));
            saveMatch();
            break;

          case R.id.autoPowerCellInnerPortFailurePlusBtnID:
            Log.d(TAG, "Inside of Rocket Top Cargo failure plus");
            if (powerCell_innerPort_failure < Defaults.MAX_ROCKET_NUMBER)
              powerCell_innerPort_failure++;
            tcode.setAuto_powerCell_innerPort_f(powerCell_innerPort_failure);
            powerCellInnerPortFailNum.setText(String.valueOf(powerCell_innerPort_failure));
            saveMatch();
            break;

          case R.id.autoPowerCellInnerPortFailureMinusBtnID:
            Log.d(TAG, "Inside of Rocket Top Cargo failure minus");
            if (powerCell_innerPort_failure > 0) powerCell_innerPort_failure--;
            tcode.setAuto_powerCell_innerPort_f(powerCell_innerPort_failure);
            powerCellInnerPortFailNum.setText(String.valueOf(powerCell_innerPort_failure));
            saveMatch();
            break;

          // Rocket Mid Cargo
          case R.id.autoRocketMidCargoSuccessPlusBtnID:
            Log.d(TAG, "Inside of Rocket Mid Cargo success plus");
            if (powerCell_outerPort_success < Defaults.MAX_ROCKET_NUMBER)
              powerCell_outerPort_success++;
            tcode.setAuto_powerCell_outerPort_s(powerCell_outerPort_success);
            powerCellOuterPortSuccessNum.setText(String.valueOf(powerCell_outerPort_success));
            saveMatch();
            break;

          case R.id.autoRocketMidCargoSuccessMinusBtnID:
            Log.d(TAG, "Inside of Rocket Mid Cargo success minus");
            if (powerCell_outerPort_success > 0) powerCell_outerPort_success--;
            tcode.setAuto_powerCell_outerPort_s(powerCell_outerPort_success);
            powerCellOuterPortSuccessNum.setText(String.valueOf(powerCell_outerPort_success));
            saveMatch();
            break;

          case R.id.autoRocketMidCargoFailurePlusBtnID:
            Log.d(TAG, "Inside of Rocket Mid Cargo failure plus");
            if (powerCell_outerPort_failure < Defaults.MAX_ROCKET_NUMBER)
              powerCell_outerPort_failure++;
            tcode.setAuto_powerCell_outerPort_f(powerCell_outerPort_failure);
            powerCellOuterPortFailNum.setText(String.valueOf(powerCell_outerPort_failure));
            saveMatch();
            break;

          case R.id.autoRocketMidCargoFailureMinusBtnID:
            Log.d(TAG, "Inside of Rocket Mid Cargo failure minus");
            if (powerCell_outerPort_failure > 0) powerCell_outerPort_failure--;
            tcode.setAuto_powerCell_outerPort_f(powerCell_outerPort_failure);
            powerCellOuterPortFailNum.setText(String.valueOf(powerCell_outerPort_failure));
            saveMatch();
            break;

          // Rocket Bot Cargo
          case R.id.autoRocketBotCargoSuccessPlusBtnID:
            Log.d(TAG, "Inside of Rocket Bot Cargo success plus");
            if (powerCell_bottomPort_success < Defaults.MAX_ROCKET_NUMBER)
              powerCell_bottomPort_success++;
            tcode.setAuto_powerCell_bottomPort_s(powerCell_bottomPort_success);
            powerCellBottomPortSuccessNum.setText(String.valueOf(powerCell_bottomPort_success));
            saveMatch();
            break;
          case R.id.autoRocketBotCargoSuccessMinusBtnID:
            Log.d(TAG, "Inside of Rocket Bot Cargo success minus");
            if (powerCell_bottomPort_success > 0) powerCell_bottomPort_success--;
            tcode.setAuto_powerCell_bottomPort_s(powerCell_bottomPort_success);
            powerCellBottomPortSuccessNum.setText(String.valueOf(powerCell_bottomPort_success));
            saveMatch();
            break;

          case R.id.autoRocketBotCargoFailurePlusBtnID:
            Log.d(TAG, "Inside of Rocket Bot Cargo failure plus");
            if (powerCell_bottomPort_failure < Defaults.MAX_ROCKET_NUMBER)
              powerCell_bottomPort_failure++;
            tcode.setAuto_powerCell_bottomPort_f(powerCell_bottomPort_failure);
            powerCellBottomPortFailNum.setText(String.valueOf(powerCell_bottomPort_failure));
            saveMatch();
            break;

          case R.id.autoRocketBotCargoFailureMinusBtnID:
            Log.d(TAG, "Inside of Rocket Bot Cargo failure minus");
            if (powerCell_bottomPort_failure > 0) powerCell_bottomPort_failure--;
            tcode.setAuto_powerCell_bottomPort_f(powerCell_bottomPort_failure);
            powerCellBottomPortFailNum.setText(String.valueOf(powerCell_bottomPort_failure));
            saveMatch();
            break;

          default:
            Log.e(TAG, "Something is wrong...");
            saveMatch();
            break;
        }
      }

    };

    autoRGCrossInitiation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.autoRGCrossInitiationtSuccessID) {
          Log.i(TAG, "sandstormRGCrossHabitat: Success");
          tcode.setAuto_cross_line(1);
        } else if (checkedId == R.id.autoRGCrossInitiationFailedID) {
          Log.i(TAG, "sandstormRGCrossHabitat: Failed");
          tcode.setAuto_cross_line(0);
        }
      }
    });

    autoPowerCellInnerPortSuccessPlusBtn.setOnClickListener(listener);
    autoPowerCellInnerPortSuccessMinusBtn.setOnClickListener(listener);
    autoPowerCellInnerPortFailurePlusBtn.setOnClickListener(listener);
    autoPowerCellInnerPortFailureMinusBtn.setOnClickListener(listener);

    autoPowerCellOuterPortSuccessPlusBtn.setOnClickListener(listener);
    autoPowerCellOuterPortSuccessMinusBtn.setOnClickListener(listener);
    autoPowerCellOuterPortFailurePlusBtn.setOnClickListener(listener);
    autoPowerCellOuterPortFailureMinusBtn.setOnClickListener(listener);

    autoPowerCellBottomPortSuccessPlusBtn.setOnClickListener(listener);
    autoPowerCellBottomPortSuccessMinusBtn.setOnClickListener(listener);
    autoPowerCellBottomPortFailurePlusBtn.setOnClickListener(listener);
    autoPowerCellBottomPortFailureMinusBtn.setOnClickListener(listener);

    teleOpBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        proceedTeleOpActivity();
      }
    });  // End of AutoButton

    showAllValues();
  }
  private void proceedTeleOpActivity() {
    Gson gson = new Gson();
    String json = gson.toJson(tcode);

    Log.i(TAG, "proceedTeleOpActivity: intent JSON ==> " + json);
    saveMatch();

    Intent intent = new Intent(AutoActivity.this, TeleActivity.class);
    intent.putExtra("code", json);
    startActivity(intent);

    // Amaury --- FIX 3/16/2019
    String key = tcode.getTeamNumber() + "/" + tcode.getMatchNumber();
    tcode = allMatches.get(key);
    Log.i(TAG, "After proceedTeleOpActivity(): " + tcode.toString());
  }
  private void saveMatch() {
    String key = tcode.getTeamNumber() + "/" + tcode.getMatchNumber();
    Log.i(TAG, "saveMatch, KEY: " + key + ", TCODE..: " + tcode.toString());
    allMatches.put(key, tcode);
    PreferenceUtility.saveAllMatches(getApplicationContext(), allMatches);
  }
  private void setAllValuesFromObject() {
    powerCell_innerPort_success = tcode.getAuto_powerCell_innerPort_s();
    powerCell_innerPort_failure = tcode.getAuto_powerCell_innerPort_f();

    powerCell_outerPort_success = tcode.getAuto_powerCell_outerPort_s();
    powerCell_outerPort_failure = tcode.getAuto_powerCell_outerPort_f();

    powerCell_bottomPort_success = tcode.getAuto_powerCell_bottomPort_s();
    powerCell_bottomPort_failure = tcode.getAuto_powerCell_bottomPort_f();

    localZone1 = tcode.getAuto_zone1();
    localZone2 = tcode.getAuto_zone2();
    localZone3 = tcode.getAuto_zone3();
  }
  private void showAllValues() {
    if (tcode.getAuto_cross_line() == 1) {
      autoRGCrossInitiation.check(R.id.autoRGCrossInitiationtSuccessID);
    }

    if (tcode.getAuto_cross_line() == 0) {
      autoRGCrossInitiation.check(R.id.autoRGCrossInitiationFailedID);
    }

    powerCellInnerPortSuccessNum.setText(String.valueOf(powerCell_innerPort_success));
    powerCellInnerPortFailNum.setText(String.valueOf(powerCell_innerPort_failure));
    powerCellOuterPortSuccessNum.setText(String.valueOf(powerCell_outerPort_success));
    powerCellOuterPortFailNum.setText(String.valueOf(powerCell_outerPort_failure));
    powerCellBottomPortSuccessNum.setText(String.valueOf(powerCell_bottomPort_success));
    powerCellBottomPortFailNum.setText(String.valueOf(powerCell_bottomPort_failure));
    auto_zone_1_text.setText(String.valueOf(localZone1));
    auto_zone_2_text.setText(String.valueOf(localZone2));
    auto_zone_3_text.setText(String.valueOf(localZone2));

  }
  private void setComponentBackground(int isRed) {

    if (isRed == 1) {
      gridLayout2.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout7.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout8.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout11.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout12.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout15.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout16.setBackgroundResource(R.drawable.round_grid_red);
      mapVals.setBackgroundResource(R.drawable.round_grid_red);
    } else {
      gridLayout2.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout7.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout8.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout11.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout12.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout15.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout16.setBackgroundResource(R.drawable.round_grid_blue);
      mapVals.setBackgroundResource(R.drawable.round_grid_blue);
    }
  }

  protected void updateValues() {
    Log.i(TAG, "updateValues: ");
    if (tcode.getAuto_cross_line() == 1) {
      autoRGCrossInitiation.check(R.id.autoRGCrossInitiationtSuccessID);
    } else {
      autoRGCrossInitiation.check(R.id.autoRGCrossInitiationFailedID);
    }
    powerCell_innerPort_success = tcode.getAuto_powerCell_innerPort_s();
    powerCell_innerPort_failure = tcode.getAuto_powerCell_innerPort_f();
    powerCell_outerPort_success = tcode.getAuto_powerCell_outerPort_s();
    powerCell_outerPort_failure = tcode.getAuto_powerCell_outerPort_f();
    powerCell_bottomPort_success = tcode.getAuto_powerCell_bottomPort_s();
    powerCell_bottomPort_failure = tcode.getAuto_powerCell_bottomPort_f();
    localZone1 = tcode.getTele_zone1();
    localZone2 = tcode.getTele_zone2();
    localZone3 = tcode.getTele_zone3();

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
    Log.i(TAG, "onResume: Inside of TeleActivity...");
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
    updateValues();
  }
  @Override
  public boolean onSupportNavigateUp() {
    saveMatch();
    onBackPressed();
    return true;
  }

  public void plus_zone_2_shots(View view) {
    localZone2++;
    tcode.plusAutoZone2();
    Toast.makeText(getApplicationContext(), "Number of Zone 2 shots saved to " + localZone2, Toast.LENGTH_SHORT).show();
    String text_for_zone_2 = "" + localZone2;
    auto_zone_2_text.setText(text_for_zone_2);
    saveMatch();
  }
  public void plus_zone_1_shots(View view) {
    localZone1++;
    tcode.plusAutoZone1();
    Toast.makeText(getApplicationContext(), "Number of Zone 1 shots saved to " + localZone1, Toast.LENGTH_SHORT).show();
    String text_for_zone_1 = "" + localZone1;
    auto_zone_1_text.setText(text_for_zone_1);
    saveMatch();
  }
  public void plus_zone_3_shots(View view) {
    localZone3++;
    tcode.plusAutoZone3();
    Toast.makeText(getApplicationContext(), "Number of Zone 1 shots saved to " + localZone3, Toast.LENGTH_SHORT).show();
    String text_for_zone_3 = "" + localZone3;
    auto_zone_3_text.setText(text_for_zone_3);
    saveMatch();
  }
  public void minus_zone_1_shots(View view) {
    if ((localZone1) > 0) {
      tcode.minusAutoZone1();
      localZone1--;
    }
    String text_for_zone_1 = "" + localZone1;
    auto_zone_1_text.setText(text_for_zone_1);
    saveMatch();
  }
  public void minus_zone_2_shots(View view) {
    if ((localZone2) > 0) {
      tcode.minusAutoZone2();
      localZone2--;
    }
    String text_for_zone_2 = "" + localZone2;
    auto_zone_2_text.setText(text_for_zone_2);
    saveMatch();
  }
  public void minus_zone_3_shots(View view) {
    if ((localZone3) > 0) {
      tcode.minusAutoZone3();
      localZone3--;
    }
    String text_for_zone_3 = "" + localZone3;
    auto_zone_3_text.setText(text_for_zone_3);
    saveMatch();
  }
}


