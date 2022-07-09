package com.kakaopaysec.common.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Util {
	static public JsonObject convertStringToJsonObj(String input) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		JsonObject jsonObj = gson.fromJson(input, JsonObject.class);
		return jsonObj;
	}
	
	static public String getCurrentDate(String format) {
		return new SimpleDateFormat(format,Locale.KOREA).format(new Date());
	}
	
	static public String parseDouble(String num) throws NumberFormatException {
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		return format.format(Double.parseDouble(num));
	}
	
    public static boolean isNumber(String str){
        String expr = "^[-+]?(0|[1-9][0-9]*)(\\.[0-9]+)?([eE][-+]?[0-9]+)?$";
        Pattern pattern = Pattern.compile(expr);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public static String checkDateFormat(String date) {
    	String expr = "([0-9]{4})([0-9]{2})([0-9]{2})";
    	Pattern pattern = Pattern.compile(expr);
        Matcher matcher = pattern.matcher(date);
        if(matcher.matches()) return matcher.group(1)+"-"+matcher.group(2)+"-"+matcher.group(3);
        
        expr = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
        pattern = Pattern.compile(expr);
        matcher = pattern.matcher(date);
        
        if(matcher.matches()) return date;
        else return "error";
    }
}
