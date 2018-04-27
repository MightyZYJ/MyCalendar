package com.example.zhangyijun.mycalendar.ui.Main;

import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;

import java.util.Calendar;

/**
 * Created by 11234 on 2018/4/11.
 * 判断是不是今天
 * 是的话加上框
 * 不是的话清除背景
 */

public class IsToday {

    public static boolean isToday(TextView textView, Calendar calendar) {

        String s = (String) textView.getText();

        if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) &&
                s.equals(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)))) {

            textView.setBackgroundResource(R.drawable.today);

            return true;

        } else {
            textView.setBackgroundColor(0);
            return false;
        }
    }
}
