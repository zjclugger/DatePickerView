package com.bigkoo.pickerview.jview;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.utils.DateTimeFormat;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间选择器<br>
 * Created by King.Zi on 2019/12/17.<br>
 */
public class DateTimePickerView {
    private Context mContext;
    private TimePickerView mTimePickerView;
    private OnTimeSelectListener mTimeSelectListener;
    private Calendar mStartDate;
    private Calendar mEndDate;
    private TextView mDateTimeView;
    private DateTimeFormat mDateTimeFormat = DateTimeFormat.YMD;
    private SimpleDateFormat mDateFormat;
    private Date mInitDateTime = new Date(System.currentTimeMillis());
    private TimePickerBuilder mTimePickerBuilder;

    public DateTimePickerView(Context context, TimePickerView dateTimePickerView,
                              OnTimeSelectListener selectListener,
                              DateTimeFormat dateTimeFormat) {
        mContext = context;
        mTimePickerView = dateTimePickerView;
        mTimeSelectListener = selectListener;
        mDateTimeFormat = dateTimeFormat;
        mDateFormat = new SimpleDateFormat(mDateTimeFormat.getFormat());
        initBuilder();
    }

    public DateTimePickerView(Context context, OnTimeSelectListener selectListener,
                              DateTimeFormat dateTimeFormat) {
        this(context, null, selectListener, dateTimeFormat);
    }

    public DateTimePickerView(Context context, OnTimeSelectListener selectListener) {
        this(context, null, selectListener, DateTimeFormat.YMD);
    }

    private void initBuilder() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate 方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        if (null == mStartDate) {
            mStartDate = Calendar.getInstance();
            mStartDate.set(2018, 1, 1);
        }

        if (null == mEndDate) {
            mEndDate = Calendar.getInstance();
            mEndDate.set(Calendar.getInstance().get(Calendar.YEAR) + 20, 12, 31);
        }
        mTimePickerBuilder = new TimePickerBuilder(mContext, mTimeSelectListener)
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDividerColor(mContext.getResources().getColor(R.color.list_line))
                .setDate(selectedDate)
                .setRangDate(mStartDate, mEndDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        setDateText(date);
                    }
                })
                .setGravity(Gravity.CENTER)
                .setSubCalSize(12)
                .setTitleSize(14)
                .setContentTextSize(12)
                .setType(mDateTimeFormat)
                .setLabel(mContext.getString(R.string.pickerview_year),
                        mContext.getString(R.string.pickerview_month),
                        mContext.getString(R.string.pickerview_day),
                        mContext.getString(R.string.pickerview_hours),
                        mContext.getString(R.string.pickerview_minutes),
                        mContext.getString(R.string.pickerview_seconds))
                .setLineSpacingMultiplier(2f)
                .setTextXOffset(0, 0, 0, 0, 0, 0)
                .isCenterLabel(true); //是否只显示中间选中项的label文字，false则每项item全部都带有label。
    }

    /**
     * 显示指定的初始化时间
     *
     * @param dateTime
     */
    public DateTimePickerView setInitDateTime(String dateTime, String format) {
        mInitDateTime = DateTimeFormat.parseDateTime(dateTime, TextUtils.isEmpty(format) ?
                mDateTimeFormat.getFormat() : format);
        mTimePickerBuilder.setDate(DateTimeFormat.dataToCalendar(mInitDateTime));
        return this;
    }

    public DateTimePickerView setRangDate(Calendar startDate, Calendar endDate) {
        mStartDate = startDate;
        mEndDate = endDate;
        mTimePickerBuilder.setRangDate(mStartDate, mEndDate);
        return this;
    }

    public void setClassicLayout(final boolean showCancel) {
        mTimePickerBuilder.setLayoutRes(R.layout.custom_dialog_date_picker, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = v.findViewById(R.id.btnSubmit);
                if (!showCancel) {
                    v.findViewById(R.id.btnCancel).setVisibility(View.INVISIBLE);
                }
                mDateTimeView = v.findViewById(R.id.tvTitle);
                mDateTimeView.setText(mTimePickerBuilder.getPickerOptions().textContentTitle);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTimePickerView.returnData();
                        mTimePickerView.dismiss();
                    }
                });
            }
        });
    }

    public DateTimePickerView setOnTimeSelectChangeListener(OnTimeSelectChangeListener selectChangeListener) {
        mTimePickerBuilder.setTimeSelectChangeListener(selectChangeListener);
        return this;
    }

    public void show() {
        if (null == mTimePickerView) {
            setDateText(mInitDateTime);
            mTimePickerView = mTimePickerBuilder.build();
        }

        if (!mTimePickerView.isShowing()) {
            mTimePickerView.show();
        }
    }

    public TimePickerBuilder getBuilder() {
        return mTimePickerBuilder;
    }

    public void setBuilder(TimePickerBuilder timePickerBuilder) {
        this.mTimePickerBuilder = timePickerBuilder;
    }

    private void setDateText(Date date) {
        if (null != date) {
            if (null == mDateTimeView) {
                mTimePickerBuilder.setTitleText(mDateFormat.format(date));
            } else {
                mDateTimeView.setText(mDateFormat.format(date));
            }
        }
    }
}