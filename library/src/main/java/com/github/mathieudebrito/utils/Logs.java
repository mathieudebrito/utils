package com.github.mathieudebrito.utils;

import android.util.Log;

import com.google.common.base.Strings;

public class Logs {
    public static final int DEFAULT = 1;
    public static final int ACTIVITY_MANAGER = 2;
    public static final int ANDROID_RUNTIME = 3;

    public static String logHeader = "";
    public static String tag = "[MDB_LOG]";

    public static boolean forced = false;

    public static final int LOG = 1;
    public static final int NO_LOG = 2;

    public enum Type {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    public static boolean canPrint() {
        return forced || BuildConfig.DEBUG;
    }

    public static void debug(String logMessage) {
        if (canPrint()) {
            Logs.log(logMessage, DEFAULT);
        }
    }

    public static void debug(Object object, String message) {
        if (canPrint()) {
            debug(getMessageWithClassName(object, message));
        }
    }

    public static void debug(Object object, String function, String message) {
        debug(getMessageWithFunction(object, function, message));
    }

    public static void info(String message) {
        if (canPrint()) {
            Log.i(tag, logHeader + message);
        }
    }

    public static void info(Object object, String message) {
        info(getMessageWithClassName(object, message));
    }

    public static void info(Object object, String function, String message) {
        info(getMessageWithFunction(object, function, message));
    }

    public static void error(String message) {
        if (canPrint()) {
            Log.e(tag, logHeader + message);
        }
    }

    public static void error(Object object, String message) {
        error(getMessageWithClassName(object, message));
    }

    public static void error(Object object, String function, String message) {
        error(getMessageWithFunction(object, function, message));
    }

    public static void warn(String message) {
        if (canPrint()) {
            Log.w(tag, logHeader + message);
        }
    }

    public static void warn(Object object, String message) {
        warn(getMessageWithClassName(object, message));
    }

    public static void warn(Object object, String function, String message) {
        warn(getMessageWithFunction(object, function, message));
    }

    public static void logClick(String logButtonName) {
        if (canPrint()) {
            Logs.log(" ~ Button " + logButtonName + " clicked", DEFAULT);
        }
    }

    public static void logMenu(String logMenu) {
        if (canPrint()) {
            Logs.log(" # " + logMenu + " asked", DEFAULT);
        }
    }

    public static void logValue(String logValueName, String logValue) {
        if (canPrint()) {
            Logs.log(" |" + logValueName + "| = " + logValue, DEFAULT);
        }
    }

    public static void log(String logMessage) {
        debug(logMessage);
    }

    public static void log(String logMessage, int logType) {
        if (canPrint()) {

            String logTypeString;
            switch (logType) {
                case DEFAULT:
                    logTypeString = tag;
                    break;

                case ACTIVITY_MANAGER:
                    logTypeString = "ActivityManager";
                    break;

                case ANDROID_RUNTIME:
                    logTypeString = "AndroidRuntime";
                    break;

                default:
                    logTypeString = tag;
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

    public static Builder verbose() {
        return new Builder(Type.VERBOSE).tag(tag);
    }

    public static Builder debug() {
        return new Builder(Type.DEBUG).tag(tag);
    }

    public static Builder info() {
        return new Builder(Type.INFO).tag(tag);
    }

    public static Builder warn() {
        return new Builder(Type.WARN).tag(tag);
    }

    public static Builder error() {
        return new Builder(Type.ERROR).tag(tag);
    }

    public static class Builder {
        private String tag;
        private String message;
        private String method;
        private String classe;
        private Type type;

        public Builder(Type type) {
            this.type = type;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder method() {
            this.method = Thread.currentThread().getStackTrace()[1].getMethodName();
            return this;
        }

        public Builder classe(Object object) {
            this.classe = getClassName(object);
            return this;
        }

        public void print() {

            if (canPrint()) {
                StringBuilder log = new StringBuilder();
                if (!Strings.isNullOrEmpty(classe)) {
                    log.append("[" + classe + "] ");
                }
                if (!Strings.isNullOrEmpty(method)) {
                    log.append(method + " ");
                }
                if (!Strings.isNullOrEmpty(message)) {
                    if (!Strings.isNullOrEmpty(method)) {
                        log.append("( " + message + " )");
                    } else {
                        log.append(message);
                    }
                }

                if (type == Type.VERBOSE) {
                    Log.v(tag, message);
                } else if (type == Type.DEBUG) {
                    Log.d(tag, message);
                } else if (type == Type.INFO) {
                    Log.i(tag, message);
                } else if (type == Type.WARN) {
                    Log.w(tag, message);
                } else if (type == Type.ERROR) {
                    Log.e(tag, message);
                }
            }
        }
    }

}
