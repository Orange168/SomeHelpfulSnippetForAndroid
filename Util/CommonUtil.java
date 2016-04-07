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

}
