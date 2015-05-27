        // One animator
        int total = 5 ;
        int radius = 300 ;
        // calculate the translation X Y
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));
        ImageView imageView = new ImageView(this) ;
        ObjectAnimator.ofFloat(imageView,"rotation",0F,360F).setDuration(100).start();
        ObjectAnimator.ofFloat(imageView,"translationX",0F,200F).setDuration(100).start();
        /*some interesting settings
        *  1. color gradual change between
        * **/
        // 1. red to blue gradual change infinite
        ValueAnimator colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
         // a lot of animator together
        PropertyValuesHolder p1 =  PropertyValuesHolder.ofFloat("rotation",0F, 360F) ;
        PropertyValuesHolder p2 =  PropertyValuesHolder.ofFloat("translationX",0F, 200F) ;
        ObjectAnimator.ofPropertyValuesHolder(imageView,p1,p2) ;
        //a set control
        ObjectAnimator a1 = ObjectAnimator.ofFloat(imageView, "rotation", 0F, 360F);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(imageView, "translationX", 0F, 360F);
        AnimatorSet set = new AnimatorSet() ;
        set.play(a1).with(a2) ;
        set.play(a2).after(a1) ;
        set.playTogether(a1,a2);
        set.start();
        //Listener adapter
        a1.addListener( new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        /* Interpolator
        *  1. Bounce
        *  2. OverShoot
        *  3. Accelerator
        *    ¡­¡­
        * **/
        a1.setInterpolator(new BounceInterpolator());
        /*
        * ValueAnimator
        * 1, addUpdateListener ;
        * 2, TypeEvaluator
        * 3, Generics TypeEvaluator<PointF> and public PointF evaluate(¡­¡­){¡­¡­}
        * **/
        final Button button = new Button(this);
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setText(String.valueOf(value));
            }
        });
        animator.start();
        // Override method return the value you want
        animator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return null;
            }
        }) ;
        a advance usage from filterMenus/FilterMenuLayout.java
        //1. init
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), primaryColor, primaryDarkColor);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //3. use
                primaryDarkPaint.setColor((Integer) animation.getAnimatedValue());
            }
        });
        //2. set 
        colorAnimator.setObjectValues(colorAnimator.getAnimatedValue() == null ? 
                primaryColor : colorAnimator.getAnimatedValue(), primaryDarkColor);
        colorAnimator.start();
        
//advancedSample001 customized Propertyass
MutableForegroundColorSpan.java

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

public class MutableForegroundColorSpan extends ForegroundColorSpan {

    private int mAlpha = 255;
    private int mForegroundColor;

    public MutableForegroundColorSpan(int alpha, int color) {
        super(color);
        mAlpha = alpha;
        mForegroundColor = color;
    }

    public MutableForegroundColorSpan(Parcel src) {
        super(src);
        mForegroundColor = src.readInt();
        mAlpha = src.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mForegroundColor);
        dest.writeFloat(mAlpha);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(getForegroundColor());
    }

    /**
     * @param alpha from 0 to 255
     */
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

    public void setForegroundColor(int foregroundColor) {
        mForegroundColor = foregroundColor;
    }

    public float getAlpha() {
        return mAlpha;
    }

    @Override
    public int getForegroundColor() {
        return Color.argb(mAlpha, Color.red(mForegroundColor), Color.green(mForegroundColor), Color.blue(mForegroundColor));
    }
}
MainActivity.java
  private static final Property<MutableForegroundColorSpan, Integer> MUTABLE_FOREGROUND_COLOR_SPAN_FC_PROPERTY =
            new Property<MutableForegroundColorSpan, Integer>(Integer.class, "MUTABLE_FOREGROUND_COLOR_SPAN_FC_PROPERTY") {

                @Override
                public void set(MutableForegroundColorSpan alphaForegroundColorSpanGroup, Integer value) {
                    alphaForegroundColorSpanGroup.setForegroundColor(value);
                }

                @Override
                public Integer get(MutableForegroundColorSpan span) {
                    return span.getForegroundColor();
                }
            };

MutableForegroundColorSpan span = new MutableForegroundColorSpan(255, mTextColor);        
WordPosition wordPosition = getWordPosition(mBaconIpsum);
  mBaconIpsumSpannableString.setSpan(span, wordPosition.start, wordPosition.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(span, MUTABLE_FOREGROUND_COLOR_SPAN_FC_PROPERTY, Color.BLACK, Color.RED);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //refresh
                mText.setText(mBaconIpsumSpannableString);
            }
        });
   objectAnimator.setInterpolator(mSmoothInterpolator);
   objectAnimator.setDuration(800);
   objectAnimator.start();
   
   // sample02  
    /**
     * change the activity alpha 
     *   dimBackground(1.0f,0.5f);  Window darken
     *   dimBackground(0.5f,1.0f);  Windwo lighten
     * @param from>=0&&from<=1.0f
     * @param to>=0&&to<=1.0f
     * 
     * */
   private void dimBackground(final float from, final float to) {
        final Window window = getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }
