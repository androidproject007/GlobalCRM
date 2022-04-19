package com.globalcrm.Other;

import android.content.Context;
import android.content.SharedPreferences;

public class Datastorage {

    public static final int INTEGER_KEY = 1;
    public static final int STRING_KEY = 2;
    public static final int FLOAT_KEY = 3;

    public static void WritePreference(String key, String value , Context ctx)
    {
        SharedPreferences sp= ctx.getSharedPreferences("GLOBAL", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString(key, value);
        writer.commit();
    }
    public static void WritePreference(String key, int value , Context ctx)
    {
        SharedPreferences sp= ctx.getSharedPreferences("GLOBAL", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putInt(key, value);
        writer.commit();
    }
    public static void WritePreference(String key, float value , Context ctx)
    {
        SharedPreferences sp= ctx.getSharedPreferences("GLOBAL", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putFloat(key, value);
        writer.commit();
    }

    public static Object ReadFromPreference(String key , int type , Context ctx)
    {
        SharedPreferences sp= ctx.getSharedPreferences("GLOBAL", Context.MODE_PRIVATE);
        Object temp = null;
        if(sp.contains(key)== true){
            if (type == Datastorage.STRING_KEY)
                temp= sp.getString(key,"");
            else if (type == Datastorage.INTEGER_KEY)
                temp = sp.getInt(key,0);
            else if (type == Datastorage.FLOAT_KEY) {
                temp = sp.getFloat(key, 1.0f);
            }
        }
        else
            return temp;
        return  temp;
    }
}
