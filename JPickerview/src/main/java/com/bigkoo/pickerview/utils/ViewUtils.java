package com.bigkoo.pickerview.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by King.Zi on 2019/09/26.
 */
public class ViewUtils {
    private ViewUtils() {
    }

    public static void setVisibility(boolean visible, View... views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        }
    }

    public static void setViewVisible(List<View> viewList, View showView) {
        if (viewList != null && viewList.size() > 0) {
            for (int i = 0; i < viewList.size(); i++) {
                if (viewList.get(i).equals(showView)) {
                    viewList.get(i).setVisibility(View.VISIBLE);
                } else {
                    viewList.get(i).setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 设置输入框提示文字的大小
     *
     * @param editText 输入框
     * @param textSize 字体大小
     */
    public static void setHintTextSize(EditText editText, int textSize) {
        if (editText == null || TextUtils.isEmpty(editText.getHint())) {
            return;
        }
        SpannableString hintText = new SpannableString(editText.getHint());
        //设置字体大小 true表示单位是sp
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize, true);
        hintText.setSpan(ass, 0, hintText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(hintText));
    }

    public static void setHintTextViewSize(TextView view, int textSize) {
        if (view == null || TextUtils.isEmpty(view.getHint())) {
            return;
        }
        SpannableString hintText = new SpannableString(view.getHint());
        //设置字体大小 true表示单位是sp
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize, true);
        hintText.setSpan(ass, 0, hintText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setHint(new SpannedString(hintText));
    }

    public static SpannedString getWrapperText(Context context, int fontSize, String text,
                                               int start, int end) {
        SpannableString hintText = new SpannableString(text);
        //设置字体大小 true表示单位是sp
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(fontSize, true);
        hintText.setSpan(ass, start, end == 0 ? hintText.length() : end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return new SpannedString(hintText);
    }
}
