// wakeup lock  don't light up screen 
mWakeUpLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK , "CALL_SERVICE" + "#"+ getClass().getName());
if (mWakeUpLock.isHeld())  mWakeUpLock.release();


 /**
     * 通话结束重新上锁
     */
    public void releaseWakeLock() {

        if (mWakeLock != null) {
            Log.e(TAG, " releaseWakeLock ==>> 终止服务,释放唤醒锁");
            mWakeLock.release();
            mWakeLock = null;
        }
        if (mKeyguardLock != null) {
            Log.e(TAG, "releaseWakeLock ==>> 终止服务,重新锁键盘");
            mKeyguardLock.reenableKeyguard();
        }

    }


    /**
     * 开锁
     */
    public void disableKeyguard() {
        Log.e(TAG, "disableKeyguard");
        // 点亮亮屏
        mWakeLock = mPowerManager.newWakeLock
                (PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "Tag");
        mWakeLock.acquire();
        // 初始化键盘锁
        mKeyguardLock = mKeyguardManager.newKeyguardLock("");
        // 键盘解锁
        mKeyguardLock.disableKeyguard();

    }
