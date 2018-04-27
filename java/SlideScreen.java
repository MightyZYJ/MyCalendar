package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.https.GetDataFromHttp;
import com.example.zhangyijun.mycalendar.ui.List.ClickList;
import com.example.zhangyijun.mycalendar.ui.List.RefreshList;

import java.util.Calendar;

/**
 * Created by 11234 on 2018/4/14.
 * 屏幕滑动事件
 */
public class SlideScreen {

    static void slideScreen(Activity activity, Calendar calendar) {

        CreateCalendar.createCalender(activity, calendar);

        if (calendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)) {
            calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

            RefreshList.refreshList(activity, calendar);
        }//如果滑到本月，自动选中本日
        ClickList.clickList(activity, calendar);

        ChangeTop.changeTop(activity, calendar);
        ChangeTop.cleanTop(activity, calendar, 1);

        TextView LunarMonth_tv = activity.findViewById(R.id.LunarMonth);
        if (calendar.get(Calendar.MONTH) != Calendar.getInstance().get(Calendar.MONTH)) {
            LunarMonth_tv.setText("");
            LunarMonth_tv.setClickable(false);
        } else {
            LunarMonth_tv.setClickable(true);
            GetDataFromHttp getDataFromHttp = new GetDataFromHttp(activity, calendar);               //农历/节日事件
            getDataFromHttp.sendRequestWithHttpConnrction();
        }
    }
}
