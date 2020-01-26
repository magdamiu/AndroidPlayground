package com.magdamiu.androidplayground;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

public class SendMailWithAttachement {
    public void sendMail(Context context, String mailId, String subject, String body, String pathToFile) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailId});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        File file = new File(pathToFile);
        if (!file.exists() || !file.canRead()) {
            return;
        }
        Uri attachment;
        if (Build.VERSION.SDK_INT < 24) {
            attachment = Uri.fromFile(file);
        } else {
            attachment = Uri.parse(file.getPath()); // My work-around for new SDKs, doesn't work in Android 10.
        }
        emailIntent.putExtra(Intent.EXTRA_STREAM, attachment);
        context.startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
    }

    public void composeEmail(Context context, String addresse, String subject, String body, String pathToFile) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresse);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        File file = new File(pathToFile);
        if (!file.exists() || !file.canRead()) {
            return;
        }
        Uri attachment;
        if (Build.VERSION.SDK_INT < 24) {
            attachment = Uri.fromFile(file);
        } else {
            attachment = Uri.parse(file.getPath()); // My work-around for new SDKs, doesn't work in Android 10.
        }
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, "Pick an Email provider"));
        }
    }
}
