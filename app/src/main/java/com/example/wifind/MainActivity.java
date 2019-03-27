package com.example.wifind;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.wifi.WifiManager;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    public void checkstrength(View view){
        
        new CountDownTimer(30000, 1000) {
            TextView tv = (TextView)findViewById(R.id.strength_text);
            public void onTick(long millisUntilFinished) {
                // txt.setText(wifi_Info());
                tv.setText(check_strengthfunc());
            }

            public void onFinish() {
                tv.setText("done!");
            }
        }.start();
    }

    public String check_strengthfunc(){
        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int rssi = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(rssi, 5);
        System.out.println("Level is " + level + " out of 5");
        String lol = "Wifi level is "+rssi;
        return lol;
    }

    public void savetofile(View v) throws IOException {

        new CountDownTimer(30000, 1000) {
            String file_string = "";
            Date currentTime;
            /*File directory = getApplicationContext().getFilesDir();
            File file = new File(directory, "strength");*/
            FileOutputStream outputStream=null;
            String filename = "strength_file";
            FileInputStream inputStream=null;
            byte[] b;
            TextView txt = (TextView) findViewById(R.id.fileview);
            public void onTick(long millisUntilFinished) {
                currentTime = Calendar.getInstance().getTime();
                file_string += currentTime+"\n"+ check_strengthfunc()+"\n\n"; //calls wifi
                Toast.makeText(MainActivity.this,"READING STRENGTH",Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                try{
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(file_string.getBytes());
                    if (outputStream!=null){ outputStream.close();}
                    //Toast.makeText(MainActivity.this,"SAVED at "+getFilesDir()+"/"+filename,Toast.LENGTH_LONG).show();

                    inputStream = openFileInput(filename);
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine())!=null)
                    {
                        sb.append(text).append('\n');
                    }
                    txt.setText(sb.toString());

                    if (inputStream!=null){inputStream.close();}
                //Toast.makeText(MainActivity.this,"SAVED",Toast.LENGTH_SHORT).show();
                }
                catch (IOException e){e.printStackTrace();}
            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
