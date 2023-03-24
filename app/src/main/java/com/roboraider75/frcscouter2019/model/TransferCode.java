package com.roboraider75.frcscouter2019.model;

import android.util.Log;

public class TransferCode {
  private static String TAG = "TransferCode";

  private int matchNumber = 0;    // 7 bits    (max 127)
  private int teamNumber = 0;     // 14 bits   (max 16383)
  private int isRed = 0;         // 1 bit     (0=blue, 1=red)

  private int auto_cross_line = 0;   // 1 bit
  private int auto_powerCell_innerPort_s = 0; // 4 bits
  private int auto_powerCell_outerPort_s = 0; // 4 bits
  private int auto_powerCell_bottomPort_s = 0; // 4 bits

  private int auto_powerCell_innerPort_f = 0; // 4 bits
  private int auto_powerCell_outerPort_f = 0; // 4 bits
  private int auto_powerCell_bottomPort_f = 0; // 4 bits

  private int teleop_powerCell_innerPort_s = 0; // 4 bits
  private int teleop_powerCell_outerPort_s = 0; // 4 bits
  private int teleop_powerCell_bottomPort_s = 0; // 4 bits

  private int teleop_powerCell_innerPort_f = 0; // 4 bits
  private int teleop_powerCell_outerPort_f = 0; // 4 bits
  private int teleop_powerCell_bottomPort_f = 0; // 4 bits

  private int teleopAttempt = 0; //1 bit
  private int teleop_stage_1 = 0; //1 bit
  private int teleop_stage_1_speed = 0; // 1 bit
  private int teleop_stage_2 = 0; //1 bit
  private int teleop_stage_2_speed = 0; // 1 bit

  private int endgame_park_s = 0;        // 1 bit
  private int endgame_park_f = 0;        // 1 bit
  private int endgame_hang_s = 0;        // 1 bit
  private int endgame_hang_f = 0;        // 1 bit

  private int endgame_hangPos = 0;// 0=side   1=mid
  private int endgame_level = 0;
  private int endgame_did_not_attempt = 0;   // 1 bit

  private int auto_zone1 = 0;// 4 bits
  private int auto_zone2 = 0;// 4 bits
  private int auto_zone3 = 0;// 4 bits

  private int tele_zone1 = 0;// 4 bits
  private int tele_zone2 = 0;// 4 bits
  private int tele_zone3 = 0;// 4 bits

  private int zone1 = 0;// 4 bits
  private int zone2 = 0;// 4 bits
  private int zone3 = 0;// 4 bits

  private String[] map = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
  private static String mapString = "ABCDEFGHJKLMNOPRSTUWXYZ123456789";

  public TransferCode() {
    Log.i(TAG, "Inside TransferCode()...");
  }
  public int getTeamNumber() {
    return teamNumber;
  }
  public void setTeamNumber(int teamNumber) {
    this.teamNumber = teamNumber;
  }
  public int getMatchNumber() {
    return matchNumber;
  }
  public void setMatchNumber(int matchNumber) {
    this.matchNumber = matchNumber;
  }
  public int getIsRed() {
    return isRed;
  }
  public void setIsRed(int isRed) {
    this.isRed = isRed;
  }
  public void plusAutoZone1() {
    auto_zone1++;
    zone1 = tele_zone1 + auto_zone1;
  }
  public void plusAutoZone2() {
    auto_zone2++;
    zone2 = tele_zone2 + auto_zone2;
  }
  public void plusAutoZone3() {
    auto_zone3++;
    zone3 = tele_zone3 + auto_zone3;
  }
  public void minusAutoZone1() {
    auto_zone1--;
    zone1 = tele_zone1 + auto_zone1;
  }
  public void minusAutoZone2() {
    auto_zone2--;
    zone2 = tele_zone2 + auto_zone2;
  }
  public void minusAutoZone3() {
    auto_zone3--;
    zone3 = tele_zone3 + auto_zone3;
  }
  public void plusTeleZone1() {
    tele_zone1++;
    zone1 = tele_zone1 + auto_zone1;
  }
  public void plusTeleZone2() {
    tele_zone2++;
    zone2 = tele_zone2 + auto_zone2;
  }
  public void plusTeleZone3() {
    tele_zone3++;
    zone3 = tele_zone3 + auto_zone3;
  }
  public void minusTeleZone1() {
    tele_zone1--;
    zone1 = tele_zone1 + auto_zone1;
  }
  public void minusTeleZone2() {
    tele_zone2--;
    zone2 = tele_zone2 + auto_zone2 ;
  }
  public void minusTeleZone3() {
    tele_zone3--;
    zone3 = tele_zone3 + auto_zone3 ;
  }
  public int getAuto_zone1() {
    return auto_zone1;
  }
  public int getAuto_zone2() {
    return auto_zone2;
  }
  public int getAuto_zone3() {
    return auto_zone3;
  }
  public int getTele_zone1() {
    return tele_zone1;
  }
  public int getTele_zone2() {
    return tele_zone2;
  }
  public int getTele_zone3() {
    return tele_zone3;
  }
  public void setAuto_zone1(int auto_zone1) {
    this.auto_zone1 = auto_zone1;
  }
  public void setAuto_zone2(int auto_zone2) {
    this.auto_zone2 = auto_zone2;
  }
  public void setAuto_zone3(int auto_zone3) {
    this.auto_zone3 = auto_zone3;
  }
  public void setTele_zone1(int tele_zone1) {
    this.tele_zone1 = tele_zone1;
  }
  public void setTele_zone2(int tele_zone2) {
    this.tele_zone2 = tele_zone2;
  }
  public void setTele_zone3(int tele_zone3) {
    this.tele_zone3 = tele_zone3;
  }
  public int getZone1() {
    return zone1;
  }
  public int getZone2() {
    return zone2;
  }
  public int getZone3() {
    return zone3;
  }

  public int getTeleop_stage_1_speed() {
    return teleop_stage_1_speed;
  }
  public void setTeleop_stage_1_speed(int teleop_stage_1_speed) {
    this.teleop_stage_1_speed = teleop_stage_1_speed;
  }
  public int getTeleop_stage_2_speed() {
    return teleop_stage_2_speed;
  }
  public void setTeleop_stage_2_speed(int teleop_stage_2_speed) {
    this.teleop_stage_2_speed = teleop_stage_2_speed;
  }
  public int getAuto_cross_line() {
    return auto_cross_line;
  }
  public void setAuto_cross_line(int sandstorm_cross_line) {
    this.auto_cross_line = sandstorm_cross_line;
  }
  public int getAuto_powerCell_innerPort_s() {
    return auto_powerCell_innerPort_s;
  }
  public void setAuto_powerCell_innerPort_s(int auto_powerCell_innerPort_s) {
    this.auto_powerCell_innerPort_s = auto_powerCell_innerPort_s;
  }
  public int getAuto_powerCell_outerPort_s() {
    return auto_powerCell_outerPort_s;
  }
  public void setAuto_powerCell_outerPort_s(int auto_powerCell_outerPort_s) {
    this.auto_powerCell_outerPort_s = auto_powerCell_outerPort_s;
  }
  public int getAuto_powerCell_bottomPort_s() {
    return auto_powerCell_bottomPort_s;
  }
  public void setAuto_powerCell_bottomPort_s(int auto_powerCell_bottomPort_s) {
    this.auto_powerCell_bottomPort_s = auto_powerCell_bottomPort_s;
  }
  public int getAuto_powerCell_innerPort_f() {
    return auto_powerCell_innerPort_f;
  }
  public void setAuto_powerCell_innerPort_f(int auto_powerCell_innerPort_f) {
    this.auto_powerCell_innerPort_f = auto_powerCell_innerPort_f;
  }
  public int getAuto_powerCell_outerPort_f() {
    return auto_powerCell_outerPort_f;
  }
  public void setAuto_powerCell_outerPort_f(int auto_powerCell_outerPort_f) {
    this.auto_powerCell_outerPort_f = auto_powerCell_outerPort_f;
  }
  public int getAuto_powerCell_bottomPort_f() {
    return auto_powerCell_bottomPort_f;
  }
  public void setAuto_powerCell_bottomPort_f(int auto_powerCell_bottomPort_f) {
    this.auto_powerCell_bottomPort_f = auto_powerCell_bottomPort_f;
  }
  public int getTeleop_powerCell_innerPort_s() {
    return teleop_powerCell_innerPort_s;
  }
  public void setTeleop_powerCell_innerPort_s(int teleop_powerCell_innerPort_s) {
    this.teleop_powerCell_innerPort_s = teleop_powerCell_innerPort_s;
  }
  public int getTeleop_powerCell_outerPort_s() {
    return teleop_powerCell_outerPort_s;
  }
  public void setTeleop_powerCell_outerPort_s(int teleop_powerCell_outerPort_s) {
    this.teleop_powerCell_outerPort_s = teleop_powerCell_outerPort_s;
  }
  public int getTeleop_powerCell_bottomPort_s() {
    return teleop_powerCell_bottomPort_s;
  }
  public void setTeleop_powerCell_bottomPort_s(int teleop_powerCell_bottomPort_s) {
    this.teleop_powerCell_bottomPort_s = teleop_powerCell_bottomPort_s;
  }
  public int getTeleop_powerCell_innerPort_f() {
    return teleop_powerCell_innerPort_f;
  }
  public void setTeleop_powerCell_innerPort_f(int teleop_powerCell_innerPort_f) {
    this.teleop_powerCell_innerPort_f = teleop_powerCell_innerPort_f;
  }
  public int getTeleop_powerCell_outerPort_f() {
    return teleop_powerCell_outerPort_f;
  }
  public void setTeleop_powerCell_outerPort_f(int teleop_powerCell_outerPort_f) {
    this.teleop_powerCell_outerPort_f = teleop_powerCell_outerPort_f;
  }
  public int getTeleop_powerCell_bottomPort_f() {
    return teleop_powerCell_bottomPort_f;
  }
  public void setTeleop_powerCell_bottomPort_f(int teleop_rocket_bot_cargo_f) {
    this.teleop_powerCell_bottomPort_f = teleop_rocket_bot_cargo_f;
  }
  public int getTeleopAttempted() {
    return teleopAttempt;
  }
  public void setTeleopAttempted(int teleopAttempt) {
    this.teleopAttempt = teleopAttempt;
  }
  public int getTeleop_stage_1() {
    return teleop_stage_1;
  }
  public void setTeleop_stage_1(int teleop_stage_1) {
    this.teleop_stage_1 = teleop_stage_1;
  }
  public int getTeleop_stage_2() {
      return teleop_stage_2;
  }
  public void setTeleop_stage_2(int teleop_stage_2) {
      this.teleop_stage_2 = teleop_stage_2;
  }

  public int getEndgame_hang_s() {
      return endgame_hang_s;
  }
  public void setEndgame_hang_s(int endgame_hang_s) {
    this.endgame_hang_s = endgame_hang_s;
  }
  public int getEndgame_hang_f() {
    return endgame_hang_f;
  }
  public void setEndgame_hang_f(int endgame_hang_f) {
    this.endgame_hang_f = endgame_hang_f;
  }
  public int getEndgame_park_f() {
    return endgame_park_f;
  }
  public void setEndgame_park_f(int endgame_park_f) {
    this.endgame_park_f = endgame_park_f;
  }
  public int getEndgame_park_s() {
    return endgame_park_s;
  }
  public void setEndgame_park_s(int endgame_park_s) {
    this.endgame_park_s = endgame_park_s;
  }
  public int getEndgame_hangPos() {
    return endgame_hangPos;
  }
  public void setEndgame_hangPos(int endgame_hangPos) {
    this.endgame_hangPos = endgame_hangPos;
  }
  public int getEndgame_level() {
    return endgame_level;
  }
  public void setEndgame_level(int endgame_level) {
    this.endgame_level = endgame_level;
  }
  public int getEndgame_did_not_attempt() {
    return endgame_did_not_attempt;
  }
  public void setEndgame_did_not_attempt(int endgame_did_not_attempt) {
    this.endgame_did_not_attempt = endgame_did_not_attempt;
  }

  public String[] getMap() {
    return map;
  }
  public void setMap(String[] map) {
    this.map = map;
  }
  public static String getMapString() {
    return mapString;
  }
  public static void setMapString(String mapString) {
    TransferCode.mapString = mapString;
  }
  private int getPos(String s) {
    int pos = 0;
    for (int i = 0; i < 32; i++) {
      if (s.equals(map[i])) {
        pos = i;
        return pos;
      }
    }
    return pos;
  }
  private TransferCode Decode(String code) {//loop thru code string, make binary string, use binary string to assign each field value
    int pos = 0;
    StringBuilder bitPattern = new StringBuilder();
    while (pos < code.length()) {
      String s = code.substring(pos, pos + 1);
      int l = getPos(s);
      StringBuilder b2 = new StringBuilder(Integer.toBinaryString(l));
      while (b2.length() < 5)
        b2.insert(0, "0");
      bitPattern.append(b2);
      pos++;
    }
    TransferCode tc = new TransferCode();
    GetFieldsFromBinaryString(tc, bitPattern.toString());
    return tc;
  }
  public String getBinaryString() {
    String s = "";
    s += TransferCode.GetIntBinaryString(matchNumber).substring(25, 32);
    s += TransferCode.GetIntBinaryString(teamNumber).substring(18, 32);
    s += TransferCode.GetIntBinaryString(isRed).substring(31, 32);

    s += TransferCode.GetIntBinaryString(auto_cross_line).substring(31, 32);

    s += TransferCode.GetIntBinaryString(auto_powerCell_innerPort_s).substring(26, 32);
    s += TransferCode.GetIntBinaryString(auto_powerCell_outerPort_s).substring(26, 32);
    s += TransferCode.GetIntBinaryString(auto_powerCell_bottomPort_s).substring(26, 32);

    s += TransferCode.GetIntBinaryString(auto_powerCell_innerPort_f).substring(26, 32);
    s += TransferCode.GetIntBinaryString(auto_powerCell_outerPort_f).substring(26, 32);
    s += TransferCode.GetIntBinaryString(auto_powerCell_bottomPort_f).substring(26, 32);

    s += TransferCode.GetIntBinaryString(teleopAttempt).substring(31, 32);
    s += TransferCode.GetIntBinaryString(teleop_stage_1).substring(30, 32);
    s += TransferCode.GetIntBinaryString(teleop_stage_1_speed).substring(28, 32);
    s += TransferCode.GetIntBinaryString(teleop_stage_2).substring(30, 32);
    s += TransferCode.GetIntBinaryString(teleop_stage_2_speed).substring(28, 32);

    s += TransferCode.GetIntBinaryString(teleop_powerCell_innerPort_s).substring(26, 32);
    s += TransferCode.GetIntBinaryString(teleop_powerCell_outerPort_s).substring(26, 32);
    s += TransferCode.GetIntBinaryString(teleop_powerCell_bottomPort_s).substring(26, 32);

    s += TransferCode.GetIntBinaryString(teleop_powerCell_innerPort_f).substring(26, 32);
    s += TransferCode.GetIntBinaryString(teleop_powerCell_outerPort_f).substring(26, 32);
    s += TransferCode.GetIntBinaryString(teleop_powerCell_bottomPort_f).substring(26, 32);

    s += TransferCode.GetIntBinaryString(endgame_hang_s).substring(31, 32);
    s += TransferCode.GetIntBinaryString(endgame_hang_f).substring(31, 32);
    s += TransferCode.GetIntBinaryString(endgame_hangPos).substring(31, 32);

    s += TransferCode.GetIntBinaryString(endgame_park_s).substring(31, 32);
    s += TransferCode.GetIntBinaryString(endgame_park_f).substring(31, 32);

    s += TransferCode.GetIntBinaryString(endgame_level).substring(31, 32);

//    s += TransferCode.GetIntBinaryString(endgame_did_not_attempt).substring(31, 32);

    s += TransferCode.GetIntBinaryString(zone1).substring(25,32);
    s += TransferCode.GetIntBinaryString(zone2).substring(25,32);
    s += TransferCode.GetIntBinaryString(zone3).substring(25,32);

    s += TransferCode.GetIntBinaryString(0).substring(27 , 32);//filler
    return s;
  }
  private static String GetIntBinaryString(int n) {
    char[] b = new char[32];
    int pos = 31;
    int i = 0;

    while (i < 32) {
      if ((n & (1 << i)) != 0) {
        b[pos] = '1';
      } else {
        b[pos] = '0';
      }
      pos--;
      i++;
    }
    return new String(b);
  }
  public String GenerateCode(String bstring) {
    //loop thru , take 5 bits at a time, convert to int and lookup character, handle last char may be less than 5
    int pos = 0;
    StringBuilder code = new StringBuilder();
    while (pos < bstring.length()) {
      int l = 5;
      int nl = bstring.length() - pos;
      if (nl < 5)
        l = nl;
      String s = bstring.substring(pos, pos + l);
      int a = Integer.parseInt(s, 2);
      code.append(map[a]);
      pos = pos + 5;
    }
    return code.toString().toUpperCase();
  }
  private void GetFieldsFromBinaryString(TransferCode tc, String src) {
    int offset = 0;
    tc.matchNumber = Integer.parseInt(src.substring(0, 7), 2);
    offset = offset + 7;
    tc.teamNumber = Integer.parseInt(src.substring(offset, offset + 14), 2);
    offset = offset + 14;
    tc.isRed = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;

    tc.auto_cross_line = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;

    tc.auto_powerCell_innerPort_s = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.auto_powerCell_outerPort_s = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.auto_powerCell_bottomPort_s = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;

    tc.auto_powerCell_innerPort_f = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.auto_powerCell_outerPort_f = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.auto_powerCell_bottomPort_f = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;

    tc.teleopAttempt = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;
    tc.teleop_stage_1 = Integer.parseInt(src.substring(offset, offset + 2), 2);
    offset+=2;
    tc.teleop_stage_1_speed = Integer.parseInt(src.substring(offset, offset + 4), 2);
    offset = offset + 4;
    tc.teleop_stage_2 = Integer.parseInt(src.substring(offset, offset + 2), 2);
    offset+=2;
    tc.teleop_stage_2_speed = Integer.parseInt(src.substring(offset, offset + 4), 2);
    offset = offset + 4;

    tc.teleop_powerCell_innerPort_s = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.teleop_powerCell_outerPort_s = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.teleop_powerCell_bottomPort_s = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;

    tc.teleop_powerCell_innerPort_f = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.teleop_powerCell_outerPort_f = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;
    tc.teleop_powerCell_bottomPort_f = Integer.parseInt(src.substring(offset, offset + 6), 2);
    offset = offset + 6;

//    tc.endgame_did_not_attempt = Integer.parseInt(src.substring(offset, offset + 1), 2);
//    offset++;
    tc.endgame_hang_s = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;
    tc.endgame_hang_f = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;
    tc.endgame_hangPos = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;
    tc.endgame_park_s = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;
    tc.endgame_park_f = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;
    tc.endgame_level = Integer.parseInt(src.substring(offset, offset + 1), 2);
    offset++;

    tc.zone1 = Integer.parseInt(src.substring(offset,offset + 7), 2);
    offset = offset + 7;
    tc.zone2 = Integer.parseInt(src.substring(offset,offset + 7), 2);
    offset = offset + 7;
    tc.zone3 = Integer.parseInt(src.substring(offset,offset + 7), 2);
    offset = offset + 7;
  }
  public static void main(String[] args) {
    TransferCode t = new TransferCode();
    t.setTeamNumber(75);
    t.setMatchNumber(23);
    t.setIsRed(0);
    t.setAuto_cross_line(1);
    t.setAuto_powerCell_bottomPort_s(1);
    t.setAuto_powerCell_outerPort_s(2);
    t.setEndgame_hang_s(1);
    String n = t.getBinaryString();
    String s = t.GenerateCode(n);
    TransferCode tc2 = t.Decode(s);
    boolean b = t.issame(tc2);
    if (b)
      System.out.println("they are the same");
    System.out.println(s);
  }
  private boolean issame(TransferCode tc) {
    if (matchNumber != tc.getMatchNumber())
      return false;
    if (teamNumber != tc.getTeamNumber())
      return false;
    if (teamNumber != tc.getIsRed())
      return false;
    if (auto_cross_line != tc.auto_cross_line)
      return false;
    if (auto_powerCell_innerPort_s != tc.auto_powerCell_innerPort_s)
      return false;
    if (auto_powerCell_outerPort_s != tc.auto_powerCell_outerPort_s)
      return false;
    if (auto_powerCell_bottomPort_s != tc.auto_powerCell_bottomPort_s)
      return false;
    if (auto_powerCell_innerPort_f != tc.auto_powerCell_innerPort_f)
    return false;
    if (auto_powerCell_outerPort_f != tc.auto_powerCell_outerPort_f)
      return false;
    if (auto_powerCell_bottomPort_f != tc.auto_powerCell_bottomPort_f)
      return false;
//    if (teleopAttempt != tc.teleopAttempt)
//      return false;
    if (teleop_stage_1 != tc.teleop_stage_1)
      return false;
    if (teleop_stage_1_speed != tc.teleop_stage_1_speed)
      return false;
    if (teleop_stage_2 != tc.teleop_stage_2)
      return false;
    if (teleop_stage_2_speed != tc.teleop_stage_2_speed)
      return false;
    if (teleop_powerCell_innerPort_s != tc.teleop_powerCell_innerPort_s)
      return false;
    if (teleop_powerCell_outerPort_s != tc.teleop_powerCell_outerPort_s)
      return false;
    if (teleop_powerCell_bottomPort_s != tc.teleop_powerCell_bottomPort_s)
      return false;
    if (teleop_powerCell_innerPort_f != tc.teleop_powerCell_innerPort_f)
      return false;
    if (teleop_powerCell_outerPort_f != tc.teleop_powerCell_outerPort_f)
      return false;
    if (teleop_powerCell_bottomPort_f != tc.teleop_powerCell_bottomPort_f)
      return false;
    if (endgame_hang_s != tc.endgame_hang_s)
      return false;
    if (endgame_hang_f != tc.endgame_hang_f)
      return false;
    if (endgame_park_s != tc.endgame_park_s)
      return false;
    if (endgame_park_f != tc.endgame_park_f)
      return false;
    if (endgame_level != tc.endgame_level)
      return false;
    if (endgame_did_not_attempt != tc.endgame_did_not_attempt)
      return false;
    if (zone1 != tc.zone1){
      return false;
    }
    if (zone2 != tc.zone1){
      return false;
    }
    if (zone3 != tc.zone3){
      return false;
    }
    return true;
  }
  private int rangeCheck(int low, int high, int val) {
    if (val > high)
      val = high;
    if ((val < low))
      val = low;
    return val;
  }@Override
  public String toString() {
    return "TransferCode{" +
        ", matchNumber=" + matchNumber +
        "teamNumber=" + teamNumber +
        ", isRed=" + isRed +
        ", sandstorm_cross_line=" + auto_cross_line +
        ", auto_powerCell_innerPort_s=" + auto_powerCell_innerPort_s +
        ", auto_powerCell_outerPort_s=" + auto_powerCell_outerPort_s +
        ", auto_powerCell_bottomPort_s=" + auto_powerCell_bottomPort_s +
        ", auto_powerCell_innerPort_f=" + auto_powerCell_innerPort_f +
        ", auto_powerCell_outerPort_f=" + auto_powerCell_outerPort_f +
        ", auto_powerCell_bottomPort_f=" + auto_powerCell_bottomPort_f +
        ", teleopAttempt=" + teleopAttempt +
        ", teleop_stage_1=" + teleop_stage_1 +
        ", teleop_stage_1_speed=" + teleop_stage_1_speed +
        ", teleop_stage_2=" + teleop_stage_2 +
        ", teleop_stage_2_speed=" + teleop_stage_2_speed +
        ", teleop_powerCell_innerPort_s=" + teleop_powerCell_innerPort_s +
        ", teleop_powerCell_outerPort_s=" + teleop_powerCell_outerPort_s +
        ", teleop_powerCell_bottomPort_s=" + teleop_powerCell_bottomPort_s +
        ", teleop_powerCell_innerPort_f=" + teleop_powerCell_innerPort_f +
        ", teleop_powerCell_outerPort_f=" + teleop_powerCell_outerPort_f +
        ", teleop_rocket_bot_cargo_f=" + teleop_powerCell_bottomPort_f +
//        ", endgame_did_not_attempt=" + endgame_did_not_attempt +
        ", endgame_hang_s=" + endgame_hang_s +
        ", endgame_hang_f=" + endgame_hang_f +
        ", endgame_hang_pos=" + endgame_hangPos +
        ", endgame_park_s=" + endgame_park_s +
        ", endgame_park_s=" + endgame_park_f +
        ", endgame_level =" + endgame_level +
        ", zone1=" + zone1 +
        ", zone2=" + zone2 +
        ", zone2=" + zone3 +
        '}';
  }
  public String toComma() {
    return matchNumber +
        ", " + teamNumber +
        ", " + isRed +
        ", " + auto_cross_line +
        ", " + auto_powerCell_innerPort_s +
        ", " + auto_powerCell_outerPort_s +
        ", " + auto_powerCell_bottomPort_s +
        ", " + auto_powerCell_innerPort_f +
        ", " + auto_powerCell_outerPort_f +
        ", " + auto_powerCell_bottomPort_f +
        ", " + teleopAttempt +
        ", " + teleop_stage_1 +
        ", " + teleop_stage_1_speed +
        ", " + teleop_stage_2 +
        ", " + teleop_stage_2_speed +
        ", " + teleop_powerCell_innerPort_s +
        ", " + teleop_powerCell_outerPort_s +
        ", " + teleop_powerCell_bottomPort_s +
        ", " + teleop_powerCell_innerPort_f +
        ", " + teleop_powerCell_outerPort_f +
        ", " + teleop_powerCell_bottomPort_f +
//        ", " + endgame_did_not_attempt +
        ", " + endgame_hang_s +
        ", " + endgame_hang_f +
        ", " + endgame_park_s +
        ", " + endgame_park_f +
        ", " + endgame_level +
        ", " + zone1 +
        ", " + zone2 +
        ", " + zone3;
  }
}
