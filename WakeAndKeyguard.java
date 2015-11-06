    private PowerManager.WakeLock mWakeLock; // 唤醒锁
    private KeyguardManager.KeyguardLock mKeyguardLock;// 键盘锁
    public void disableKeyguardTurnOnScreen() {
        Timber.e("disableKeyguardTurnOnScreen");
        // 点亮亮屏
        mWakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE)).newWakeLock
                (PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "wakeup");
        mWakeLock.acquire();
        KeyguardManager mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        // 初始化键盘锁
        mKeyguardLock = mKeyguardManager.newKeyguardLock("unLock");
        // 键盘解锁
        mKeyguardLock.disableKeyguard();
    }

    /**
     * 通话结束重新上锁
     */
    public void releaseWakeLock() {

        if (mWakeLock != null) {
            Timber.e(" releaseWakeLock ==>> 终止服务,释放唤醒锁");
            mWakeLock.release();
            mWakeLock = null;
        }
        if (mKeyguardLock!=null) {
            Timber.e("releaseWakeLock ==>> 终止服务,重新锁键盘");
            mKeyguardLock.reenableKeyguard();
        }

    }
