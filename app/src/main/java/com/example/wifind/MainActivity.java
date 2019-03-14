package com.example.wifind;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.wifi.WifiManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    public String checkstrength(){
        //Intent intent = new Intent(MainActivity.this,wifipage.class);
        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Log.i("ANikt","in hello");
        //startActivity(intent);
        int rssi = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(rssi, 5);
        System.out.println("Level is " + level + " out of 5");
        String lol = "Wifi level is "+rssi;
        Log.i("baap","baap");
        return lol;
        //tv.setText("Wifi Level is "+rssi+"");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(30000, 1000) {
            TextView tv = (TextView)findViewById(R.id.strength_text);
            public void onTick(long millisUntilFinished) {
               // txt.setText(wifi_Info());
               tv.setText(checkstrength());
            }

            public void onFinish() {
                tv.setText("done!");
            }
        }.start();

    }
}
