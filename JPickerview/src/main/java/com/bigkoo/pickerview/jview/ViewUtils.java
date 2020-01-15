package com.bigkoo.pickerview.jview;

import android.content.Context;
import android.util.DisplayMetrics;

public class ViewUtils {
    private static final int GRAVITY_LEFT = 0;
    private static final int GRAVITY_CENTER = 1;
    private static final int GRAVITY_RIGHT = 2;
    private static final int TEXT_STYLE_BOLD = 0;
    private static final int TEXT_STYLE_ITALIC = 1;
    private static final int TEXT_STYLE_ITALIC_BOLD = 2;

    private ViewUtils() {
    }


    /**
     * dp转换为px
     *
     * @param context
     * @param value   单位dp
     * @return
     */
    public static int dp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    /**
     * sp转换为px
     *
     * @param context
     * @param value   单位sp
     * @return
     */
    public static int sp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }

    /**
     * px转换为dp
     *
     * @param context
     * @param value
     * @return
     */
    public static int px2dp(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (value / v + 0.5f);
    }

    /**
     * px转换为sp
     *
     * @param context
     * @param value
     * @return
     */
    public static int px2sp(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / v + 0.5f);
    }

    /**
     * 获取屏幕宽度(px)
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}