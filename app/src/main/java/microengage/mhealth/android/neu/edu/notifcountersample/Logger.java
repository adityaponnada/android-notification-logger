package microengage.mhealth.android.neu.edu.notifcountersample;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adityaponnada on 25/08/17.
 */

public class Logger {

        private BufferedWriter writer = null;
        private String tag = "";
        public static final String TAG = "Logger";

        public Logger(String logTag){
            tag = logTag;
        }

        public void d(String message) {
            android.util.Log.d(TAG, message);
        }

        public void e(String message, Context context) {
            android.util.Log.e(tag, message);
            logTAGFile("error", message, context);
        }

        public void i(String message, Context context) {
            android.util.Log.i(tag, message);
            logTAGFile("info", message, context);
        }

        public void v(String message, Context context) {
            android.util.Log.v(tag, message);
            logTAGFile("verbose", message, context);
        }

        public void w(String message, Context context) {
            android.util.Log.w(tag, message);
            logTAGFile("warn", message, context);
        }

        private void logTAGFile(String type, String message, Context context) {
            // Boolean logSaveFlag = SharedPrefs.getBoolean(WearableWakefulService.KEY_ENABLE_SAVE_LOG, WearableWakefulService.FLAG_ENABLE_SAVE_LOG, context);
            // logSaveFlag = logSaveFlag == null? WearableWakefulService.FLAG_ENABLE_SAVE_LOG : logSaveFlag;
            // if(!logSaveFlag){
            //     return;
            // }

            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String currentHour = new SimpleDateFormat("HH-z").format(new Date());
            File folder = new File("/sdcard/." + "NotificationCounter" + "/logs/" + currentDate + "/" + currentHour + "/");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(folder.getAbsolutePath() + "/" + tag + ".log.csv");
            FileWriter aWriter = null;

            try {
                if(writer == null) {
                    aWriter = new FileWriter(file, true);
                    writer = new BufferedWriter(aWriter);
                }
                String logString = new Date().toString() + "," + type + "," + message;
                if(writer != null) {
                    writer.write(logString);
                    Log.d("FILE LOGGER:", "File Written to Logs");
                    writer.newLine();
                }

                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
                if (aWriter != null) {
                    aWriter.flush();
                    aWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /*private void logTAGFile(String type, String message, Context context) {
       //Boolean logSaveFlag = SharedPrefs.getBoolean(WearableWakefulService.KEY_ENABLE_SAVE_LOG, WearableWakefulService.FLAG_ENABLE_SAVE_LOG, context);
       // logSaveFlag = logSaveFlag == null? WearableWakefulService.FLAG_ENABLE_SAVE_LOG : logSaveFlag;
       // if(!logSaveFlag){
       //     return;
       // }

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String currentHour = new SimpleDateFormat("HH-z").format(new Date());
        File folder = new File("/sdcard/." + "MicroEngageTrial" + "/logs/" + currentDate + "/" + currentHour + "/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder.getAbsolutePath() + "/" + "Watch-" + tag + ".log.csv");
        FileWriter aWriter = null;

        try {
            if(writer == null) {
                aWriter = new FileWriter(file, true);
                writer = new BufferedWriter(aWriter);
            }
            String logString = new Date().toString() + "," + type + "," + message;
            if(writer != null) {
                writer.write(logString);
                Log.d("FILE LOGGER:", "File Written to Logs");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
                if (aWriter != null) {
                    aWriter.flush();
                    aWriter.close();
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }*/

        public void logStackTrace(Exception e, Context context){
            StackTraceElement[] stackTrace = e.getStackTrace();
            for(StackTraceElement trace : stackTrace){
                this.e(trace.toString(), context);
            }
        }

        public void logStackTrace(Throwable ex, Context context){
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for(StackTraceElement trace : stackTrace){
                this.e(trace.toString(), context);
            }
        }

        public void close() {
            //IOUtils.closeQuietly(writer);
            writer = null;
        }

    }
