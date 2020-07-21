package com.gzeinnumer.splitarraytwopartsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_";

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int count=4; //2=12 3=8 4=6 6=4
        String[] defCodeTime = {
                "000",
                "001","002","003","004","005","006","007","008","009","010",
                "011","012","013","014","015","016","017","018","019","020",
                "021","022","023"
        };

        int totalJam=defCodeTime.length;
        int bagi =totalJam/count; //D1 12

        String[][] codeTime = new String[bagi][count];
        int countDefCodeTime = 0;
        for (int j=0; j<codeTime.length; j++){
            for (int k=0; k<codeTime[j].length; k++){
                if (codeTime[j][k]==null){
                    codeTime[j][k] = defCodeTime[countDefCodeTime];
                    countDefCodeTime++;
                }
            }
        }

        for (int j=0; j<codeTime.length; j++){
            for (int k=0; k<codeTime[j].length; k++){
                Log.d(TAG, "debug: "+j+"_"+k+"_"+codeTime[j][k]);
            }
        }

        int plus = 0;
        plus = plus*3600000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+07:00"));
        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()+plus));
        String timeInString;
        if (Integer.parseInt(time) >=0 && Integer.parseInt(time) <=9){
            timeInString = "00"+time;
        } else {
            timeInString = "0"+time;
        }
        //jika hp menggunakan format 24 maka outputnya akan jadi 0001
        //jika hp menggunakan format 12 maka outputnya akan jadi 001
        //solusi nya hapus string pertama
        Log.d(TAG, "timeInString: "+timeInString);
        if (timeInString.length()==4){
            timeInString=timeInString.substring(1); //dari 0001 akan jadi 001
        }

        int indexD1 = -1;
        int indexD2 = -1;
        for (int j=0; j<codeTime.length; j++){
            for (int k=0; k<codeTime[j].length; k++){
                if (timeInString.equals(codeTime[j][k])){
                    indexD1=j;
                    indexD2=k;
                }
            }
        }
        Log.d(TAG, "index d1/d2: "+ indexD1+"/"+indexD2);
    }
}