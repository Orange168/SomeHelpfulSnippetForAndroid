////home key broadcast
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");

    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";


else if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                Log.e(TAG, "reason: " + reason);
                if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                    // 短按Home键
                    Log.e(TAG, "homekey");
                    sendFinishBroadcast();
                }else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                    // 长按Home键 或者 activity切换键
                    Log.e(TAG, "long press home key or activity switch");
                }else if (SYSTEM_DIALOG_REASON_LOCK.equals(reason)) {
                    // 锁屏
                    Log.e(TAG, "lock");
                }else if (SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {
                    // samsung 长按Home键
                    Log.e(TAG, "assist");
                }
            }
