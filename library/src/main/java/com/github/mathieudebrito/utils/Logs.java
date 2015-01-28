package com.github.mathieudebrito.utils;

import android.util.Log;

public class Logs {
    public static final int DEFAULT = 1;
    public static final int ACTIVITY_MANAGER = 2;
    public static final int ANDROID_RUNTIME = 3;

    private static String logHeader = "";
    private static String logMyDefault = "[MDB_LOG]";

    public static final int LOG = 1;
    public static final int NO_LOG = 2;

    public static boolean isDebug() {
        return true || BuildConfig.DEBUG;
    }

    public static void debug(String logMessage) {
        if (isDebug()) {
            Logs.log(logMessage, DEFAULT);
        }
    }

    public static void debug(Object object, String message) {
        if (isDebug()) {
            debug(getMessageWithClassName(object, message));
        }
    }

    public static void debug(Object object, String function, String message) {
        debug(getMessageWithFunction(object, function, message));
    }

    public static void info(String message) {
        if (isDebug()) {
            Log.i(logMyDefault, logHeader + message);
        }
    }

    public static void info(Object object, String message) {
        info(getMessageWithClassName(object, message));
    }

    public static void info(Object object, String function, String message) {
        info(getMessageWithFunction(object, function, message));
    }

    public static void error(String message) {
        if (isDebug()) {
            Log.e(logMyDefault, logHeader + message);
        }
    }

    public static void error(Object object, String message) {
        error(getMessageWithClassName(object, message));
    }

    public static void error(Object object, String function, String message) {
        error(getMessageWithFunction(object, function, message));
    }

    public static void warn(String message) {
        if (isDebug()) {
            Log.w(logMyDefault, logHeader + message);
        }
    }

    public static void warn(Object object, String message) {
        warn(getMessageWithClassName(object, message));
    }

    public static void warn(Object object, String function, String message) {
        warn(getMessageWithFunction(object, function, message));
    }

    public static void logClick(String logButtonName) {
        if (isDebug()) {
            Logs.log(" ~ Button " + logButtonName + " clicked", DEFAULT);
        }
    }

    public static void logMenu(String logMenu) {
        if (isDebug()) {
            Logs.log(" # " + logMenu + " asked", DEFAULT);
        }
    }

    public static void logValue(String logValueName, String logValue) {
        if (isDebug()) {
            Logs.log(" |" + logValueName + "| = " + logValue, DEFAULT);
        }
    }

    public static void log(String logMessage) {
        debug(logMessage);
    }

    public static void log(String logMessage, int logType) {
        if (isDebug()) {

            String logTypeString;
            switch (logType) {
                case DEFAULT:
                    logTypeString = logMyDefault;
                    break;

                case ACTIVITY_MANAGER:
                    logTypeString = "ActivityManager";
                    break;

                case ANDROID_RUNTIME:
                    logTypeString = "AndroidRuntime";
                    break;

                default:
                    logTypeString = logMyDefault;
                    break;
            }
            Log.d(logTypeString, logHeader + logMessage);
        }
    }

    public static String getMessageWithClassName(Object object, String message) {
        return "[" + getClassName(object) + "] " + message;
    }

    public static String getMessageWithFunction(Object object, String function, String message) {
        return "[" + getClassName(object) + "] " + function + "( " + message + " )";
    }

    public static String getClassName(Object object) {
        if (object == null) {
            return "UnknownClass";
        }
        String className = object.getClass().toString();
        String[] path = className.split("\\.");
        className = path[path.length - 1];
        if (className.endsWith("_")) {
            className = className.substring(0, className.length() - 1);
        }

        return className;
    }
}
