package com.magdamiu.androidplayground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int CALENDAR_WRITE_REQUIRED = 22;
    private static final int WRITE_EXTERNAL_STORAGE_REQUIRED = 23;

    private CalendarManagement calendarManagement;
    private CSVFileManagement csvFileManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarManagement = new CalendarManagement();
        csvFileManagement = new CSVFileManagement();
    }

    public void calendarOnClick(View view) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR},
                            CALENDAR_WRITE_REQUIRED);
        } else {
            calendarManagement.saveEvent(this);
        }
    }

    public void csvFileOnClick(View view) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUIRED);
        } else {
            csvFileManagement.createCSVFile(this);
        }
    }
}
