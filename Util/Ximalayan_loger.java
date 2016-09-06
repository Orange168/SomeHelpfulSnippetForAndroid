//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ximalaya.ting.android.opensdk.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Logger {
    public static final String JSON_ERROR = "解析json异常";
    static Map<String, String> map = new HashMap();
    private static long nowTime;
    private static int LOG_LEVEL;

    static {
        map.clear();
        String sdStatus = Environment.getExternalStorageState();
        if(sdStatus.equals("mounted")) {
            String pathname = Environment.getExternalStorageDirectory() + "/ting/config.ini";
            if(!TextUtils.isEmpty(pathname)) {
                File file = new File(pathname);
                if(file.exists()) {
                    FileReader reader = null;
                    BufferedReader br = null;

                    try {
                        reader = new FileReader(pathname);
                        br = new BufferedReader(reader);
                        String e = null;

                        while((e = br.readLine()) != null) {
                            if(!TextUtils.isEmpty(e)) {
                                String[] strs = e.split("=");
                                if(strs != null && strs.length == 2) {
                                    map.put(strs[0], strs[1]);
                                }
                            }
                        }
                    } catch (Exception var19) {
                        var19.printStackTrace();
                    } finally {
                        if(br != null) {
                            try {
                                br.close();
                            } catch (IOException var18) {
                                var18.printStackTrace();
                            }
                        }

                        if(reader != null) {
                            try {
                                reader.close();
                            } catch (IOException var17) {
                                var17.printStackTrace();
                            }
                        }

                    }
                }
            }
        }

        nowTime = System.currentTimeMillis();
        LOG_LEVEL = 0;
    }

    public Logger() {
    }

    public static void log(Object showMessage) {
        Log.i("ting", "" + showMessage);
    }

    public static void logListToSD(List list, String message) {
        if(list != null && list.size() != 0) {
            logToSd("start-log-list:" + message + ":size:" + list.size());
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Object object = var3.next();
                if(object != null) {
                    logToSd(object.toString());
                }
            }

            logToSd("end-log-list" + message);
        }
    }

    public static void logToSd(String showMessage) {
        checkIfLog(showMessage);
        File savePathFile = getLogFilePath();
        if(savePathFile != null) {
            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(new FileWriter(savePathFile, true));
                printWriter.println(showMessage);
            } catch (Throwable var7) {
                ;
            } finally {
                if(printWriter != null) {
                    printWriter.close();
                }

            }

        }
    }

    public static File getLogFilePath() {
        String sdStatus = Environment.getExternalStorageState();
        if(!sdStatus.equals("mounted")) {
            return null;
        } else {
            String pathName = Environment.getExternalStorageDirectory() + "/ting/errorLog/infor.log";
            File path = new File(pathName);
            if(!path.getParentFile().getParentFile().exists()) {
                path.getParentFile().getParentFile().mkdir();
            }

            if(!path.getParentFile().exists()) {
                path.getParentFile().mkdir();
            }

            return path;
        }
    }

    public static boolean checkIfLog(String logstr) {
        Iterator var2 = map.entrySet().iterator();

        Entry entry;
        do {
            if(!var2.hasNext()) {
                return false;
            }

            entry = (Entry)var2.next();
        } while(!logstr.contains((CharSequence)entry.getKey()) || !((String)entry.getValue()).equals("true"));

        return true;
    }

    public static void throwRuntimeException(Object exceptionMessage) {
        throw new RuntimeException("出现异常：" + exceptionMessage);
    }

    public static void logFuncRunTime(String message) {
        log("time " + message + ":" + (System.currentTimeMillis() - nowTime));
        nowTime = System.currentTimeMillis();
    }

    public static String getLineInfo() {
        StackTraceElement ste = (new Throwable()).getStackTrace()[1];
        return "@" + ste.getFileName() + ": Line " + ste.getLineNumber();
    }

    public static void setDebugLevel(int level) {
        LOG_LEVEL = level;
    }

    public static void log(String tag, String message, boolean show) {
        if(message != null && isLoggable(tag, 3)) {
            Log.d(tag, message);
        }

    }

    public static void v(String tag, String msg) {
        if(msg != null && isLoggable(tag, 2)) {
            Log.v(tag, msg);
        }

    }

    public static void v(String tag, String msg, Throwable tr) {
        if(msg != null && isLoggable(tag, 2)) {
            Log.v(tag, msg, tr);
        }

    }

    public static void d(String tag, String msg) {
        if(msg != null && isLoggable(tag, 3)) {
            Log.d(tag, msg);
        }

    }

    public static void d(String tag, String msg, Throwable tr) {
        if(msg != null && isLoggable(tag, 3)) {
            Log.d(tag, msg, tr);
        }

    }

    public static void i(String tag, String msg) {
        if(msg != null && isLoggable(tag, 4)) {
            Log.i(tag, msg);
        }

    }

    public static void i(String tag, String msg, Throwable tr) {
        if(msg != null && isLoggable(tag, 4)) {
            Log.i(tag, msg, tr);
        }

    }

    public static void w(String tag, String msg) {
        if(msg != null && isLoggable(tag, 5)) {
            Log.w(tag, msg);
        }

    }

    public static void w(String tag, String msg, Throwable tr) {
        if(msg != null && isLoggable(tag, 5)) {
            Log.w(tag, msg, tr);
        }

    }

    public static void w(String tag, Throwable tr) {
        if(tr != null && isLoggable(tag, 5)) {
            Log.w(tag, tr);
        }

    }

    public static void e(Exception e) {
        e("解析json异常", "解析json异常" + e.getMessage() + getLineInfo());
    }

    public static void e(String tag, String msg) {
        if(msg != null && isLoggable(tag, 6)) {
            Log.e(tag, msg);
        }

    }

    public static void e(String tag, String msg, Throwable tr) {
        if(msg != null && isLoggable(tag, 6)) {
            Log.e(tag, msg, tr);
        }

    }

    public static boolean isLoggable(String tag, int level) {
        return level >= LOG_LEVEL;
    }
}
