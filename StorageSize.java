//    1. 判断 SDCard 是否存在,并且是否具有可读写权限
    /**
     * 外部存储是否可用 (存在且具有读写权限)
     * @return
     */
     public static boolean isExternalStorageAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

//    2.获取手机系统可用空间大小
    /**
     * 获取手机内部可用空间大小
     * @return
     */
    static public long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }




//    3.获取手机内部总空间大小
    /**
     * 获取手机内部空间大小
     * @return
     */
    static public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();//Gets the Android data directory
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();      //每个block 占字节数
        long totalBlocks = stat.getBlockCount();   //block总数
        return totalBlocks * blockSize;
    }
//    4.获取手机外部可用空间大小、获取手机外部总空间大小
    /**
     * 获取手机外部可用空间大小
     * @return
     */
    static public long getAvailableExternalMemorySize() {
        if (isExternalStorageAvailable()) {
            File path = Environment.getExternalStorageDirectory();//获取SDCard根目录
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }

    /**
     * 获取手机外部总空间大小
     * @return
     */
    static public long getTotalExternalMemorySize() {
        if (isExternalStorageAvailable()) {
            File path = Environment.getExternalStorageDirectory(); //获取SDCard根目录
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return -1;
        }
    }
