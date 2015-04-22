
//Android判断APP是否在前台运行: 
//如果是可见的就维持一个socket长连接，如果切到后台不可见了，就断开这个连接
public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks { 
    private int foregroundActivities;
    private boolean hasSeenFirstActivity;
    private boolean isChangingConfiguration;
    @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }
 
    @Override public void onActivityStarted(Activity activity) {
        mForegroundActivities++;
        if (hasSeenFirstActivity && foregroundActivities == 1 && !isChangingConfiguration) {
            applicationDidEnterForeground(activity);
        }
        hasSeenFirstActivity = true;
        isChangingConfiguration = false;
    }
    @Override public void onActivityResumed(Activity activity) {
    }
 
    @Override public void onActivityPaused(Activity activity) {
    }
 
    @Override public void onActivityStopped(Activity activity) {
        foregroundActivities--;
        if (foregroundActivities == 0) {
            applicationDidEnterBackground(activity);
        }
        isChangingConfiguration = activity.isChangingConfigurations();
    }
 
    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }
 
    @Override public void onActivityDestroyed(Activity activity) {
    }
}

//以调用Application的registerActivityLifecycleCallback方法
