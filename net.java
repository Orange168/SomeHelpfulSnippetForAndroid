   public static boolean hasStorage2Download(@NonNull final Context context,String sUrl) {
        try {
            URL url = new URL(sUrl);
            HttpURLConnection cnn = (HttpURLConnection) url.openConnection();
            int fileLength =  cnn.getContentLength()/*19.2 MiB  return 20075405byte*/;

            Toast.makeText(context,"File Size ==>>" + fileLength,Toast.LENGTH_LONG).show();
            return fileLength < getAvailableStorage() ;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return false;
    }
//hello
