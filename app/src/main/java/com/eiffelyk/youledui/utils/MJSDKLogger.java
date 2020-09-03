package com.eiffelyk.youledui.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 * 记录日志的时候， 顺带往handler记录一份
 */

public class MJSDKLogger {
    private static final String TAG = "DataEnlighten";

    private static final String INFO = "INFO";

    private static final String ERROR = "ERROR";

    private static boolean ENABLE = false;
    public static void setLogOpen(boolean logOpen){
        ENABLE = logOpen;
    }
    private static Handler handler;

    public static void info(String message) {
        info(TAG, message);
    }

    public static void info(String tag, String message) {
        log(INFO, tag, message);
    }

    public static void error(String message) {
        error(TAG, message);
    }

    public static void error(String tag, String message) {
        log(ERROR, tag, message);
    }

    public static void setHandler(Handler handler) {
        MJSDKLogger.handler = handler;
    }

    private static void log(String level, String tag, String message) {
        if (!ENABLE) {
            return;
        }
        if (level.equals(INFO)) {
            Log.i(tag, message);

        } else if (level.equals(ERROR)) {
            Log.e(tag, message);
        }
        if (handler != null) {
            Message msg = Message.obtain();
            msg.obj = "[" + level + "]" + message + "\n";
            handler.sendMessage(msg);
        }
    }
}
