package com.example.duong.lab8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    EditText et1;
    @Override
    public void onReceive(Context context, Intent intent) {
        char c = intent.getCharExtra("random", '\u0000');
        String s = String.valueOf(c);
        et1 = (EditText)MainActivity.getInst().findViewById(R.id.editText1);
        et1.setText(s);
    }
}
