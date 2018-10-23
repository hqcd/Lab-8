//Quinten Whitaker
//Lab 6 - Random Character Generator
//CS 4322
//Created on 9-28-18

package com.example.duong.lab8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class GenerateCharacterService extends Service {

    private boolean isGeneratorOn;
    private char myRandomChar;
    private final String TAG = "RandomCharService: ";


    public GenerateCharacterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "In OnStartCommand Thread ID is "+Thread.currentThread().getId());
        isGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomGenerator();
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenerator();
        Log.i(TAG, "Service Destroyed");
    }

    private void stopRandomGenerator()
    {
        isGeneratorOn = false;
    }

    private void startRandomGenerator()
    {
        while(isGeneratorOn)
        {
            try
            {
                Thread.sleep(1000);
                if(isGeneratorOn)
                {
                    Random r = new Random();
                    char c = (char)(r.nextInt(26) + 'a');
                    myRandomChar = c;
                    Intent i = new Intent();
                    i.setAction("com.duong.lab8.testBR");
                    i.putExtra("random", c);
                    sendBroadcast(i);
                    Log.i(TAG, "Random char is " + myRandomChar);

                }
            }
            catch (InterruptedException e)
            {
                Log.i(TAG, "Thread Interrupted");
            }
        }
    }

    private char getRandom()
    {
        return myRandomChar;
    }
}
