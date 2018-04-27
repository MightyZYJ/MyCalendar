package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.https.GetDataFromHttp;
import com.example.zhangyijun.mycalendar.ui.List.ClickList;
import com.example.zhangyijun.mycalendar.ui.List.RefreshList;
import com.example.zhangyijun.mycalendar.ui.List.SortList;

import java.util.Calendar;

import static com.example.zhangyijun.mycalendar.ui.Main.ClickDay.cleanClick;

/**
 * Created by 11234 on 2018/4/24.
 *
 */
public class SwitchDay {

    public static void switchDay(Activity activity, TextView textView, Calendar calendar) {

        GetDataFromHttp getDataFromHttp = new GetDataFromHttp(activity, calendar);           //农历/节日事件
        getDataFromHttp.sendRequestWithHttpConnrction();

        BuildMission.addMission(activity, calendar);                                        //传入被选中的日期，为 + 号的点击事件做准备

        ChangeTop.changeTop(activity,calendar);

        cleanClick(calendar);                                                               //清除其他日的背景

        if (!IsToday.isToday(textView, calendar)) {
            textView.setBackgroundResource(R.drawable.checkday);                            //不是今天的话加上圆框
        }

        RefreshList.refreshList(activity,calendar);                                         //通知主活动刷新列表

        ClickList.clickList(activity, calendar);                                             //更换列表

        SortList.sort(activity, calendar);                                                   //列表排序

    }

}
