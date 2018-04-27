package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.ui.List.RefreshList;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by 11234 on 2018/4/10.
 * 日历显示数值的核心部分
 */
public class CreateCalendar {

    public static ArrayList<TextView> sTextViewArrayList = new ArrayList<>();

    public static void createCalender(Activity activity, Calendar in_calendar) {

        Calendar calendar = (Calendar) in_calendar.clone();

        int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);                              //最大天数

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int week = calendar.get(Calendar.DAY_OF_WEEK);                                              //本月第一天是星期几

        Display display = activity.getWindowManager().getDefaultDisplay();                          //获取屏幕宽度
        int height = display.getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, height / 7, 1.0f);
        params.setMargins(0, 0, 0, 22);

        if (sTextViewArrayList.isEmpty()) {                                                          //刚进入日历，TextView列表为空，即初始化日历

            changelay06(activity, week);                                                             //是否删除第六行

            for (int k = 0, i = 0; i < 6; i++) {                                                    //行
                LinearLayout layout = activity.findViewById(R.id.lay01 + i);

                for (int j = 0; j < 7; j++) {                                                       //列
                    TextView textView = new TextView(activity);

                    if (k < week - 1 || k > maxday + week - 2) {
                        textView.setText("");
                        textView.setClickable(false);
                    } else {

                        textView = PrintRed.printRed(activity, calendar, textView);

                        textView.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));      //填字

                        ClickDay.setClick(activity, textView, calendar, (String) textView.getText());//设点击

                        IsToday.isToday(textView, calendar);                                        //是否今天

                        calendar.add(Calendar.DAY_OF_MONTH, 1);

                    }
                    k++;
                    textView.setGravity(Gravity.CENTER);                                            //设TextView属性
                    textView.setTextSize(17);

                    textView.setLayoutParams(params);

                    sTextViewArrayList.add(textView);                                               //将TextView放到列表中

                    layout.addView(textView);                                                       //布局中动态加入TextView

                }
            }

        } else {                                                                                    //TextView列表不为空，即翻页后更换月份

            changelay06(activity, week);                                                             //是否删除第六行

            ChangeTop.changeTop(activity, String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));      //改变日历头

            RefreshList.refreshList(activity, calendar);                                             //刷新列表

            for (int i = 0; i < 42; i++) {                                                          //直接从列表中读取TextView
                TextView textView = sTextViewArrayList.get(i);
                if (i < week - 1 || i > maxday + week - 2) {
                    textView.setText("");
                    textView.setClickable(false);
                    textView.setBackgroundColor(0);
                } else {
                    textView.setText(String.valueOf(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))));

                    textView = PrintRed.printRed(activity, calendar, textView);

                    ClickDay.setClick(activity, textView, calendar, (String) textView.getText());

                    IsToday.isToday(textView, calendar);

                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        }
    }

    private static void changelay06(Activity activity, int week) {                                         //动态显示和移除第六行，以便节省空间

        Log.e(TAG, "changelay06: remove+++++++++++++++++++");
        LinearLayout linearLayout = activity.findViewById(R.id.lay06);

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);

        if (week == 7) {
            linearLayout.setLayoutParams(layoutParams1);
        } else {
            linearLayout.setLayoutParams(layoutParams2);
        }
    }

}