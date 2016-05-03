public class CommonUtils {
    
    public static void saveBitmap2File(Bitmap bitmap, File filePath) {
        if (filePath == null) {
            return;
        }
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(filePath);
            if (null != fos) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @param rawBitmap 原来的Bitmap
     * @param row       切成几行
     * @param column    切成几列
     * @return
     */
    public static ArrayList<Bitmap> splitBitmap(Bitmap rawBitmap, int row, int column) {
        ArrayList<Bitmap> mSplitList = new ArrayList<Bitmap>(row * column);
        int rawBitmapWidth = rawBitmap.getWidth();
        int rawBitmapHeight = rawBitmap.getHeight();

        System.out.println("rawBitmapWidth=" + rawBitmapWidth + ",rawBitmapHeight=" + rawBitmapHeight);
        int perPartWidth = rawBitmapWidth / column;
        int perPartHeight = rawBitmapHeight / row;
        System.out.println("perPartWidth=" + perPartWidth + ",perPartHeight=" + perPartHeight);
        Bitmap bitmap;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int x = j * perPartWidth;
                int y = i * perPartHeight;
                System.out.println("i=" + i + ",j=" + j + ",x=" + x + ",y=" + y);
                bitmap = Bitmap.createBitmap(rawBitmap, x, y, perPartWidth, perPartHeight);
                mSplitList.add(bitmap);
            }
        }
        System.out.println("size=" + mSplitList.size());
        return mSplitList;
    }

 /**
     * 获取单个文件的MD5值
     * @param file 文件
     * @return 文件的MD5值
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
    /**
     * 获取单个文件的MD5值
     * @param filePath 文件路径
     * @return 文件的MD5值
     */
    public static String getFileMD5(String filePath) {
//        Log.i("FileOperatorUtils", "getFileMD5" + getFileMD5(filePath));
        return getFileMD5(new File(filePath));
    }
    
       public static String getBitmapMD5(byte[] bytesBitmap) {
        MessageDigest digest ;

        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(bytesBitmap);
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
