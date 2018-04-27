package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by 11234 on 2018/3/28.
 * 这个类是用来构建日历的头顶部分（显示年月日）
 * 第一个方法用于MainActivity中
 * 第二个方法用于CreatCalendar中
 */

public class ChangeTop {

    private static TextView mTop;

    public static void changeTop(Activity activity, Calendar calendar) {

        int month = calendar.get(Calendar.MONTH) + 1;
        mTop = activity.findViewById(R.id.top_month);
        mTop.setText("   " + month + " 月");

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mTop = activity.findViewById(R.id.top_day);
        mTop.setText(day + "日");

        int year = calendar.get(Calendar.YEAR);
        mTop = activity.findViewById(R.id.top_year);
        mTop.setText(year + "年");


    }  //新建日历、滑动屏幕后，构建日历头顶

    public static void changeTop(Activity activity, String choice_date) {
        mTop = activity.findViewById(R.id.top_day);
        mTop.setText(choice_date + "日");
    }  //点击某一日后改变日历头顶

    public static void cleanTop(Activity activity, Calendar calendar, int i) {
        mTop = activity.findViewById(R.id.top_day);
        if (calendar.get(Calendar.MONTH) != Calendar.getInstance().get(Calendar.MONTH)) {
            mTop.setText("");
        }//翻页清除头顶
    }

}
