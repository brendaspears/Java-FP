package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;      // to transfer/receive data between activities
import android.media.Ringtone;      // to set the ringtone
import android.media.RingtoneManager;
import android.os.Bundle;       // to transfer transfer/receive data between activities
import android.view.View;       // to connect the onClick button ( set as parameter )
import android.widget.TextClock;        // to output real time time.
import android.widget.TimePicker;       // used to select the alarm time
import android.widget.Button;       // to create the button
import java.util.Timer;     // to calculate the real life time
import java.util.TimerTask;
import java.util.*;     // to call the arraylist

public class MainActivity extends AppCompatActivity {


    String curr = "";
    boolean a = false;
    TimePicker alarmTime;
    TextClock currentTime;

    private ArrayList<String> alarmList = new ArrayList<>(10);      // Create an array list


    @Override
    protected void onCreate(Bundle savedInstanceState) {        // used to override the onClick (button) and run()

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveBtn = (Button) findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmList.add(AlarmTime());

            }
        });


        Button listBtn = (Button) findViewById(R.id.listButton);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openList();
            }
        });

        alarmTime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.textClock);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                a = false;
                for (int i = 0; i < alarmList.size(); i++) {
                    if (currentTime.getText().toString().equals(alarmList.get(i)) && a == false && !curr.equals(currentTime.getText().toString())) {
                        r.play();
                    }
                    else {
                        Button stopBtn = (Button) findViewById(R.id.stopButton);
                        stopBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                r.stop();
                                a = true;
                                curr = currentTime.getText().toString();
                            }
                        });
                    }
                }
            }
                                                                                                                                                                       }, 0, 1000);
    }

    public void openList(){     // to open the List.java and send the arraylist
        Intent intent = new Intent(this, List.class);
        try{
            intent.putExtra("array_list", alarmList);
        }catch(NullPointerException ignored){

        }
        startActivity(intent);
    }


    public String AlarmTime(){      // to set the selected alarm time to string

        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();

        String stringAlarmTime, stringAlarmMinutes;

        if (alarmMinutes < 10) {
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        }
        else {
            stringAlarmMinutes = alarmMinutes.toString();
        }

        if (alarmHours > 12) {
            alarmHours = alarmHours - 12;
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" PM");
        }
        else {
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" AM");
        }

        return stringAlarmTime;

    }
}