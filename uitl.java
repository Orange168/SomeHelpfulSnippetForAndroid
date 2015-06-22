
import android.content.Context;

public class DisplayUtil {
	public static int dip2px(Context context, float dipValue){            
		final float scale = context.getResources().getDisplayMetrics().density;                 
		return (int)(dipValue * scale + 0.5f);         
	}            
	public static int px2dip(Context context, float pxValue){                
		final float scale = context.getResources().getDisplayMetrics().density;                 
		return (int)(pxValue / scale + 0.5f);         
	} 
/**
     * 在一个低密度的小屏手机上，仅靠上面的代码是不能获取正确的尺寸的。比如说，一部240x320像素的低密度手机，如果运行上述代码，获取到的屏幕尺寸是320x427。
     * 若没有设定多分辨率支持的话，Android系统会将240x320的低密度（120）尺寸转换为中等密度（160）对应的尺寸
     *  <supports-screens
     *    android:smallScreens="true"
     *    android:normalScreens="true"
     *    android:largeScreens="true"
     *    android:resizeable="true"
     *    android:anyDensity="true"/>
     * @param activity
     */
    public static void  getDefaultDisplay(Activity activity){
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
//        metric = activity.getResources().getDisplayMetrics() ;
        int screenWidth = metric.widthPixels;  // 屏幕宽度（像素）
        int screenHeight = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        Log.e(TAG + "getDefaultDisplay", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
        Log.e(TAG + "getDefaultDisplay", "density=" + density + "; densityDpi=" + densityDpi);
    }
    public static void getViewCoordinate(View v, Context cxt){
    	DisplayMetrics metrics = cxt.getResources().getDisplayMetrics();
        Log.i(TAG, "density=" + metrics.density + "height="+metrics.heightPixels  +
                    "width=" + metrics.widthPixels) ;
        Log.i(TAG, "left = " + v.getLeft() +"\t\tright" + v.getRight() +
                            "\n top" + v.getTop()+ "\t\tbottom" + v.getBottom() );
    }
}
