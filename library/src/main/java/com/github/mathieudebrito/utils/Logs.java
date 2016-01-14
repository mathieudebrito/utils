package com.github.mathieudebrito.utils;

import android.util.Log;

import com.google.common.base.Strings;

public class Logs {
    public static final int DEFAULT = 1;
    public static final int ACTIVITY_MANAGER = 2;
    public static final int ANDROID_RUNTIME = 3;

    public static String logHeader = "";
    public static String tag = "[MDB_LOG]";

    protected static boolean printOnReleaseBuildEnabled = false;

    public static final int LOG = 1;
    public static final int NO_LOG = 2;

    public enum Type {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    public static void enablePrintOnReleaseBuild() {
        printOnReleaseBuildEnabled = true;
    }

    public static boolean canPrint() {
        return printOnReleaseBuildEnabled || BuildConfig.DEBUG;
    }


    public static void method(Object classe) {
        method(classe, null);
    }

    public static void method(Object classe, String message) {
        new Builder(message).type(Type.VERBOSE).tag(tag).object(classe).method().print();
    }

    public static void verbose(Object classe, String message) {
        new Builder(message).type(Type.VERBOSE).tag(tag).object(classe).method().print();
    }

    public static void debug(Object classe, String message) {
        new Builder(message).type(Type.DEBUG).tag(tag).object(classe).method().print();
    }

    public static void info(Object classe, String message) {
        new Builder(message).type(Type.INFO).tag(tag).object(classe).method().print();
    }

    public static void warn(Object classe, String message) {
        new Builder(message).type(Type.WARN).tag(tag).object(classe).method().print();
    }

    public static void error(Object classe, String message) {
        new Builder(message).type(Type.ERROR).tag(tag).object(classe).method().print();
    }

    public static class Builder {
        private String tag;
        private String message;
        private String method;
        private String object;
        private Type type;

        public Builder(String message) {
            this.message = message;
        }

        protected Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        protected Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder object(Object object) {
            this.object = Objects.name(object);
            return this;
        }

        public Builder method() {
            this.method = Thread.currentThread().getStackTrace()[3].getMethodName();
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public void print() {

            if (canPrint()) {
                StringBuilder log = new StringBuilder();
                if (!Strings.isNullOrEmpty(object)) {
                    log.append("[" + object + "] ");
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
                    Log.v(tag, log.toString());
                } else if (type == Type.DEBUG) {
                    Log.d(tag, log.toString());
                } else if (type == Type.INFO) {
                    Log.i(tag, log.toString());
                } else if (type == Type.WARN) {
                    Log.w(tag, log.toString());
                } else if (type == Type.ERROR) {
                    Log.e(tag, log.toString());
                }
            }
        }
    }

}
