package com.example.zhangyijun.mycalendar.ui.Main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.method.Touch;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.https.GetDataFromHttp;
import com.example.zhangyijun.mycalendar.ui.List.ClickList;
import com.example.zhangyijun.mycalendar.ui.List.RefreshList;
import com.example.zhangyijun.mycalendar.ui.List.SortList;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by 11234 on 2018/4/11.
 * 点击某一日后的事件
 */
public class ClickDay {

    @SuppressLint("ClickableViewAccessibility")
    public static void setClick(final Activity activity, final TextView textView, Calendar in_calendar, final String day) {
        final Calendar calendar = (Calendar) in_calendar.clone();

        /*textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return true;
            }
        });*/

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetDataFromHttp getDataFromHttp = new GetDataFromHttp(activity, calendar);           //农历/节日事件
                getDataFromHttp.sendRequestWithHttpConnrction();

                ChangeTop.changeTop(activity, day);                                                  //创建日历的头顶部分

                BuildMission.addMission(activity, calendar);                                        //传入被选中的日期，为 + 号的点击事件做准备

                cleanClick(calendar);                                                               //清除其他日的背景

                if (!IsToday.isToday(textView, calendar)) {
                    textView.setBackgroundResource(R.drawable.checkday);                            //不是今天的话加上圆框
                }

                RefreshList.refreshList(activity,calendar);                                         //通知主活动刷新列表

                ClickList.clickList(activity, calendar);                                             //更换列表

                SortList.sort(activity, calendar);                                                   //列表排序
            }
        });
    }

    static void cleanClick(Calendar in_calendar) {

        Calendar calendar = (Calendar) in_calendar.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 42; i++) {
            TextView textView1 = CreateCalendar.sTextViewArrayList.get(i);
            IsToday.isToday(textView1, calendar);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}
