package com.magdamiu.androidplayground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int CALENDAR_WRITE_REQUIRED = 22;
    private static final int WRITE_EXTERNAL_STORAGE_REQUIRED = 23;
    private static final int READ_EXTERNAL_STORAGE_REQUIRED = 25;

    private CalendarManagement calendarManagement;
    private CSVFileManagement csvFileManagement;
    private SendMailWithAttachement sendMailWithAttachement;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarManagement = new CalendarManagement();
        csvFileManagement = new CSVFileManagement();
        sendMailWithAttachement = new SendMailWithAttachement();

        fileName = "Event.csv";

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
            csvFileManagement.createCSVFile(this, fileName);
        }
    }

    public void attachFileOnMailOnClick(View view) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUIRED);
        } else {
            final String filePath = getFilePath();
            // TODO to be fixed..it does not work to send an attach
            sendMailWithAttachement.sendMail(MainActivity.this, "badita.magda@gmail.com", "title mail", "body email", filePath);
        }
    }
    private String getFilePath() {
        File folder = new File(Environment.getExternalStorageDirectory()
                + "/FolderCSV");

        boolean existsFolder = false;
        if (!folder.exists())
            existsFolder = folder.mkdir();

        System.out.println("" + existsFolder);

        return folder.toString() + "/" + fileName;
    }


}
