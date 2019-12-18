package com.bigkoo.pickerview.jview;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;

import java.util.List;

/**
 * 自定义选项选择器 <br>
 * Created by King.Zi on 2019/12/17.<br>
 */
public class OptionsPickerView<T> {
    private Context mContext;
    private com.bigkoo.pickerview.view.OptionsPickerView mOptionsPickerView;
    private OptionsPickerBuilder mOptionsPickerBuilder;
    private OnOptionsSelectListener mSelectListener;

    public OptionsPickerView(Context context, OnOptionsSelectListener listener) {
        mContext = context;
        mSelectListener = listener;
        initBuilder();
    }

    private void initBuilder() {
        mOptionsPickerBuilder = new OptionsPickerBuilder(mContext, mSelectListener)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(R.color.text_selected)
                .setContentTextSize(12)
                .setSubCalSize(12)
                .setTitleSize(14)
                .setDividerColor(mContext.getResources().getColor(R.color.list_line))
                .setSelectOptions(0)//默认选中项
                .setTextColorCenter(Color.BLACK)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideColor(0x99000000); //设置外部遮罩颜色;
    }

    public void setClassicLayout(final String titleText, final String cancelText,
                                 final String confirmText) {
        mOptionsPickerBuilder.setLayoutRes(R.layout.custom_option_picker_layout,
                new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        Button btnCancel = v.findViewById(R.id.btnCancel);
                        if (TextUtils.isEmpty(cancelText)) {
                            btnCancel.setVisibility(View.INVISIBLE);
                        } else {
                            btnCancel.setText(cancelText);
                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mOptionsPickerView.dismiss();
                                }
                            });
                        }

                        Button btnSubmit = v.findViewById(R.id.btnSubmit);
                        if (!TextUtils.isEmpty(confirmText)) {
                            btnSubmit.setText(confirmText);
                        }

                        btnSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mOptionsPickerView.returnData();
                                mOptionsPickerView.dismiss();
                            }
                        });
                        TextView tvTitle = v.findViewById(R.id.tvTitle);
                        tvTitle.setText(titleText);
                    }
                });
    }

    public void bindData(List<T> firstList) {
        create();
        mOptionsPickerView.setPicker(firstList);
    }

    public void bindData(List<T> firstList, List<T> secondList) {
        create();
        mOptionsPickerView.setPicker(firstList, secondList);
    }

    public void bindData(List<T> firstList, List<T> secondList, List<T> thirdList) {
        create();
        mOptionsPickerView.setPicker(firstList, secondList, thirdList);
    }

    /**
     * 创建选择器
     *
     * @return
     */
    private OptionsPickerView create() {
        mOptionsPickerView = mOptionsPickerBuilder.build();
        return this;
    }

    public OptionsPickerView OnOptionsSelectChangeListener(OnOptionsSelectChangeListener selectChangeListener) {
        mOptionsPickerBuilder.setOptionsSelectChangeListener(selectChangeListener);
        return this;
    }

    public void setOptionsPickerBuilder(OptionsPickerBuilder builder) {
        mOptionsPickerBuilder = builder;
    }

    public OptionsPickerBuilder getBuilder() {
        return mOptionsPickerBuilder;
    }

    public void show() {
        if (mOptionsPickerView != null && !mOptionsPickerView.isShowing()) {
            mOptionsPickerView.show(true);
        }
    }
}