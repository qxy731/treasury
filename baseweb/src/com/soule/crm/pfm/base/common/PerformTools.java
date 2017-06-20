package com.soule.crm.pfm.base.common;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;

public class PerformTools
{
  public static java.sql.Date buildSqlDate(){
    java.sql.Date today;
    try{
      SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
      java.util.Date utilDate = sdfrmt.parse(sdfrmt.format(new java.util.Date()));
      today = new java.sql.Date(utilDate.getTime());
    }catch (ParseException e) {
      today = new java.sql.Date(System.currentTimeMillis());
    }
    return today;
  }

  public static BitSet buildBitSet(String stream)
  {
    if (stream == null) {
      return new BitSet(16);
    }
    int len = stream.length();
    BitSet bits = new BitSet(len);
    for (int i = 0; i < len; ++i) {
      bits.set(i + 1, stream.charAt(i) == '1');
    }
    return bits;
  }

  public static String setStatusBit(int bit, String status) {
    if (status == null)
      throw new IllegalArgumentException("The argument 'status' must not be null");
    if (status.length() < bit) {
      throw new IllegalArgumentException("The argument 'bit' must be less than " + status.length());
    }

    return status.substring(0, bit - 1) + 
      "1" + status.substring(bit);
  }

  public static String clearStatusBit(int bit, String status) {
    if (status == null)
      throw new IllegalArgumentException("The argument 'status' must not be null");
    if (status.length() < bit) {
      throw new IllegalArgumentException("The argument 'bit' must be less than " + status.length());
    }

    return status.substring(0, bit - 1) + 
      "0" + status.substring(bit);
  }

  public static void main(String[] args) throws Exception
  {
    String str = "00000000";
    System.out.println(str);
    System.out.println(setStatusBit(2, str));
    System.out.println(setStatusBit(3, str));
    System.out.println(setStatusBit(4, str));
    BitSet bits = new BitSet(8);
    int i = 0; for (int len = str.length(); i < len; ++i) {
      boolean bit = str.charAt(i) == '1';
      bits.set(i + 1, bit);
       System.out.println(i + "   " + bit);
     }
     System.out.println(bits);
 }
 }