package com.sdis.secours.lsf;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {

    public static void write(Context context, String message) {
        SimpleDateFormat formatDateSimple = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault());

        File logDir = context.getExternalFilesDir(null);
        File logFile = new File(logDir, "logs" + "_" + formatDateSimple.format(new Date()) + ".txt");

        SimpleDateFormat formatDateComplete = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.append(formatDateComplete.format(new Date()));
            writer.append(" : ");
            writer.append(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}