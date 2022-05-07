package com.example.scorebook;

import android.content.Context;

public class ConverterHelper {
    public int convertDPS(int dps, Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
         return (int) (dps * scale + 0.5f);
    }
}
