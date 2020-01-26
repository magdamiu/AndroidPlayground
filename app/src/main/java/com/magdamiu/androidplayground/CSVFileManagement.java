package com.magdamiu.androidplayground;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;

public class CSVFileManagement {
    public void createCSVFile(Context context) {
        File folder = new File(Environment.getExternalStorageDirectory()
                + "/Folder");

        boolean existsFolder = false;
        if (!folder.exists())
            existsFolder = folder.mkdir();

        System.out.println("" + existsFolder);

        final String filename = folder.toString() + "/" + "Test.csv";

        // show waiting screen
        CharSequence contentTitle = "A title...";
        final ProgressDialog progressDialog = ProgressDialog.show(
                context, contentTitle, "",
                true);//please wait

        new Thread() {
            public void run() {
                try {

                    FileWriter fileWriter = new FileWriter(filename);

                    fileWriter.append("code");
                    fileWriter.append(',');

                    fileWriter.append("nr");
                    fileWriter.append(',');

                    fileWriter.append("name");
                    fileWriter.append(',');

                    fileWriter.append("asd");
                    fileWriter.append(',');

                    fileWriter.append("qwe");
                    fileWriter.append(',');

                    fileWriter.append("rty");
                    fileWriter.append(',');

                    fileWriter.append('\n');
                    // fileWriter.flush();
                    fileWriter.close();

                } catch (Exception e) {
                }
                progressDialog.dismiss();
            }
        }.start();
    }
}
