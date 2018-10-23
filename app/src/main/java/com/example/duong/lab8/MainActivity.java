/*
* Name: Quinten Whitaker
* Date Created: 10/16/18
* Assignment: Lab 8 - Random Characters using Broadcast Receiver
*/
package com.example.duong.lab8;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2;
    EditText et1;
    MyReceiver myReceiver = new MyReceiver();
    Intent serviceIntent;
    private static MainActivity ins;
    public static MainActivity getInst()
    {
        return ins;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ins = this;
        bt1 = (Button)findViewById(R.id.button1);
        bt2 = (Button)findViewById(R.id.button2);
        et1 = (EditText)findViewById(R.id.editText1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(getApplicationContext(), GenerateCharacterService.class);
                startService(serviceIntent);
                Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(getApplicationContext(), GenerateCharacterService.class);
                stopService(serviceIntent);
                Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
            }
        });

        IntentFilter intentFilter = new IntentFilter("com.duong.lab8.testBR");
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
