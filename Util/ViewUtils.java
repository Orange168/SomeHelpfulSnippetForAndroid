public class InputTxtFilter{
    public static final int INPUT_TYPE_EN = 0x01;
    public static final int INPUT_TYPE_CH = 0x02;
    private static final String[] SPELL = new String[]{
        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
        "ā","á","ǎ","à","ō","ó","ǒ","ò","ē","é","ě","è","ī","í","ǐ","ì","ū","ú","ǔ","ù","ǖ","ǘ","ǚ","ǜ","ü"
    };
    private static char[] chineseParam = new char[]{'」','，','。','？','…','：','～','【','＃','、','％','＊','＆','＄','（','‘','’','“','”','『','〔','｛','【'
        ,'￥','￡','‖','〖','《','「','》','〗','】','｝','〕','』','”','）','！','；','—'};

    private InputTxtFilter( ){
    }
    
       /**
    * dp2px
    */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
    * px2dp
    */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    

    public static void inputFilter( final Context context, final EditText editText, final int type, final int inputLimit){
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(inputLimit){
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){
                boolean isRightCharater = false;
                if(type == INPUT_TYPE_EN){
                    isRightCharater = isLetter(source.toString());
                }else if(type == INPUT_TYPE_CH){
                    isRightCharater = isChineseWord(source.toString());
                }

                if ( !isRightCharater|| dest.toString( ).length( )>=inputLimit ){
                    return "";
                }

                return source;
            }
        };
        editText.setFilters(filters);
    }

    /**
     * 检测String是否全是中文
     * 
     */
    public static boolean isChineseWord( String name ){
        boolean res=true;
        char[] cTemp = name.toCharArray( );

        for( int i = 0; i < name.length( ); i++ ){
            if( !isChinese( cTemp[ i ] ) ){
                res=false;
                break;
            }
        }

        return res;
    }

    /**
     * 是否为英文字母
     * 
     * */
    public static boolean isLetter( String inputStr ){
        char[] inputArray = inputStr.toCharArray( );
        List<String> spellList = Arrays.asList( SPELL );

        for( char input : inputArray ){
            if( !spellList.contains( input + "" ) ){
                return false;
            }
        }

        return true;
    }

    /**
     * 判定输入汉字
     * @param c
     */
    public static boolean isChinese( char c ){
        for( char param : chineseParam ){
            if( param == c ){
                return false;
            }
        }

        Character.UnicodeBlock ub = Character.UnicodeBlock.of( c );
        if ( ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ){
            return true;
        }

        return false;
    }
    
    /**
     * 改变status bar 颜色above 21 LOLLIPOP 
     */
    public static void setStatusBarColor(Context mContext, int id) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((AppCompatActivity)mContext).getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(mContext, id));
        }
    }
    
    /**
     * 
     */
      public static void showKeyboard(final View view,Context mContext) {
        view.requestFocus();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(view, 0);
            }
        }, 400);
    }
    
    public static void hideKeyboard(final View view,Activity mActivity) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mActivity.getCurrentFocus() != null) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }, 400);
    }

}
