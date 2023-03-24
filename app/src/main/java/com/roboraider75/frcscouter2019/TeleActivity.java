package com.roboraider75.frcscouter2019;

import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SeekBar;
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
 * Created by Amaury Valdes on 1/25/2019.
 */

public class TeleActivity extends AppCompatActivity {

  private static final String TAG = "TeleActivity";
  private static final float ENABLED = 1f;
  private static final float DISABLED = 0.15f;
  private TextView teleTeamNumber;
  private TextView teleMatchNumber;
  private ImageView teleAllianceFlag;
  private TextView teleAllianceColor;

  private TransferCode tcode;
  private Map<String, TransferCode> allMatches;
  private String INSTANCE_STATE = "INSTANCE_STATE";

  private TextView powerCellInnerPortSuccessNum;
  private TextView powerCellInnerPortFailNum;
  private TextView powerCellOuterPortSuccessNum;
  private TextView powerCellOuterPortFailNum;
  private TextView powerCellBottomPortSuccessNum;
  private TextView powerCellBottomPortFailNum;
  private TextView tele_zone_1_text;
  private TextView tele_zone_2_text;
  private TextView tele_zone_3_text;

  private ImageView gameBG;

  private GridLayout gridLayout1;
  private GridLayout gridLayout2;
  private GridLayout gridLayout3;
  private GridLayout gridLayout7;
  private GridLayout gridLayout8;
  private GridLayout gridLayout11;
  private GridLayout gridLayout12;
  private GridLayout gridLayout15;
  private GridLayout gridLayout16;
  private GridLayout mapVals;

  private int powerCell_innerPort_success;
  private int powerCell_innerPort_failure;

  private int powerCell_outerPort_success;
  private int powerCell_outerPort_failure;

  private int powerCell_bottomPort_success;
  private int powerCell_bottomPort_failure;
  private int stage1speed;
  private int stage2speed;

  private int localZone1 = 0;
  private int localZone2 = 0;
  private int localZone3 = 0;


  private Button telePowerCellInnerPortSuccessPlusBtn;
  private Button telePowerCellInnerPortSuccessMinusBtn;
  private Button telePowerCellInnerPortFailurePlusBtn;
  private Button telePowerCellInnerPortFailureMinusBtn;

  private Button telePowerCellOuterPortSuccessPlusBtn;
  private Button telePowerCellOuterPortSuccessMinusBtn;
  private Button telePowerCellOuterPortFailurePlusBtn;
  private Button telePowerCellOuterPortFailureMinusBtn;

  private Button telePowerCellBottomPortSuccessPlusBtn;
  private Button telePowerCellBottomPortSuccessMinusBtn;
  private Button telePowerCellBottomPortFailurePlusBtn;
  private Button telePowerCellBottomPortFailureMinusBtn;

  private SeekBar speed1;
  private SeekBar speed2;
  private Button teleOpBtn;
  private Button clearBtn;
  private RadioGroup teleControlPanelRGAttempted;
  private RadioGroup teleControlPanelRGStage1;
  private RadioGroup teleControlPanelRGStage2;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tele);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    allMatches = PreferenceUtility.getAllMatches(getApplicationContext());
    teleTeamNumber = findViewById(R.id.teleTeamNumberID);
    teleMatchNumber = findViewById(R.id.teleMatchNumberID);
    teleAllianceColor = findViewById(R.id.teleAllianceColorID);
    teleAllianceFlag = findViewById(R.id.teleAllianceFlagID);

    teleControlPanelRGAttempted = findViewById(R.id.teleControlPanelAttemptedRGID);
    teleControlPanelRGStage1 = findViewById(R.id.teleControlPanelStage1RGID);
    teleControlPanelRGStage2 = findViewById(R.id.teleControlPanelStage2RGID);

    gridLayout1 = findViewById(R.id.teleGridLayout1ID);
    gridLayout2 = findViewById(R.id.teleGridLayout2ID);
    gridLayout3 = findViewById(R.id.teleGridLayout3ID);
    gridLayout7 = findViewById(R.id.teleGridLayout7ID);
    gridLayout8 = findViewById(R.id.teleGridLayout8ID);
    gridLayout11 = findViewById(R.id.teleGridLayout11ID);
    gridLayout12 = findViewById(R.id.teleGridLayout12ID);
    gridLayout15 = findViewById(R.id.teleGridLayout15ID);
    gridLayout16 = findViewById(R.id.teleGridLayout16ID);

    tele_zone_1_text = findViewById(R.id.tele_zone_1_text);
    tele_zone_2_text = findViewById(R.id.tele_zone_2_text);
    tele_zone_3_text = findViewById(R.id.tele_zone_3_text);

    mapVals = findViewById(R.id.valGrid);
    telePowerCellInnerPortSuccessPlusBtn = findViewById(R.id.telePowerCellInnerPortSuccessPlusBtnID);
    telePowerCellInnerPortSuccessMinusBtn = findViewById(R.id.telePowerCellInnerPortSuccessMinusBtnID);
    telePowerCellInnerPortFailurePlusBtn = findViewById(R.id.telePowerCellInnerPortFailurePlusBtnID);
    telePowerCellInnerPortFailureMinusBtn = findViewById(R.id.telePowerCellOuterPortInnerMinusBtnID);

    telePowerCellOuterPortSuccessPlusBtn = findViewById(R.id.telePowerCellOuterPortSuccessPlusBtnID);
    telePowerCellOuterPortSuccessMinusBtn = findViewById(R.id.telePowerCellOuterPortSuccessMinusBtnID);
    telePowerCellOuterPortFailurePlusBtn = findViewById(R.id.telePowerCellOuterPortFailurePlusBtnID);
    telePowerCellOuterPortFailureMinusBtn = findViewById(R.id.telePowerCellOuterPortFailureMinusBtnID);

    telePowerCellBottomPortSuccessPlusBtn = findViewById(R.id.telePowerCellBottomPortSuccessPlusBtnID);
    telePowerCellBottomPortSuccessMinusBtn = findViewById(R.id.telePowerCellBottomPortSuccessMinusBtnID);
    telePowerCellBottomPortFailurePlusBtn = findViewById(R.id.telePowerCellBottomPortFailurePlusBtnID);
    telePowerCellBottomPortFailureMinusBtn = findViewById(R.id.telePowerCellBottomPortFailureMinusBtnID);

    gameBG = findViewById(R.id.teleopImageID);
    teleOpBtn = findViewById(R.id.teleopBtnID);
    clearBtn = findViewById(R.id.tele_clearBtn);
    speed1 = findViewById(R.id.teleStage1Speed);
    speed2 = findViewById(R.id.teleStage2Speed);

    powerCellInnerPortSuccessNum = findViewById(R.id.telePowerCellInnerPortSuccessNum);
    powerCellInnerPortFailNum = findViewById(R.id.telePowerCellInnerPortFailureNum);

    powerCellOuterPortSuccessNum = findViewById(R.id.telePowerCellOuterPortSuccessNum);
    powerCellOuterPortFailNum = findViewById(R.id.telePowerCellOuterPortFailureNum);

    powerCellBottomPortSuccessNum = findViewById(R.id.telePowerCellBottomPortSuccessNum);
    powerCellBottomPortFailNum = findViewById(R.id.telePowerCellBottomPortFailureNum);
    
    tcode = new TransferCode();

    Intent intent = getIntent();
    if (intent != null) {
      String json = intent.getStringExtra("code");
      Log.i(TAG, "onCreate: intent JSON ==> " + json);

      Gson gson = new Gson();
      tcode = gson.fromJson(json, TransferCode.class);
      Log.i(TAG, "Existing TCODE from Intent..." + tcode.toString());

      telePowerCellInnerPortSuccessPlusBtn.setOnClickListener(listener);
      telePowerCellInnerPortSuccessMinusBtn.setOnClickListener(listener);
      telePowerCellInnerPortFailurePlusBtn.setOnClickListener(listener);
      telePowerCellInnerPortFailureMinusBtn.setOnClickListener(listener);

      telePowerCellOuterPortSuccessPlusBtn.setOnClickListener(listener);
      telePowerCellOuterPortSuccessMinusBtn.setOnClickListener(listener);
      telePowerCellOuterPortFailurePlusBtn.setOnClickListener(listener);
      telePowerCellOuterPortFailureMinusBtn.setOnClickListener(listener);

      telePowerCellBottomPortSuccessPlusBtn.setOnClickListener(listener);
      telePowerCellBottomPortSuccessMinusBtn.setOnClickListener(listener);
      telePowerCellBottomPortFailurePlusBtn.setOnClickListener(listener);
      telePowerCellBottomPortFailureMinusBtn.setOnClickListener(listener);

      teleOpBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          proceedEndGameActivity();
        }
      });  // End of AutoButton
      clearBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          tcode.setTeleopAttempted(0);
          tcode.setTeleop_stage_1(0);
          tcode.setTeleop_stage_2(0);
          teleControlPanelRGAttempted.clearCheck();
          teleControlPanelRGStage1.clearCheck();
          teleControlPanelRGStage2.clearCheck();
          gridLayout1.setAlpha(ENABLED);
          gridLayout2.setAlpha(ENABLED);
          gridLayout3.setAlpha(ENABLED);
          enableRadioButtons(teleControlPanelRGAttempted);
          enableRadioButtons(teleControlPanelRGStage1);
          enableRadioButtons(teleControlPanelRGStage2);
          teleControlPanelRGAttempted.clearCheck();
        }
      });  // End of endGameClearBtn

      setAllValuesFromObject();
      showAllValues();

      teleTeamNumber.setText("Team Number: " + tcode.getTeamNumber());
      teleMatchNumber.setText("Match Number: " + tcode.getMatchNumber());
      setComponentBackground(tcode.getIsRed());

      if (tcode.getIsRed() == 1) {
        teleAllianceFlag.setImageDrawable(getResources().getDrawable(R.drawable.flag_red));
        teleAllianceColor.setText(Defaults.RED_ALLIANCE);
      } else {
        teleAllianceFlag.setImageDrawable(getResources().getDrawable(R.drawable.flag_blue));
        teleAllianceColor.setText(Defaults.BLUE_ALLIANCE);
      }

      int orientation = getResources().getConfiguration().orientation;
      if(orientation == Configuration.ORIENTATION_PORTRAIT){
        gameBG.setBackgroundResource(R.drawable.game_bg_p);
      }
      else{
        gameBG.setBackgroundResource(R.drawable.game_bg_l);
      }

      speed1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          stage1speed = progress;
          tcode.setTeleop_stage_1_speed(stage1speed);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
      });

      speed2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          stage2speed = progress;
          tcode.setTeleop_stage_2_speed(stage2speed);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
      });
      teleControlPanelRGAttempted.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
          if (checkedId == R.id.teleControlPanelAttemptedYesID) {
            tcode.setTeleopAttempted(1);
            gridLayout2.setAlpha(ENABLED);
            gridLayout3.setAlpha(ENABLED);
            enableRadioButtons(teleControlPanelRGStage1);
            enableRadioButtons(teleControlPanelRGStage2);
            enableSeekBar(speed1);
            enableSeekBar(speed2);

          } else if (checkedId == R.id.teleControlPanelAttemptedNoID) {
            tcode.setTeleopAttempted(0);
            tcode.setTeleop_stage_1(0);
            tcode.setTeleop_stage_2(0);
            tcode.setTeleop_stage_1_speed(0);
            tcode.setTeleop_stage_2_speed(0);
            teleControlPanelRGStage1.clearCheck();
            teleControlPanelRGStage2.clearCheck();
            gridLayout2.setAlpha(DISABLED);
            gridLayout3.setAlpha(DISABLED);
            disableRadioButtons(teleControlPanelRGStage1);
            disableRadioButtons(teleControlPanelRGStage2);
            disableSeekBar(speed1);
            disableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedNoID);
          }
        }
      });
      teleControlPanelRGStage1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
          if (checkedId == R.id.teleControlPanelStage1SuccessID) {
            tcode.setTeleopAttempted(1);
            tcode.setTeleop_stage_1(2);
            gridLayout3.setAlpha(ENABLED);
            enableRadioButtons(teleControlPanelRGStage2);
            enableSeekBar(speed1);
            enableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
            teleControlPanelRGStage1.check(R.id.teleControlPanelStage1SuccessID);

          }
          else if (checkedId == R.id.teleControlPanelAttemptedFaliure) {
            tcode.setTeleopAttempted(1);
            tcode.setTeleop_stage_1(1);
            tcode.setTeleop_stage_1_speed(0);
//            tcode.setTeleop_stage_2_speed(0);
//            gridLayout3.setAlpha(DISABLED);
//            disableRadioButtons(teleControlPanelRGStage2);
            disableSeekBar(speed1);
//            disableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
            teleControlPanelRGStage1.check(R.id.teleControlPanelAttemptedFaliure);
          }
          else if (checkedId == R.id.teleControlPanelStage1FailedID) {
            tcode.setTeleopAttempted(1);
            tcode.setTeleop_stage_1(0);
            tcode.setTeleop_stage_1_speed(0);
//            tcode.setTeleop_stage_2_speed(0);
//            gridLayout3.setAlpha(DISABLED);
//            disableRadioButtons(teleControlPanelRGStage2);
            disableSeekBar(speed1);
//            disableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
            teleControlPanelRGStage1.check(R.id.teleControlPanelStage1FailedID);
          }
        }
      });
      teleControlPanelRGStage2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
          if (checkedId == R.id.teleControlPanelStage2SuccessID) {
            tcode.setTeleopAttempted(1);
//            tcode.setTeleop_stage_1(2);
            tcode.setTeleop_stage_2(2);
            enableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
//            teleControlPanelRGStage1.check(R.id.teleControlPanelStage1SuccessID);
            teleControlPanelRGStage2.check(R.id.teleControlPanelStage2SuccessID);

          } else if (checkedId == R.id.telePositionControlAttemptedFaliure) {
            tcode.setTeleopAttempted(1);
            tcode.setTeleop_stage_2(1);
            tcode.setTeleop_stage_2_speed(0);
            disableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
            teleControlPanelRGStage2.check(R.id.telePositionControlAttemptedFaliure);

          }else if (checkedId == R.id.teleControlPanelStage2FailedID) {
            tcode.setTeleopAttempted(1);
            tcode.setTeleop_stage_2(0);
            tcode.setTeleop_stage_2_speed(0);
            disableSeekBar(speed2);
            teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
            teleControlPanelRGStage2.check(R.id.teleControlPanelStage2FailedID);

          }
        }
      });
    }
  }
  private void disableRadioButtons(RadioGroup rg) {
    for (int i = 0; i < rg.getChildCount(); i++) {
      rg.getChildAt(i).setEnabled(false);
    }
  }
  private void disableSeekBar(SeekBar rg) {
    rg.setEnabled(false);
  }
  private void enableSeekBar(SeekBar rg) {
    rg.setEnabled(true);
  }
  private void enableRadioButtons(RadioGroup rg) {
    for (int i = 0; i < rg.getChildCount(); i++) {
      rg.getChildAt(i).setEnabled(true);
    }
  }
  private void proceedEndGameActivity() {
    Gson gson = new Gson();
    String json = gson.toJson(tcode);

    Log.i(TAG, "proceedEndGameActivity: intent JSON ==> " + json);
    saveMatch();

    Intent intent = new Intent(TeleActivity.this, EndGameActivity.class);
    intent.putExtra("code", json);
    startActivity(intent);
  }
  private void saveMatch() {
    String key = tcode.getTeamNumber() + "/" + tcode.getMatchNumber();
    Log.i(TAG, "saveMatch, KEY: " + key + ", TCODE..: " + tcode.toString());
    allMatches.put(key, tcode);
    PreferenceUtility.saveAllMatches(getApplicationContext(), allMatches);
  }
  private void setAllValuesFromObject() {
    powerCell_innerPort_success = tcode.getTeleop_powerCell_innerPort_s();
    powerCell_innerPort_failure= tcode.getTeleop_powerCell_innerPort_f();

    powerCell_outerPort_success = tcode.getTeleop_powerCell_outerPort_s();
    powerCell_outerPort_failure = tcode.getTeleop_powerCell_outerPort_f();

    powerCell_bottomPort_success = tcode.getTeleop_powerCell_bottomPort_s();
    powerCell_bottomPort_failure = tcode.getTeleop_powerCell_bottomPort_f();

    stage1speed = tcode.getTeleop_stage_1_speed();
    stage2speed = tcode.getTeleop_stage_2_speed();

    localZone1 = tcode.getTele_zone1();
    localZone2 = tcode.getTele_zone2();
    localZone3 = tcode.getTele_zone3();

  }
  private void showAllValues() {
    if (tcode.getTeleopAttempted() == 1){
      teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
    }
    else{
      teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedNoID);
    }

    if (tcode.getTeleop_stage_1() == 2){
      teleControlPanelRGStage1.check(R.id.teleControlPanelStage1SuccessID);
    }
    else if(tcode.getTeleop_stage_1() == 1){
      teleControlPanelRGStage1.check(R.id.teleControlPanelAttemptedFaliure);
    }
    else{
      teleControlPanelRGStage1.check(R.id.teleControlPanelStage1FailedID);
    }

    if (tcode.getTeleop_stage_2() == 2){
      teleControlPanelRGStage2.check(R.id.teleControlPanelStage2SuccessID);
    }
    else if(tcode.getTeleop_stage_2() == 1){
      teleControlPanelRGStage2.check(R.id.telePositionControlAttemptedFaliure);
    }
    else{
      teleControlPanelRGStage2.check(R.id.teleControlPanelStage2FailedID);
    }
    speed1.setProgress(stage1speed);
    speed2.setProgress(stage2speed);
    tele_zone_1_text.setText(String.valueOf(localZone1));
    tele_zone_2_text.setText(String.valueOf(localZone2));
    tele_zone_3_text.setText(String.valueOf(localZone3));

    powerCellInnerPortSuccessNum.setText(String.valueOf(powerCell_innerPort_success));
    powerCellInnerPortFailNum.setText(String.valueOf(powerCell_innerPort_failure));
    powerCellOuterPortSuccessNum.setText(String.valueOf(powerCell_outerPort_success));
    powerCellOuterPortFailNum.setText(String.valueOf(powerCell_outerPort_failure));
    powerCellBottomPortSuccessNum.setText(String.valueOf(powerCell_bottomPort_success));
    powerCellBottomPortFailNum.setText(String.valueOf(powerCell_bottomPort_failure));
  }
  private void setComponentBackground(int isRed) {

    if (isRed == 1) {
      gridLayout1.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout2.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout3.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout7.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout8.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout11.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout12.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout15.setBackgroundResource(R.drawable.round_grid_red);
      gridLayout16.setBackgroundResource(R.drawable.round_grid_red);
      mapVals.setBackgroundResource(R.drawable.round_grid_red);
    } else {
      gridLayout1.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout2.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout3.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout7.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout8.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout11.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout12.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout15.setBackgroundResource(R.drawable.round_grid_blue);
      gridLayout16.setBackgroundResource(R.drawable.round_grid_blue);
      mapVals.setBackgroundResource(R.drawable.round_grid_blue);
    }
  }
  private View.OnClickListener listener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Button btn = (Button) view;
      int id = btn.getId();

      switch (id) {
        // Power Cell Inner
        case R.id.telePowerCellInnerPortSuccessPlusBtnID:
          Log.d(TAG, "Inside of Rocket Top Cargo success plus");
          if (powerCell_innerPort_success < Defaults.MAX_ROCKET_NUMBER) powerCell_innerPort_success++;
          tcode.setTeleop_powerCell_innerPort_s(powerCell_innerPort_success);
          powerCellInnerPortSuccessNum.setText(String.valueOf(powerCell_innerPort_success));
          saveMatch();
          break;

        case R.id.telePowerCellInnerPortSuccessMinusBtnID:
          Log.d(TAG, "Inside of Rocket Top Cargo success minus");
          if (powerCell_innerPort_success > 0) powerCell_innerPort_success--;
          tcode.setTeleop_powerCell_innerPort_s(powerCell_innerPort_success);
          powerCellInnerPortSuccessNum.setText(String.valueOf(powerCell_innerPort_success));
          saveMatch();
          break;

        case R.id.telePowerCellInnerPortFailurePlusBtnID:
          Log.d(TAG, "Inside of Rocket Top Cargo failure plus");
          if (powerCell_innerPort_failure < Defaults.MAX_ROCKET_NUMBER) powerCell_innerPort_failure++;
          tcode.setTeleop_powerCell_innerPort_f(powerCell_innerPort_failure);
          powerCellInnerPortFailNum.setText(String.valueOf(powerCell_innerPort_failure));
          saveMatch();
          break;

        case R.id.telePowerCellOuterPortInnerMinusBtnID:
          Log.d(TAG, "Inside of Rocket Top Cargo failure minus");
          if (powerCell_innerPort_failure > 0) powerCell_innerPort_failure--;
          tcode.setTeleop_powerCell_innerPort_f(powerCell_innerPort_failure);
          powerCellInnerPortFailNum.setText(String.valueOf(powerCell_innerPort_failure));
          saveMatch();
          break;

        // Power Cell Outer
        case R.id.telePowerCellOuterPortSuccessPlusBtnID:
          Log.d(TAG, "Inside of Rocket Mid Cargo success plus");
          if (powerCell_outerPort_success < Defaults.MAX_ROCKET_NUMBER) powerCell_outerPort_success++;
          tcode.setTeleop_powerCell_outerPort_s(powerCell_outerPort_success);
          powerCellOuterPortSuccessNum.setText(String.valueOf(powerCell_outerPort_success));
          saveMatch();
          break;

        case R.id.telePowerCellOuterPortSuccessMinusBtnID:
          Log.d(TAG, "Inside of Rocket Mid Cargo success minus");
          if (powerCell_outerPort_success > 0) powerCell_outerPort_success--;
          tcode.setTeleop_powerCell_outerPort_s(powerCell_outerPort_success);
          powerCellOuterPortSuccessNum.setText(String.valueOf(powerCell_outerPort_success));
          saveMatch();
          break;

        case R.id.telePowerCellOuterPortFailurePlusBtnID:
          Log.d(TAG, "Inside of Rocket Mid Cargo failure plus");
          if (powerCell_outerPort_failure < Defaults.MAX_ROCKET_NUMBER) powerCell_outerPort_failure++;
          tcode.setTeleop_powerCell_outerPort_f(powerCell_outerPort_failure);
          powerCellOuterPortFailNum.setText(String.valueOf(powerCell_outerPort_failure));
          saveMatch();
          break;

        case R.id.telePowerCellOuterPortFailureMinusBtnID:
          Log.d(TAG, "Inside of Rocket Mid Cargo failure minus");
          if (powerCell_outerPort_failure > 0) powerCell_outerPort_failure--;
          tcode.setTeleop_powerCell_outerPort_f(powerCell_outerPort_failure);
          powerCellOuterPortFailNum.setText(String.valueOf(powerCell_outerPort_failure));
          saveMatch();
          break;

        // Power Cell Bottom
        case R.id.telePowerCellBottomPortSuccessPlusBtnID:
          Log.d(TAG, "Inside of Rocket Bot Cargo success plus");
          if (powerCell_bottomPort_success < Defaults.MAX_ROCKET_NUMBER) powerCell_bottomPort_success++;
          tcode.setTeleop_powerCell_bottomPort_s(powerCell_bottomPort_success);
          powerCellBottomPortSuccessNum.setText(String.valueOf(powerCell_bottomPort_success));
          saveMatch();
          break;

        case R.id.telePowerCellBottomPortSuccessMinusBtnID:
          Log.d(TAG, "Inside of Rocket Bot Cargo success minus");
          if (powerCell_bottomPort_success > 0) powerCell_bottomPort_success--;
          tcode.setTeleop_powerCell_bottomPort_s(powerCell_bottomPort_success);
          powerCellBottomPortSuccessNum.setText(String.valueOf(powerCell_bottomPort_success));
          saveMatch();
          break;

        case R.id.telePowerCellBottomPortFailurePlusBtnID:
          Log.d(TAG, "Inside of Rocket Bot Cargo failure plus");
          if (powerCell_bottomPort_failure < Defaults.MAX_ROCKET_NUMBER) powerCell_bottomPort_failure++;
          tcode.setTeleop_powerCell_bottomPort_f(powerCell_bottomPort_failure);
          powerCellBottomPortFailNum.setText(String.valueOf(powerCell_bottomPort_failure));
          saveMatch();
          break;

        case R.id.telePowerCellBottomPortFailureMinusBtnID:
          Log.d(TAG, "Inside of Rocket Bot Cargo failure minus");
          if (powerCell_bottomPort_failure > 0) powerCell_bottomPort_failure--;
          tcode.setTeleop_powerCell_bottomPort_f(powerCell_bottomPort_failure);
          powerCellBottomPortFailNum.setText(String.valueOf(powerCell_bottomPort_failure));
          saveMatch();
          break;

        default:  Log.e(TAG, "Something is wrong...");
          saveMatch();
          break;
      }
    }
  };
  protected void updateValues() {
    Log.i(TAG, "updateValues: ");
    if (tcode.getTeleopAttempted() == 1){
      teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedYesID);
    }
    else{
      teleControlPanelRGAttempted.check(R.id.teleControlPanelAttemptedNoID);
    }

    if (tcode.getTeleop_stage_1() == 1){
      teleControlPanelRGStage1.check(R.id.teleControlPanelStage1SuccessID);
    }
    else if(tcode.getTeleop_stage_1() == 1){
      teleControlPanelRGStage1.check(R.id.teleControlPanelAttemptedFaliure);
    }
    else{
      teleControlPanelRGStage1.check(R.id.teleControlPanelStage1FailedID);
    }

    if (tcode.getTeleop_stage_2() == 1){
      teleControlPanelRGStage2.check(R.id.teleControlPanelStage2SuccessID);
    }
    else if(tcode.getTeleop_stage_2() == 1){
      teleControlPanelRGStage2.check(R.id.telePositionControlAttemptedFaliure);
    }
    else{
      teleControlPanelRGStage2.check(R.id.teleControlPanelStage2FailedID);
    }
    stage1speed = tcode.getTeleop_stage_1_speed();
    stage2speed = tcode.getTeleop_stage_2_speed();
    localZone1 = tcode.getTele_zone1();
    localZone2 = tcode.getTele_zone2();
    localZone3 = tcode.getTele_zone3();
    powerCell_innerPort_success = tcode.getTeleop_powerCell_innerPort_s();
    powerCell_innerPort_failure = tcode.getTeleop_powerCell_innerPort_f();
    powerCell_outerPort_success = tcode.getTeleop_powerCell_outerPort_s();
    powerCell_outerPort_failure = tcode.getTeleop_powerCell_outerPort_f();
    powerCell_bottomPort_success = tcode.getTeleop_powerCell_bottomPort_s();
    powerCell_bottomPort_failure = tcode.getTeleop_powerCell_bottomPort_f();
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
    Gson gson = new Gson();
    String json = gson.toJson(tcode);

    Log.i(TAG, "onSupportNavigateUp: intent JSON ==> " + json);
    saveMatch();
    onBackPressed();
    return true;
  }
  public void plus_zone_2_shots(View view) {
    localZone2++;
    tcode.plusTeleZone2();
    Toast.makeText(getApplicationContext(), "Number of Zone 2 shots saved to " + localZone2, Toast.LENGTH_SHORT).show();
    String text_for_zone_2 = "" + localZone2;
    tele_zone_2_text.setText(text_for_zone_2);
    saveMatch();
  }
  public void plus_zone_1_shots(View view) {
    localZone1++;
    tcode.plusTeleZone1();
    Toast.makeText(getApplicationContext(), "Number of Zone 1 shots saved to " + localZone1, Toast.LENGTH_SHORT).show();
    String text_for_zone_1 = "" + localZone1;
    tele_zone_1_text.setText(text_for_zone_1);
    saveMatch();
  }
  public void plus_zone_3_shots(View view) {
    localZone3++;
    tcode.plusTeleZone3();
    Toast.makeText(getApplicationContext(), "Number of Zone 3 shots saved to " + localZone3, Toast.LENGTH_SHORT).show();
    String text_for_zone_3 = "" + localZone3;
    tele_zone_3_text.setText(text_for_zone_3);
    saveMatch();
  }
  public void minus_zone_1_shots(View view) {
    if ((localZone1) > 0){
      tcode.minusTeleZone1();
      localZone1--;
    }
    String text_for_zone_1 = "" + localZone1;
    tele_zone_1_text.setText(text_for_zone_1);
    saveMatch();

  }
  public void minus_zone_2_shots(View view) {
    if ((localZone2) > 0){
      tcode.minusTeleZone2();
      localZone2--;
    }
    String text_for_zone_2 = "" + localZone2;
    tele_zone_2_text.setText(text_for_zone_2);
    saveMatch();
}
  public void minus_zone_3_shots(View view) {
    if ((localZone3) > 0){
      tcode.minusTeleZone3();
      localZone3--;
    }
    String text_for_zone_3 = "" + localZone3;
    tele_zone_3_text.setText(text_for_zone_3);
    saveMatch();
  }
}
