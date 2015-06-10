
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
	
	 public static void  getDefaultDisplay(Activity activity){
	 	
	        DisplayMetrics metric = new DisplayMetrics();
	        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
	//       metric = activity.getResources().getDisplayMetrics() ;
	        int screenWidth = metric.widthPixels;  // 屏幕宽度（像素）
	        int screenHeight = metric.heightPixels;  // 屏幕高度（像素）
	        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
	        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
	        Log.e(TAG + "getDefaultDisplay", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
	        Log.e(TAG + "getDefaultDisplay", "density=" + density + "; densityDpi=" + densityDpi);
	  }
}
