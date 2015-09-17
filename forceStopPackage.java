 private void forceStopPackage(String pkgName, Context context)
            throws Exception {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        java.lang.reflect.Method method = Class.forName(
                "android.app.ActivityManager").getMethod("forceStopPackage",
                String.class);
        method.invoke(am, pkgName);
  }
